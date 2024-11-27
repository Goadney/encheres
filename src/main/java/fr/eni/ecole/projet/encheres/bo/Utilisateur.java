package fr.eni.ecole.projet.encheres.bo;

import jakarta.persistence.*;

@Entity
@Table(name = "utilisateurs")
public class Utilisateur {

    @Id
    @Column(name = "pseudo", nullable = false, unique = true)
    private String pseudo;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    @Column(name = "mot_de_passe", nullable = false)
    private String motDePasse;
    private int credit;
    private boolean administrateur;

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
