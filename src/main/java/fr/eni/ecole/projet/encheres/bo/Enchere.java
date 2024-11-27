package fr.eni.ecole.projet.encheres.bo;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
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
	
	@EmbeddedId
	private EnchereId id;
	
	@Column(name = "date_enchere", nullable = false)
	@NotBlank(message = "La date de l'enchère est obligatoire")
	private LocalDateTime date;
	
	@ManyToOne
	@JoinColumn(name = "no_article", referencedColumnName = "no_article", insertable = false, updatable = false)
	private ArticleAVendre articleAVendre;
	
	@ManyToOne
	@JoinColumn(name = "id_utilisateur", referencedColumnName = "pseudo", insertable = false, updatable = false)
	private Utilisateur utilisateur;
	
	
	public Enchere() {

	}	


	public Enchere(EnchereId id, @NotBlank(message = "La date de l'enchère est obligatoire") LocalDateTime date,
			ArticleAVendre articleAVendre, Utilisateur utilisateur) {
		super();
		this.id = id;
		this.date = date;
		this.articleAVendre = articleAVendre;
		this.utilisateur = utilisateur;
	}


	public EnchereId getId() {
		return id;
	}


	public void setId(EnchereId id) {
		this.id = id;
	}


	public LocalDateTime getDate() {
		return date;
	}


	public void setDate(LocalDateTime date) {
		this.date = date;
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
		builder.append("Enchere [id=");
		builder.append(id);
		builder.append(", date=");
		builder.append(date);
		builder.append(", articleAVendre=");
		builder.append(articleAVendre);
		builder.append(", utilisateur=");
		builder.append(utilisateur);
		builder.append("]");
		return builder.toString();
	}


}
