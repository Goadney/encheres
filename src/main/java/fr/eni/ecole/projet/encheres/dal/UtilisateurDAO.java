package fr.eni.ecole.projet.encheres.dal;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.eni.ecole.projet.encheres.bo.Utilisateur;

@Repository
public interface UtilisateurDAO extends JpaRepository<Utilisateur, Long> {

    Optional<Utilisateur> findByPseudo(String pseudo);

    Optional<Utilisateur> findByEmail(String email);

    boolean existsByPseudo(String pseudo);

    boolean existsByEmail(String email);
}
