package fr.eni.ecole.projet.encheres.bo;

import jakarta.persistence.*;

@Entity
@Table(name = "adresses")
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long no_adresse;

    private String rue;
    private String codePostal;
    private String ville;

    public Adresse() {
    }

    public Adresse(String rue, String codePostal, String ville) {
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public Long getId() {
        return no_adresse;
    }

    public void setId(Long id) {
        this.no_adresse = id;
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
        return "Adresse{" +
                "id=" + no_adresse +
                ", rue='" + rue + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }
}
