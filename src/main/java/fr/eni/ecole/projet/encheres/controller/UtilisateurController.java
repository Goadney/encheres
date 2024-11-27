package fr.eni.ecole.projet.encheres.controller;


import java.util.Collections;
import java.util.Optional;

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

import fr.eni.ecole.projet.encheres.bll.UtilisateurService;
import fr.eni.ecole.projet.encheres.bo.Adresse;
import fr.eni.ecole.projet.encheres.bo.InscriptionDTO;
import fr.eni.ecole.projet.encheres.bo.Utilisateur;
import fr.eni.ecole.projet.encheres.bo.VendeurDTO;

@Controller
@RequestMapping("/users") 
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private PasswordEncoder passwordEncoder = null;
    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
        this.passwordEncoder = passwordEncoder;
    }

    
    @GetMapping("/inscription")
    public String afficherFormulaireInscription() {
        return "view-inscription";
    }
    

    @PostMapping("/inscription")
    public ResponseEntity<?> inscrireUtilisateur(@ModelAttribute InscriptionDTO inscriptionDTO) {
        System.out.println("inscription");
        // Vérifier si un utilisateur avec ce pseudo ou email existe déjà
        if (utilisateurService.getUtilisateurByPseudo(inscriptionDTO.getPseudo()).isPresent()) {
            return ResponseEntity.badRequest().body("Le pseudo est déjà utilisé.");
        }

        if (utilisateurService.getUtilisateurByEmail(inscriptionDTO.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("L'email est déjà utilisé.");
        }

        // Créer un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPseudo(inscriptionDTO.getPseudo());
        utilisateur.setEmail(inscriptionDTO.getEmail());
        utilisateur.setMotDePasse(passwordEncoder.encode(inscriptionDTO.getMotDePasse())); // Encodage du mot de passe
        utilisateur.setNom(inscriptionDTO.getNom());
        utilisateur.setPrenom(inscriptionDTO.getPrenom());

        // Enregistrer l'utilisateur dans la base de données
        Utilisateur utilisateurEnregistre = utilisateurService.saveUtilisateur(utilisateur);

        // Exemple pour connecter automatiquement l'utilisateur après l'inscription
        Authentication authentication = new UsernamePasswordAuthenticationToken(utilisateur, null, Collections.singleton(new SimpleGrantedAuthority("USER")));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Retourner une réponse avec l'utilisateur créé
        return ResponseEntity.ok(utilisateurEnregistre);
    }

    
    
    @GetMapping("/profil")
    public String afficherMonProfil(Model model) {
        // Récupérer l'utilisateur authentifié via SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login"; // Redirige vers la page de login si l'utilisateur n'est pas authentifié
        }

        // Récupérer le pseudo ou l'ID de l'utilisateur authentifié
        String pseudo = authentication.getName(); // Si tu utilises le pseudo comme identifiant unique
        
        Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateurByPseudo(pseudo);
        if (utilisateur.isPresent()) {
            // Ajouter l'utilisateur au modèle pour qu'il soit accessible dans la vue
            model.addAttribute("utilisateur", utilisateur.get());
            return "profil"; // Nom de la vue Thymeleaf (par exemple, profil.html)
        } else {
            return "redirect:/login"; // Si l'utilisateur n'est pas trouvé, rediriger vers la page de login
        }
    }


    @GetMapping("/profil/{pseudo}")
    public ResponseEntity<?> afficherProfilVendeur(@PathVariable String pseudo) {
        Optional<Utilisateur> vendeur = utilisateurService.getUtilisateurByPseudo(pseudo);
        if (vendeur.isPresent()) {
            // Renvoyer seulement les informations nécessaires
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
        Optional<Utilisateur> utilisateurOpt = utilisateurService.getUtilisateurById(userId);

        if (utilisateurOpt.isPresent()) {
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

            Utilisateur utilisateurMisAJour = utilisateurService.saveUtilisateur(utilisateur);

            return ResponseEntity.ok(utilisateurMisAJour);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

