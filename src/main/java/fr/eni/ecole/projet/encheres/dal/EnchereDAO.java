package fr.eni.ecole.projet.encheres.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.eni.ecole.projet.encheres.bo.Enchere;
import fr.eni.ecole.projet.encheres.bo.EnchereId;

@Repository
public interface EnchereDAO extends JpaRepository<Enchere, EnchereId> {

	List<Enchere> findByUtilisateurPseudo(@Param("p") String pseudo);
	List<Enchere> findAll();
	
}


