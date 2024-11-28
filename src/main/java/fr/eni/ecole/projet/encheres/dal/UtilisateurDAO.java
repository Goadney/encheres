package fr.eni.ecole.projet.encheres.dal;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.eni.ecole.projet.encheres.bo.Utilisateur;

@Repository
public interface UtilisateurDAO extends JpaRepository<Utilisateur, String>, JpaSpecificationExecutor<Utilisateur> {

	Optional<Utilisateur> findByPseudo(@Param("pseudo") String pseudo);

	Optional<Utilisateur> findByEmail(@Param("email") String email);

}
