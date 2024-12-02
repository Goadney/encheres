package fr.eni.ecole.projet.encheres.bll;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import fr.eni.ecole.projet.encheres.bo.Adresse;
import fr.eni.ecole.projet.encheres.bo.Utilisateur;
import fr.eni.ecole.projet.encheres.dal.AdresseDAO;
import fr.eni.ecole.projet.encheres.dal.UtilisateurDAO;
import fr.eni.ecole.projet.encheres.exceptions.CantFindUser;
import fr.eni.ecole.projet.encheres.exceptions.DuplicateUserException;
import fr.eni.ecole.projet.encheres.exceptions.InvalidPasswordException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

	private UtilisateurDAO daoUser;
	private AdresseDAO daoAd;
	
	public UtilisateurServiceImpl(UtilisateurDAO dao, AdresseDAO daoAd) {
		this.daoUser = dao;
		this.daoAd = daoAd;
	}
	
	@Override
	public Utilisateur save(Utilisateur user, Adresse ad) {
		if(user.getMotDePasse() == null || user.getMotDePasse().length() > 68) {
	        throw new InvalidPasswordException("Mot de passe incorrect");	
		}
		if(daoUser.findByPseudo(user.getPseudo()).isPresent() || daoUser.findByEmail(user.getEmail()).isPresent())  {
	        throw new DuplicateUserException("Ce mail ou ce pseudo est déjà pris !! On arrête les test anne-lise !!");	
	    }

		daoAd.save(ad);
		user.setAdresse(ad);
		daoUser.save(user);
		
		return user;
		
	}
	
	@Override
	public void update(Utilisateur utilisateur) {
	    // Validation et mise à jour de l'utilisateur
		daoUser.save(utilisateur);
	}

	
	
	@Override
	public Utilisateur findByPseudo(String pseudo) {
	    java.util.Optional<Utilisateur> utilisateurOpt = daoUser.findByPseudo(pseudo);
		    if (utilisateurOpt.isPresent()) {
		        return utilisateurOpt.get();
		    } else {
		        throw new CantFindUser("Cet utilisateur n'existe pas");	
		    }		
	    }
	
	@Override
	public Utilisateur findByEmail(String email) {

		    java.util.Optional<Utilisateur> utilisateurOpt = daoUser.findByEmail(email);
			    if (utilisateurOpt.isPresent()) {
			        return utilisateurOpt.get();
			    } else {
			        return null;
			    }	
	    }




	@Override
	public void delete(String pseudo) {
	    java.util.Optional<Utilisateur> utilisateurOpt = daoUser.findByPseudo(pseudo);
	    if (utilisateurOpt.isPresent()) {
	    	daoUser.delete(utilisateurOpt.get());
	        return;
	    } else {
	        throw new CantFindUser("Cet utilisateur n'existe pas");	
	    }		
    }
	
	public Utilisateur getUtilisateurConnecte() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null && authentication.getPrincipal() instanceof Utilisateur) {
	        return (Utilisateur) authentication.getPrincipal();
	    }
	    return null;
	}



}
