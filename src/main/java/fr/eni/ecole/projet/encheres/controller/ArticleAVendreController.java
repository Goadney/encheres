package fr.eni.ecole.projet.encheres.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.ecole.projet.encheres.bll.ArticleAVendreService;
import fr.eni.ecole.projet.encheres.bll.CategorieService;
import fr.eni.ecole.projet.encheres.bo.ArticleAVendre;
import fr.eni.ecole.projet.encheres.bo.Categorie;

@RestController
@RequestMapping("/articles")
public class ArticleAVendreController {
	
	private final ArticleAVendreService articleAVendreService;
	private final CategorieService categorieService;
	
	public ArticleAVendreController(final ArticleAVendreService articleAVendreService, 
			final CategorieService categorieService) {
		this.articleAVendreService = articleAVendreService;
		this.categorieService = categorieService;
	}
	
	@GetMapping("/creer")
	public String creerArticleAVendre(Model model) {
		ArticleAVendre articleAVendre = new ArticleAVendre();
		List<Categorie> categories = categorieService.getCategories();
		model.addAttribute("articleAVendre", articleAVendre);
		return "view-article-creer";
	}
	
	
//	@GetMapping
//	public String afficherArticlesAVendre(Model model) {
//		List<ArticleAVendre> lstArticlesAVendre = articleAVendreService.getArticlesAVendre();
//		
//		System.out.println("Articles récupérés : " + lstArticlesAVendre);
//		
//		model.addAttribute("articlesAVendre", lstArticlesAVendre);
//		return "view-articles";
//	}
	
}
