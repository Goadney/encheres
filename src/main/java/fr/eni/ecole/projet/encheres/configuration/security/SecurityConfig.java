package fr.eni.ecole.projet.encheres.configuration.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import fr.eni.ecole.projet.encheres.bll.UtilisateurServiceImpl;
import fr.eni.ecole.projet.encheres.bo.Utilisateur;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UtilisateurServiceImpl utilisateurService;

    public SecurityConfig(UtilisateurServiceImpl utilisateurService) {
        this.utilisateurService = utilisateurService;
    }
    

    @Bean
    public UserDetailsService userDetailsService() {
        return new JpaUserDetailsService(utilisateurService);
    }
    
    
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	// pour que les 
        // Définit un encodeur par défaut (bcrypt ici)
        String idForEncode = "bcrypt";
        // Liste des encodeurs disponibles
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());

        // Crée un delegating password encoder
        return new DelegatingPasswordEncoder(idForEncode, encoders);
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/css/**", "/images/**").permitAll() 
                .requestMatchers("/login", "/register").permitAll()
                .requestMatchers(HttpMethod.GET, "/encheres/**").hasAnyRole("USER", "ADMIN") 
                .requestMatchers("/utilisateurs/admin/delete/*").hasRole("ADMIN") 
                .requestMatchers("/users/profil").hasRole("USER") 
                .anyRequest().permitAll() 
                )
            .formLogin(form -> form
                .loginPage("/login").permitAll() 
                .defaultSuccessUrl("/", true) 
                )
            .logout(logout -> logout
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").permitAll()
            );

        return http.build();
    }

  
 
}
