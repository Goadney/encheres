package fr.eni.ecole.projet.encheres.controller;


import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.eni.ecole.projet.encheres.bll.UtilisateurServiceImpl;
import fr.eni.ecole.projet.encheres.bo.Adresse;
import fr.eni.ecole.projet.encheres.bo.InscriptionDTO;
import fr.eni.ecole.projet.encheres.bo.Utilisateur;
import fr.eni.ecole.projet.encheres.bo.VendeurDTO;

@Controller
@RequestMapping("/users") 
public class UtilisateurController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    private final UtilisateurServiceImpl utilisateurService;
    @Autowired
    public UtilisateurController(UtilisateurServiceImpl utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    
    @GetMapping("/inscription")
    public String afficherFormulaireInscription() {
        return "view-inscription";
    }
    

    @PostMapping("/inscription")
    public String inscrireUtilisateur(@ModelAttribute InscriptionDTO inscriptionDTO, Model model, RedirectAttributes redirectAttributes) {
        System.out.println("inscription");

        if (utilisateurService.findByPseudo(inscriptionDTO.getPseudo()) != null) {
            model.addAttribute("error", "Le pseudo est déjà utilisé.");
            return "view-inscription"; 
        }

        if (utilisateurService.findByEmail(inscriptionDTO.getEmail()) != null) {
            model.addAttribute("error", "L'email est déjà utilisé.");
            return "view-inscription"; 
        }
        try {
            // Créer un nouvel utilisateur
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setPseudo(inscriptionDTO.getPseudo());
            utilisateur.setEmail(inscriptionDTO.getEmail());
            utilisateur.setMotDePasse(passwordEncoder.encode(inscriptionDTO.getMotDePasse()));
            utilisateur.setNom(inscriptionDTO.getNom());
            utilisateur.setPrenom(inscriptionDTO.getPrenom());

            // Ajouter l'adresse
            Adresse adresse = new Adresse();
            adresse.setRue(inscriptionDTO.getRue());
            adresse.setCodePostal(inscriptionDTO.getCodePostal());
            adresse.setVille(inscriptionDTO.getVille());
            utilisateur.setAdresse(adresse);

            // Sauvegarder l'utilisateur
            Utilisateur utilisateurEnregistre = utilisateurService.save(utilisateur);
            System.out.println(utilisateurEnregistre);

            // Authentifier l'utilisateur
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                utilisateurEnregistre,
                null,
                Collections.singleton(new SimpleGrantedAuthority("USER"))
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Rediriger vers la page de profil
            redirectAttributes.addFlashAttribute("success", "Inscription réussie !");
            return "redirect:/users/profil";
        } catch (Exception e) {
            // Gestion des erreurs imprévues
            model.addAttribute("error", "Une erreur est survenue. Veuillez réessayer.");
            return "view-inscription"; // Retourne le formulaire avec l'erreur
        }
    }


    
    
    @GetMapping("/profil")
    public String afficherMonProfil(Model model) {
        // Récupérer l'utilisateur authentifié via SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login"; // Redirige vers la page de login si l'utilisateur n'est pas authentifié
        }

        String pseudo = authentication.getName(); 
        System.out.println(pseudo);
        
        Utilisateur utilisateur = utilisateurService.findByPseudo(pseudo);
        if (utilisateur != null) {
        	System.out.println("utilisateur pas null on ajoute a la vue");
            model.addAttribute("utilisateur", utilisateur.get());
            return "profil"; 
        } else {
            return "redirect:/login"; 
        }
    }


    @GetMapping("/profil/{pseudo}")
    public ResponseEntity<?> afficherProfilVendeur(@PathVariable String pseudo) {
        Utilisateur vendeur = utilisateurService.findByPseudo(pseudo);
        if (vendeur != null) {
            Utilisateur utilisateur = vendeur.get();
            return ResponseEntity.ok(new VendeurDTO(
                    utilisateur.getPseudo(),
                    utilisateur.getNom(),
                    utilisateur.getPrenom(),
                    utilisateur.getEmail(),
                    utilisateur.getTelephone()
            ));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/profil/modifier")
    public ResponseEntity<Utilisateur> modifierMonProfil(
            @RequestParam Long userId,
            @RequestBody Utilisateur utilisateurModifie) {
        Utilisateur utilisateurOpt = utilisateurService.findById(userId);

        if (utilisateurOpt != null) {
            Utilisateur utilisateur = utilisateurOpt.get();

            utilisateur.setNom(utilisateurModifie.getNom());
            utilisateur.setPrenom(utilisateurModifie.getPrenom());
            utilisateur.setEmail(utilisateurModifie.getEmail());
            utilisateur.setTelephone(utilisateurModifie.getTelephone());
            utilisateur.setMotDePasse(utilisateurModifie.getMotDePasse());

            Adresse adresse = utilisateur.getAdresse();
            if (adresse != null && utilisateurModifie.getAdresse() != null) {
                adresse.setRue(utilisateurModifie.getAdresse().getRue());
                adresse.setCodePostal(utilisateurModifie.getAdresse().getCodePostal());
                adresse.setVille(utilisateurModifie.getAdresse().getVille());
                utilisateur.setAdresse(adresse);
            }

            Utilisateur utilisateurMisAJour = utilisateurService.save(utilisateur);

            return ResponseEntity.ok(utilisateurMisAJour);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

