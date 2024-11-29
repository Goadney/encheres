package fr.eni.ecole.projet.encheres.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.ecole.projet.encheres.bll.CategorieService;
import fr.eni.ecole.projet.encheres.bll.EnchereService;
import fr.eni.ecole.projet.encheres.bo.Categorie;
import fr.eni.ecole.projet.encheres.bo.Enchere;
import fr.eni.ecole.projet.encheres.bo.Utilisateur;

@Controller
@RequestMapping("/encheres") 
@SessionAttributes("utilisateurEnSession")
public class EnchereController {

    private EnchereService enchereService;
    private CategorieService categorieService;
    
    public EnchereController(EnchereService enchereService, CategorieService categorieService) {
		super();
		this.enchereService = enchereService;
		this.categorieService = categorieService;
	}

	@GetMapping
    public String afficherEncheresActives(Model model) {
    
     //   List<Enchere> encheresActives = enchereService.afficherEncheresActives();
     //   model.addAttribute("encheres", encheresActives);
		List<Categorie> categories = categorieService.getCategories();
		System.out.println("catégories" +categories);
	    model.addAttribute("categories", categories);
        
        return "index";
    }
	    
	    @GetMapping("/achats")
	    public String afficherEncheresParUtilisateur(Model model, Authentication authentication) {
	    	Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();
	    	String pseudo = utilisateur.getPseudo();
	    	
	    	model.addAttribute("encheres", enchereService.afficherEncheresParUtilisateur(pseudo));
	    	
	        return "view-encheres-achats";
	    }
	    
	/*    @PostMapping("/encherir")
	   public String encherir(@RequestParam(name = "no_article", required = true) long id,  
			   @ModelAttribute("enchere") Enchere enchere, Model model, @ModelAttribute("UtilisateurEnSession") Utilisateur utilisateurEnSession) {
		   if (utilisateurEnSession != null && utilisateurEnSession.getPseudo() != null) {
			   if (enchere != null) {
				   enchere.setUtilisateur(utilisateurEnSession);
				   enchereService.encherir(enchere, id);
				   return "redirect:/encheres";
			   }
	 
	    }
		   
		   model.addAttribute("nomEnchere", enchereService.consulterArticle(EnchereId));
		   model.addAttribute("idEnchere", EnchereId);
		   return "view-detail-enchere";
		   
	}*/
	    
}