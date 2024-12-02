package fr.eni.ecole.projet.encheres.bll;

import java.util.List;

import fr.eni.ecole.projet.encheres.bo.Enchere;
import fr.eni.ecole.projet.encheres.bo.Utilisateur;

public interface EnchereService {
	
	void encherir(Enchere enchere, long id);

	void add(Enchere enchere);
	
	List<Enchere> afficherEncheresActives();

	List<Enchere> afficherAchats(String pseudo);

	List<Enchere> getAchatsUtilisateur(String recherche, Long categorieId, List<Enchere> achats, Utilisateur utilisateur); 
	
}


