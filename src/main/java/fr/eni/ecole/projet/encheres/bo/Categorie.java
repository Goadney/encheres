package fr.eni.ecole.projet.encheres.bo;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "categories")
public class Categorie implements Serializable {
	
	/**
	 * Identifiant de l'interface Serializable
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "no_categorie")
	private long id;
	
	@Column(name = "libelle", nullable = false)
	@NotBlank(message = "Le libellé est obligatoire")
	private String libelle;
	
	
	public Categorie() {
		
	}


	public Categorie(long id, @NotBlank(message = "Le libellé est obligatoire") String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Categorie [id=");
		builder.append(id);
		builder.append(", libelle=");
		builder.append(libelle);
		builder.append("]");
		return builder.toString();
	}
	

}
