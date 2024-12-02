package fr.eni.ecole.projet.encheres.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.ecole.projet.encheres.bll.UtilisateurService;
import fr.eni.ecole.projet.encheres.bo.Utilisateur;
import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("utilisateurEnSession")
public class LoginController {
	
	private UtilisateurService utilisateurService;
	
	 public LoginController(UtilisateurService utilisateurService) {
	        this.utilisateurService = utilisateurService;
	    }


    @GetMapping("/login")
    public String login() {
        return "login"; 
    }
    
    @PostMapping("/login")
    public String connecterUtilisateur(@RequestParam String email, @RequestParam String motDePasse, HttpSession session) {
        Utilisateur utilisateur = utilisateurService.findByEmail(email);
        if (utilisateur != null) {
            session.setAttribute("utilisateurEnSession", utilisateur);
            return "redirect:/"; 
        } else {
            return "login"; 
        }
    }
}
