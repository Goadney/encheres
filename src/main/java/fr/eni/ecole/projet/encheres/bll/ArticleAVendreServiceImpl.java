package fr.eni.ecole.projet.encheres.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.ecole.projet.encheres.bo.ArticleAVendre;
import fr.eni.ecole.projet.encheres.dal.ArticleAVendreDAO;
import fr.eni.ecole.projet.encheres.dal.CategorieDAO;

@Service
public class ArticleAVendreServiceImpl implements ArticleAVendreService {
	
	private ArticleAVendreDAO articleAVendreDAO;
	private CategorieDAO categorieDAO;
	
	public ArticleAVendreServiceImpl(ArticleAVendreDAO articleAVendreDAO, CategorieDAO categorieDAO) {
		this.articleAVendreDAO = articleAVendreDAO;
		this.categorieDAO = categorieDAO;
	}
	
	@Override
	public void add(ArticleAVendre articleAVendre) {
		articleAVendreDAO.save(articleAVendre);
	}
	
	@Override
	public List<ArticleAVendre> getArticlesAVendre() {
		return articleAVendreDAO.findAll();
	}
	
	@Override
	public ArticleAVendre findById(Long id) {
		return articleAVendreDAO.findById(id)
		        .orElseThrow(() -> new RuntimeException("Article avec l'ID " + id + " non trouvé."));
	}
	
	@Override
	public void update(ArticleAVendre articleAVendre) {
		articleAVendreDAO.save(articleAVendre);
	}
	
	@Override
	public void delete(Long id) {
		if (articleAVendreDAO.existsById(id)) {
            articleAVendreDAO.deleteById(id);
        } else {
            throw new RuntimeException("Article avec l'ID " + id + " non trouvé.");
        }
    }

}
