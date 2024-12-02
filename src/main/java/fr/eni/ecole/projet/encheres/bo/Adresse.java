package fr.eni.ecole.projet.encheres.bo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "adresses")
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    @Column(name = "no_adresse")
    private Long NoAdresse;
    
    @NotNull(message = "{rue.notnull}")
    @Size(max = 100, message = "{rue.size}")
    private String rue;

    @NotNull(message = "{codePostal.notnull}")
    @Size(max = 10, message = "{codePostal.size}")
    private String codePostal;

    @NotNull(message = "{ville.notnull}")
    @Size(max = 50, message = "{ville.size}")
    private String ville;
    
    @Column(name = "adresse_eni")
    private Boolean adresseEni;
    
    
/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long no_adresse;
    @NotBlank
    @Max(value=100, message="La rue doit faire 100 caractères ou moins")
    private String rue;
    @NotBlank
    @Max( message="La rue doit faire 10 caractères ou moins", value = 10)
    private String codePostal;
    private String ville;
    @NotBlank
    @Max(value=50, message="La ville doit faire 50 caractères ou moins")
    @Column(name = "adresse_eni")
    private Boolean adresseEni;*/

    public Adresse() {
    }

    public Adresse(String rue, String codePostal, String ville) {
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public Long getId() {
        return NoAdresse;
    }

    public void setId(Long id) {
        this.NoAdresse = id;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return rue + " " + codePostal + " " + ville;
    }


	public Boolean getAddresse_eni() {
		return adresseEni;
	}

	public void setAddresse_eni(Boolean addresse_eni) {
		this.adresseEni = addresse_eni;
	}
}
