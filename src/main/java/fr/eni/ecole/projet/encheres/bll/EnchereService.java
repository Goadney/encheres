package fr.eni.ecole.projet.encheres.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.ecole.projet.encheres.bo.Categorie;
import fr.eni.ecole.projet.encheres.bo.Enchere;
import fr.eni.ecole.projet.encheres.bo.Utilisateur;

@Service
public interface EnchereService {
	
	void encherir(Enchere enchere, long id);

	void add(Enchere enchere);

	void encherir(Enchere enchere);
	
	List<Categorie> afficherCategories();

	List<Enchere> afficherEncheresActives();

	List<Enchere> afficherAchats(String pseudo);

	List<Enchere> getAchatsUtilisateur(String recherche, Long categorieId, List<Enchere> achats, Utilisateur utilisateur); 
	
}


