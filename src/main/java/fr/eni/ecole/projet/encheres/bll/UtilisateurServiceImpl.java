package fr.eni.ecole.projet.encheres.bll;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.ecole.projet.encheres.bo.Utilisateur;
import fr.eni.ecole.projet.encheres.dal.UtilisateurDAO;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	private UtilisateurDAO dao;
	
	@Autowired
	public UtilisateurServiceImpl(UtilisateurDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public Utilisateur save(Utilisateur user) {
		return dao.save(user);		
	}


	@Override
	public Utilisateur findByPseudo(String pseudo) {
	    Optional<Utilisateur> utilisateurOpt = dao.findByPseudo(pseudo);
		    if (utilisateurOpt.isPresent()) {
		        return utilisateurOpt.get();
		    } else {
		        return null;
		    }		
	    }
	
	@Override
	public Utilisateur findByEmail(String email) {

		    Optional<Utilisateur> utilisateurOpt = dao.findByEmail(email);
			    if (utilisateurOpt.isPresent()) {
			        return utilisateurOpt.get();
			    } else {
			        return null;
			    }	
	    }

	@Override
	public Utilisateur findById(Long id) {
	    Optional<Utilisateur> utilisateurOpt = dao.findById(id);
	    if (utilisateurOpt.isPresent()) {
	        return utilisateurOpt.get();
	    } else {
	        throw new EntityNotFoundException("Utilisateur non trouvé pour l'ID: " + id);
	    }
	}


	@Override
	public void update(Utilisateur user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}



}
