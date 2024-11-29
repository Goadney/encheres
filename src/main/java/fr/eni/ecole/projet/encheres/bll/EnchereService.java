package fr.eni.ecole.projet.encheres.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.ecole.projet.encheres.bo.Categorie;
import fr.eni.ecole.projet.encheres.bo.Enchere;

@Service
public interface EnchereService {
	
	List<Enchere> afficherEncheresParUtilisateur(String pseudo);
	
	void encherir(Enchere enchere, long id);

	void add(Enchere enchere);

	void encherir(Enchere enchere);
	
	List<Categorie> afficherCategories(); 
	
}


