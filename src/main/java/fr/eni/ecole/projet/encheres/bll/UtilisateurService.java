package fr.eni.ecole.projet.encheres.bll;

import org.springframework.stereotype.Service;

import fr.eni.ecole.projet.encheres.bo.Utilisateur;

@Service
public interface UtilisateurService {
	
	Utilisateur save(Utilisateur user);
	
	Utilisateur findByPseudo(String pseudo);
	
	Utilisateur findByEmail(String findByEmail);

	Utilisateur findById(Long id);
	
	void update(Utilisateur user);
	
	void delete(Long id);
 
}