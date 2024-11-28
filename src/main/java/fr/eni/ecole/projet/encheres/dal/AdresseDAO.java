package fr.eni.ecole.projet.encheres.dal;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import fr.eni.ecole.projet.encheres.bo.Adresse;

@Repository
public interface AdresseDAO extends JpaRepository<Adresse, Integer>, JpaSpecificationExecutor<Adresse> {


}
