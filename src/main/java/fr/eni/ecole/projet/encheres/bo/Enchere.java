package fr.eni.ecole.projet.encheres.bo;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "encheres")
public class Enchere implements Serializable {
	
	/**
	 * Identifiant de l'interface Serializable
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "date_enchere", nullable = false)
	@NotBlank(message = "La date de l'enchère est obligatoire")
	private LocalDateTime date;
	
	@Column(name = "montant_enchere", nullable = false)
	@NotBlank(message = "Le montant est obligatoire")
	private int montant;
	
	@ManyToOne
	@JoinColumn(name = "no_article", referencedColumnName = "no_article")
	private ArticleAVendre articleAVendre;
	
	@ManyToOne
	@JoinColumn(name = "id_utilisateur", referencedColumnName = "pseudo" )
	private Utilisateur utilisateur;
	
	
	public Enchere() {

	}	

	public Enchere(@NotBlank(message = "La date de l'enchère est obligatoire") LocalDateTime date,
			@NotBlank(message = "Le montant est obligatoire") int montant, ArticleAVendre articleAVendre,
			Utilisateur utilisateur) {
		super();
		this.date = date;
		this.montant = montant;
		this.articleAVendre = articleAVendre;
		this.utilisateur = utilisateur;
	}

	public LocalDateTime getDate() {
		return date;
	}


	public void setDate(LocalDateTime date) {
		this.date = date;
	}


	public int getMontant() {
		return montant;
	}


	public void setMontant(int montant) {
		this.montant = montant;
	}

	public ArticleAVendre getArticleAVendre() {
		return articleAVendre;
	}


	public void setArticleAVendre(ArticleAVendre articleAVendre) {
		this.articleAVendre = articleAVendre;
	}
	
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Enchere [date=");
		builder.append(date);
		builder.append(", montant=");
		builder.append(montant);
		builder.append(", articleAVendre=");
		builder.append(articleAVendre);
		builder.append(", utilisateur=");
		builder.append(utilisateur);
		builder.append("]");
		return builder.toString();
	}


}