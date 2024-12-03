package fr.eni.ecole.projet.encheres.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.ecole.projet.encheres.bll.UtilisateurServiceImpl;
import fr.eni.ecole.projet.encheres.bo.Adresse;
import fr.eni.ecole.projet.encheres.bo.Utilisateur;
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





    @GetMapping({"/profil", "/profil/{pseudo}"})
    public String afficherProfilVendeur(@PathVariable(value = "pseudo", required = false) String pseudo, Model model) {

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Utilisateur utilisateur;

            if (pseudo == null) {
                String username = authentication.getName();
                utilisateur = utilisateurService.findByPseudo(username);
            } else {
                utilisateur = utilisateurService.findByPseudo(pseudo);
            }

            boolean me = utilisateur.getPseudo().equals(authentication.getName());
            model.addAttribute("utilisateur", utilisateur);
            model.addAttribute("me", me);

            return "profil";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "profil";
        }
    }
    
    @GetMapping("/profil/modifier")
    public String modifierMonProfil(Model model) {
    	try {
    		
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
        	return "redirect:/";
        }
        
        String pseudo = authentication.getName();

        Utilisateur utilisateur = utilisateurService.findByPseudo(pseudo);
        model.addAttribute("utilisateur", utilisateur);
        return "profil-modify";
    	}
		catch(Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/";
		}
    }
    
    @PostMapping("/profil/modifier")
    public String modifierMonProfilForm(
    		@ModelAttribute Utilisateur utilisateurModifie, Model model) {
        
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String pseudo = authentication.getName();

            Utilisateur utilisateur = utilisateurService.findByPseudo(pseudo);
            utilisateur.setPrenom(utilisateurModifie.getPrenom());
            utilisateur.setTelephone(utilisateurModifie.getTelephone());
            utilisateur.setNom(utilisateurModifie.getNom());
            utilisateur.setEmail(utilisateurModifie.getEmail());
            utilisateur.getAdresse().setRue(utilisateurModifie.getAdresse().getRue());
            utilisateur.getAdresse().setCodePostal(utilisateurModifie.getAdresse().getCodePostal());
            utilisateur.getAdresse().setVille(utilisateurModifie.getAdresse().getVille());

            utilisateurService.update(utilisateur);
            boolean me = utilisateur.getPseudo().equals(authentication.getName());
            model.addAttribute("utilisateur", utilisateur);
            model.addAttribute("me", me);
            return "/profil";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "/profil/modifier";
        }
    }
    
    @GetMapping("/profil/modifier/password")
    public String afficherFormulaireModificationMotDePasse(Model model) {
        try {
            return "profil-modify-password";  // Utilisez une vue qui correspond à la modification du mot de passe
        } catch (Exception e) {
            return "profil-modify-password";
        }
    }


    @PostMapping("/profil/modifier/password")
    public String modifierMotDePasse(
            @RequestParam("old_password") String ancienMotDePasse,
            @RequestParam("new_password") String nouveauMotDePasse,
            @RequestParam("new_password_confirmation") String confirmationMotDePasse,
            Model model) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                return "redirect:/";
            }

            String pseudo = authentication.getName();
            Utilisateur utilisateur = utilisateurService.findByPseudo(pseudo);

            if (!passwordEncoder.matches(ancienMotDePasse, utilisateur.getMotDePasse())) {
                model.addAttribute("error", "Le mot de passe actuel est incorrect.");
                return "profil-modify";
            }

            if (!nouveauMotDePasse.equals(confirmationMotDePasse)) {
                model.addAttribute("error", "La confirmation du mot de passe ne correspond pas.");
                return "profil-modify";
            }

            // Mise à jour du mot de passe
            utilisateur.setMotDePasse(passwordEncoder.encode(nouveauMotDePasse));
            utilisateurService.updatePassword(utilisateur);

            model.addAttribute("message", "Mot de passe modifié avec succès.");
            return "redirect:/users/profil";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la modification du mot de passe : " + e.getMessage());
            return "profil-modify";
        }
    }


    
    @GetMapping("/profil/supprimer")
    public String supprimerCompte(Model model) {
        
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String pseudo = authentication.getName();

            Utilisateur utilisateur = utilisateurService.findByPseudo(pseudo);

            utilisateurService.update(utilisateur);
            if(utilisateur.getPseudo().equals(authentication.getName())) { 
            	utilisateurService.delete(pseudo);
            };
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "/profil/modifier";
        }
    }



  
}

