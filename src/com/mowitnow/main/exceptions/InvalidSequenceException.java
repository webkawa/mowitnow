package com.mowitnow.main.exceptions;

/**
 *  Séquence invalide.
 *  Exception levée en cas d'utilisation d'une séquence d'entrée invalide.
 */
public class InvalidSequenceException extends AbstractException {
    /**
     *  Constructeur.
     *  @param sequence Séquence invalide.
     */
    public InvalidSequenceException(String sequence) {
        super("La séquence d'entrée '%s' est invalide", sequence);
    }
}
