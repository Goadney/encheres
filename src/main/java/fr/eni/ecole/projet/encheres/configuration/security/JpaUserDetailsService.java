package fr.eni.ecole.projet.encheres.configuration.security;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fr.eni.ecole.projet.encheres.bll.UtilisateurService;
import fr.eni.ecole.projet.encheres.bo.Utilisateur;

public class JpaUserDetailsService implements UserDetailsService {

    private final UtilisateurService utilisateurService;

    public JpaUserDetailsService(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurService.getUtilisateurByPseudo(pseudo)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + pseudo));

        return User.builder()
                .username(utilisateur.getPseudo())
                .password(utilisateur.getMotDePasse())
                .roles(utilisateur.isAdmin() ? "ADMIN" : "USER") 
                .build();
    }
}
