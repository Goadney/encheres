package fr.eni.ecole.projet.encheres.bo;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EnchereId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "montant_enchere")
	private int montant;
	
	@Column(name = "no_article")
	private int noArticle;
	
	@Column(name = "id_utilisateur")
	private String idUtilisateur;

	public EnchereId() {

	}

	public EnchereId(int montant, int noArticle, String idUtilisateur) {
		this.montant = montant;
		this.noArticle = noArticle;
		this.idUtilisateur = idUtilisateur;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(String idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	
	  @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        EnchereId enchereId = (EnchereId) o;
	        return Objects.equals(getIdUtilisateur(), enchereId.getIdUtilisateur()) &&
	               Objects.equals(getNoArticle(), enchereId.getNoArticle());
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(getIdUtilisateur(), getNoArticle());
	    }
	

}
