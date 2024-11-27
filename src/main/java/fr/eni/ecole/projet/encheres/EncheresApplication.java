package fr.eni.ecole.projet.encheres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.eni.ecole.projet.encheres.bll.UtilisateurService;


@SpringBootApplication
public class EncheresApplication {

	private static UtilisateurService s;

	@Autowired
	public EncheresApplication(UtilisateurService s) {
		this.s = s;
	}
	public static void main(String[] args) {
		SpringApplication.run(EncheresApplication.class, args);
	    PasswordEncoder encoder = new BCryptPasswordEncoder();
	    System.out.println(encoder.encode("password"));
	    System.out.println();
	    
        String pseudo = "coach_admin";
        System.out.println(s.findByPseudo(pseudo));
	    
	}

}
