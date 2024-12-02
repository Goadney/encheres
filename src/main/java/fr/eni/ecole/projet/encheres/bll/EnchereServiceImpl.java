package fr.eni.ecole.projet.encheres.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.ecole.projet.encheres.bo.Enchere;
import fr.eni.ecole.projet.encheres.bo.Utilisateur;
import fr.eni.ecole.projet.encheres.dal.CategorieDAO;
import fr.eni.ecole.projet.encheres.dal.EnchereDAO;

@Service
public class EnchereServiceImpl implements EnchereService {

	private EnchereDAO enchereDAO;
	private CategorieDAO categorieDAO;

	public EnchereServiceImpl(EnchereDAO enchereDAO, CategorieDAO categorieDAO) {
		this.enchereDAO = enchereDAO;
		this.categorieDAO = categorieDAO;
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
	public List<Enchere> afficherAchats(String pseudo) {
		return enchereDAO.findByUtilisateurPseudo(pseudo);
	}


	@Override
	public void encherir(Enchere enchere, long id) {
		// TODO Auto-generated method stub

	}


	@Override
	public List<Enchere> getAchatsUtilisateur(String recherche, Long categorieId, List<Enchere> achats,
			Utilisateur utilisateur) {
		
		return null;
	}

	
	
}
