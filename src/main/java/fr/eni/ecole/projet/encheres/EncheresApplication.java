package fr.eni.ecole.projet.encheres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EncheresApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncheresApplication.class, args);
	    PasswordEncoder encoder = new BCryptPasswordEncoder();
	    System.out.println(encoder.encode("password"));
	}

}
