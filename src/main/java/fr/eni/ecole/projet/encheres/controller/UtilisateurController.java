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
import org.springframework.validation.BindingResult;
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
import fr.eni.ecole.projet.encheres.bo.Utilisateur;
import fr.eni.ecole.projet.encheres.bo.VendeurDTO;
import jakarta.validation.Valid;

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
    public String afficherFormulaireInscription(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        model.addAttribute("adresse", new Adresse()); 
        return "view-inscription";
    }

    

    @PostMapping("/inscription")
    public String inscrireUtilisateur(@Valid @ModelAttribute Utilisateur utilisateur,BindingResult bindingResultUser, @Valid @ModelAttribute Adresse adresse,
            BindingResult bindingResultAdresse, 
            Model model) {

				if (bindingResultAdresse.hasErrors() || bindingResultUser.hasErrors()) {
				return "view-inscription";  
				}
        


        try {
            utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
            utilisateur.setCredit(10);
            adresse.setAddresse_eni(false);

            utilisateurService.save(utilisateur, adresse);

            return "redirect:/users/profil";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "view-inscription";
        }
    }



    
    
    @GetMapping("/profil")
    public String afficherMonProfil(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
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

  
}

