package fr.eni.ecole.projet.encheres.bll;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import fr.eni.ecole.projet.encheres.bo.ArticleAVendre;
import fr.eni.ecole.projet.encheres.bo.Categorie;
import fr.eni.ecole.projet.encheres.bo.Enchere;
import fr.eni.ecole.projet.encheres.dal.ArticleAVendreDAO;
import fr.eni.ecole.projet.encheres.dal.CategorieDAO;
import fr.eni.ecole.projet.encheres.dal.EnchereDAO;

@Service
public class EnchereServiceImpl implements EnchereService {
	
	private EnchereDAO enchereDAO;
	private CategorieDAO categorieDAO;

	
	public EnchereServiceImpl(EnchereDAO enchereDAO, CategorieDAO categorieDAO) {
		this.enchereDAO =enchereDAO;
		this.categorieDAO =categorieDAO;
		}
	
	@Override
	public void add(Enchere enchere) {
		enchereDAO.save(enchere);
	}
	
	@Override
	public List<Enchere> afficherEncheresActives() {
		return enchereDAO.findAll();
		
	}
	
	@Override
	public List<Enchere> afficherEncheresParUtilisateur(String pseudo) {
		return enchereDAO.findByUtilisateurPseudo(pseudo);
	}

	@Override
	public void encherir(Enchere enchere) {
			// TODO Auto-generated method stub
			
	}

	@Override
	public void encherir(Enchere enchere, long id) {
			// TODO Auto-generated method stub
			
	}

	@Override
	public List<Categorie> afficherCategories() {
		List<Categorie> categories = categorieDAO.findAll();
	    System.out.println("Catégories récupérées : " + categories);
	    return categories;
	}

	
}
