package fr.eni.ecole.projet.encheres.bo;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "utilisateurs")
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pseudo", nullable = false, unique = true)
    @NotNull(message = "{pseudo.notnull}")
    @Size(max = 30, message = "{pseudo.size}")
    private String pseudo;

    @NotBlank(message = "{nom.notblank}")
    @Size(max = 40, message = "{nom.size}")
    private String nom;

    @NotBlank(message = "{prenom.notblank}")
    @Size(max = 50, message = "{prenom.size}")
    private String prenom;

    @Email(message = "{email.invalid}")
    @NotNull(message = "{email.notnull}")
    @Size(max = 100, message = "{email.size}")
    private String email;

    @Size(max = 15, message = "{telephone.size}")
    private String telephone;

    @Column(name = "mot_de_passe", nullable = false)
    private String motDePasse;

    private int credit;
    private boolean administrateur;
    
   /* @Id
    @Column(name = "pseudo", nullable = false, unique = true)
    @NotBlank
    @Max(value=30, message="Le pseudo doit faire 30 caractères ou moins")
    private String pseudo;
    @NotBlank
    @Max(value=40, message="Le pseudo doit faire 40 caractères ou moins")
    private String nom;
    @NotBlank
    @Max(value=50, message="Le pseudo doit faire 50 caractères ou moins")
    private String prenom;
    @NotBlank
    @Email
    @Max(value=100, message="Le pseudo doit faire 100 caractères ou moins")
    private String email;
    @Max(value=15, message="Le pseudo doit faire 15 caractères ou moins")
    private String telephone;
    @Max(value=68, message="Le pseudo doit faire 68 caractères ou moins")
    @Column(name = "mot_de_passe", nullable = false)
    private String motDePasse;
    private int credit;
    private boolean administrateur;*/

    //  one to one  avec addresse
    @OneToOne(cascade = CascadeType.ALL) // psi  on suppr l'user ca suppr l'adresse
    @JoinColumn(name = "no_adresse", referencedColumnName = "no_adresse")
    private Adresse adresse;

    public Utilisateur() {
    }

    public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone,
                       String motDePasse, int credit, boolean admin, Adresse adresse) {
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.motDePasse = motDePasse;
        this.credit = credit;
        this.administrateur = admin;
        this.adresse = adresse;
    }



    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean isAdmin() {
        return administrateur;
    }

    public void setAdmin(boolean admin) {
        this.administrateur = admin;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                ", pseudo='" + pseudo + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", credit=" + credit +
                ", admin=" + administrateur +
                ", adresse=" + adresse +
                '}';
    }

	public Utilisateur orElseThrow(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	public Utilisateur get() {
		return this;
	}
}
