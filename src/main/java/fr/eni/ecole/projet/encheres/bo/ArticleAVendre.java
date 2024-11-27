package fr.eni.ecole.projet.encheres.bo;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;



@Entity
@Table(name = "articles_a_vendre")
public class ArticleAVendre implements Serializable {
	
	/**
	 * Identifiant de l'interface Serializable
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "no_article")
    private long id;
	
	@Column(name = "nom_article", nullable = false)
	@NotBlank(message = "Le nom de l'article est obligatoire")
	private String nom;
	
	@Column(name = "description",nullable = false)
	@NotBlank(message = "La description est obligatoire")
	private String description;
	
	@Column(name = "date_debut_encheres",nullable = false)
	@NotBlank(message = "La date de début des enchères est obligatoire")
	private LocalDate dateDebutEncheres;
	
	@Column(name = "date_fin_encheres",nullable = false)
	@NotBlank(message = "La date de fin des enchères est obligatoire")
	private LocalDate dateFinEncheres;
	
	@Column(name = "statut_enchere",nullable = false)
	@NotBlank(message = "Le statut de l'enchère est obligatoire")
	private int statut;
	
	@Column(name = "prix_initial",nullable = false)
	@NotBlank(message = "Le prix initial est obligatoire")
	private int prixInitial;
	
	@Column(name = "prix_vente")
	private int prixVente;
	
	@ManyToOne
	@JoinColumn(name = "no_categorie", referencedColumnName = "no_categorie")
	private Categorie categorie;
	
	@ManyToOne
	@JoinColumn(name = "no_adresse_retrait", referencedColumnName = "no_adresse")
	private Adresse adresse;
	
	
	
	
	
	public ArticleAVendre() {

	}

	public ArticleAVendre(long id, @NotBlank(message = "Le nom de l'article est obligatoire") String nom,
			@NotBlank(message = "La description est obligatoire") String description,
			@NotBlank(message = "La date de début des enchères est obligatoire") LocalDate dateDebutEncheres,
			@NotBlank(message = "La date de fin des enchères est obligatoire") LocalDate dateFinEncheres,
			@NotBlank(message = "Le statut de l'enchère est obligatoire") int statut,
			@NotBlank(message = "Le prix initial est obligatoire") int prixInitial, int prixVente,
			Categorie categorie) {
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.statut = statut;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.categorie = categorie;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}


	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}


	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}


	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}


	public int getStatut() {
		return statut;
	}


	public void setStatut(int statut) {
		this.statut = statut;
	}


	public int getPrixInitial() {
		return prixInitial;
	}


	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}


	public int getPrixVente() {
		return prixVente;
	}


	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ArticleAVendre [id=");
		builder.append(id);
		builder.append(", nom=");
		builder.append(nom);
		builder.append(", description=");
		builder.append(description);
		builder.append(", dateDebutEncheres=");
		builder.append(dateDebutEncheres);
		builder.append(", dateFinEncheres=");
		builder.append(dateFinEncheres);
		builder.append(", statut=");
		builder.append(statut);
		builder.append(", prixInitial=");
		builder.append(prixInitial);
		builder.append(", prixVente=");
		builder.append(prixVente);
		builder.append(", categorie=");
		builder.append(categorie);
		builder.append("]");
		return builder.toString();
	}


}
