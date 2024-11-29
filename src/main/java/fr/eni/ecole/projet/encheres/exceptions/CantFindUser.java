package fr.eni.ecole.projet.encheres.exceptions;

public class CantFindUser extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public CantFindUser(String message) {
        super(message);
    }
}
