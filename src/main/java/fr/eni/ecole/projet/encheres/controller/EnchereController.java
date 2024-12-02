package fr.eni.ecole.projet.encheres.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.ecole.projet.encheres.bll.ArticleAVendreService;
import fr.eni.ecole.projet.encheres.bll.CategorieService;
import fr.eni.ecole.projet.encheres.bll.EnchereService;
import fr.eni.ecole.projet.encheres.bll.UtilisateurService;
import fr.eni.ecole.projet.encheres.bo.ArticleAVendre;
import fr.eni.ecole.projet.encheres.bo.Categorie;
import fr.eni.ecole.projet.encheres.bo.Enchere;
import fr.eni.ecole.projet.encheres.bo.Utilisateur;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class EnchereController {

	private EnchereService enchereService;
	private CategorieService categorieService;
	private ArticleAVendreService articleAVendreService;
	private UtilisateurService utilisateurService;

	public EnchereController(EnchereService enchereService, CategorieService categorieService, ArticleAVendreService articleAVendreService, UtilisateurService utilisateurService) {
		super();
		this.enchereService = enchereService;
		this.categorieService = categorieService;
		this.articleAVendreService = articleAVendreService;
		this.utilisateurService = utilisateurService;
	}

	@GetMapping
	public String afficherEncheresActives(Model model) {

		List<ArticleAVendre> encheresActives = articleAVendreService.afficherEncheresActives();
		model.addAttribute("encheres", encheresActives);
		List<Categorie> categories = categorieService.getCategories();
		System.out.println("catégories" + categories);
		model.addAttribute("categories", categories);

		return "index";
	}
	
	@GetMapping("/encheres/{id}")
	public String afficherDetailEnchere(@PathVariable("id") Long id, 
	                                     HttpSession session, 
	                                     Model model) {
	    
	    Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateurEnSession");

	    if (utilisateurConnecte == null) {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        if (auth != null) {
	            String pseudoConnecte = auth.getName(); 
	            utilisateurConnecte = utilisateurService.findByPseudo(pseudoConnecte);  
	        }
	    }

	    ArticleAVendre enchere = articleAVendreService.findById(id);

	    model.addAttribute("utilisateur", utilisateurConnecte);
	    model.addAttribute("enchere", enchere);
	    
	    model.addAttribute("utilisateurConnecte", utilisateurConnecte != null ? utilisateurConnecte.getPseudo() : null);

	    return "view-detail-enchere";
	}

	
/*	TO DO	 
 * @PostMapping("/encherir") 
	  public String encherir (@RequestParam(name = "no_article", 
			  								required = true) long id,
			  				@ModelAttribute("enchere") 
	  						Enchere enchere, Model model, 
	  						@ModelAttribute("UtilisateurEnSession") 
	 
	  Utilisateur utilisateurEnSession) { 
		  if (utilisateurEnSession != null && utilisateurEnSession.getPseudo() != null) { 
		  if (enchere != null) {
			  
			  enchere.setUtilisateur(utilisateurEnSession);
			  enchereService.encherir(enchere, id); 
		
		return "view-detail-enchere"; }
	
	  model.addAttribute("nomEnchere", enchereService.consulterArticle(EnchereId));
	  model.addAttribute("idEnchere", EnchereId); return "view-detail-enchere";
	 
	  }
	 
	  } */
} 