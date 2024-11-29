package fr.eni.ecole.projet.encheres.bll;

import java.util.List;

import fr.eni.ecole.projet.encheres.bo.ArticleAVendre;
import fr.eni.ecole.projet.encheres.bo.Enchere;

public interface ArticleAVendreService {
	
	void add(ArticleAVendre articleAVendre);
	
	List<ArticleAVendre> getArticlesAVendre();
	
	ArticleAVendre findById(Long id);
	
	void update(ArticleAVendre articleAVendre);
	
	void delete(Long id);

	List<ArticleAVendre> afficherEncheresActives();
		
}
