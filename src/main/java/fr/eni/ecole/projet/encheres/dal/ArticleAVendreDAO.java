package fr.eni.ecole.projet.encheres.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import fr.eni.ecole.projet.encheres.bo.ArticleAVendre;

public interface ArticleAVendreDAO extends JpaRepository<ArticleAVendre, Long>, JpaSpecificationExecutor<ArticleAVendre> {
	
	
}
