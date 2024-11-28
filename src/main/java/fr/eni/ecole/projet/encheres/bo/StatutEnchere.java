package fr.eni.ecole.projet.encheres.bo;

public enum StatutEnchere {
	
	CREEE("Créée"),
	EN_COURS("En cours"),
	ENCHERES_TERMINEES("Enchères terminées"),
	RETRAIT("Retrait effectué"),
	ANNULE("Enchère annulée");
	
	private final String label;
	
	StatutEnchere(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}

}
