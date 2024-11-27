package fr.eni.ecole.projet.encheres.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import fr.eni.ecole.projet.encheres.bo.Categorie;

public interface CategorieDAO extends JpaRepository<Categorie, Long>, JpaSpecificationExecutor<Categorie> {

}
