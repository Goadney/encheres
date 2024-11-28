package fr.eni.ecole.projet.encheres.exceptions;

public class InvalidPasswordException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public InvalidPasswordException(String message) {
        super(message);
    }
}
