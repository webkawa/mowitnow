package com.mowitnow.main.exceptions;

/**
 *  Exception.
 *  Erreur généraliste survenant dans le cadre de l'exécution du programme.
 */
public abstract class AbstractException extends Exception {
    /**
     *  Constructeur simple.
     *  @param message Message d'erreur.
     *  @param format Arguments de formatage.
     */
    public AbstractException(String message, Object... format) {
        super(String.format(message, format));
    }

    /**
     *  Constructeur à cause.
     *  @param cause Cause de l'erreur.
     *  @param message Message d'erreur.
     *  @param format Arguments de formatage.
     */
    public AbstractException(Throwable cause, String message, Object... format) {
        super(String.format(message, format), cause);
    }
}
