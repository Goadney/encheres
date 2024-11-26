package fr.eni.ecole.projet.encheres.bo;

import java.io.Serializable;
import java.time.LocalDate;

public class ArticleAVendre implements Serializable {
	
	/**
	 * Identifiant de l'interface Serializable
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String nom;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int statut;
	private int prixInitial;
	private int prixVente;
	
	
	public ArticleAVendre() {

	}


	public ArticleAVendre(long id, String nom, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int statut, int prixInitial, int prixVente) {
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.statut = statut;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
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
		builder.append("]");
		return builder.toString();
	}	

}
