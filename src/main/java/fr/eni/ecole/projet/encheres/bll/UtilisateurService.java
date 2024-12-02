package fr.eni.ecole.projet.encheres.bll;

import org.springframework.stereotype.Service;

import fr.eni.ecole.projet.encheres.bo.Adresse;
import fr.eni.ecole.projet.encheres.bo.Utilisateur;

@Service
public interface UtilisateurService {
	
	Utilisateur save(Utilisateur user, Adresse ad);
	
	Utilisateur findByPseudo(String pseudo);
	
	Utilisateur findByEmail(String email);
	
	void update(Utilisateur user);
	
	void delete(String pseudo);
	
	void updatePassword(Utilisateur user);
 
}