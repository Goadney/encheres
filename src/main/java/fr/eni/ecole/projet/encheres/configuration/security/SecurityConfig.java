package fr.eni.ecole.projet.encheres.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import fr.eni.ecole.projet.encheres.bll.UtilisateurServiceImpl;
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
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/css/*", "/images/*").permitAll()
                .requestMatchers("/utilisateurs/admin/delete/*").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/accueil").hasAnyRole("USER", "ADMIN")
            	.requestMatchers(HttpMethod.GET, "/encheres/details").hasAnyRole("USER", "ADMIN")
            	.requestMatchers(HttpMethod.POST, "/encheres/details").hasAnyRole("USER", "ADMIN")
            	.requestMatchers("/utilisateurs/supprimer").hasAnyRole("ADMIN")
            	.requestMatchers("/utilisateurs/desactiver").hasAnyRole("ADMIN") 
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
                .logoutSuccessUrl("/")
                .permitAll()
            );

        return http.build();
    }
    
    


}
