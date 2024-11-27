package fr.eni.ecole.projet.encheres.configuration.security;
import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fr.eni.ecole.projet.encheres.bll.UtilisateurServiceImpl;
import fr.eni.ecole.projet.encheres.bo.Utilisateur;

public class JpaUserDetailsService implements UserDetailsService {

    private final UtilisateurServiceImpl utilisateurService;

    public JpaUserDetailsService(UtilisateurServiceImpl utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurService.findByPseudo(pseudo);
        
        if(utilisateur !=null) {
        	
    	    
	       return User.builder()
	                .username(utilisateur.getPseudo())
	                .password(utilisateur.getMotDePasse())
	                .roles(utilisateur.isAdmin() ? "ADMIN" : "USER") 
	                .build();
        }
        
        return null;
 
    }
}
