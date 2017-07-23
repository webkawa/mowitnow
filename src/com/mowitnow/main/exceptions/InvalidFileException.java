package com.mowitnow.main.exceptions;

/**
 *  Fichier source invalide.
 *  Erreur levée en cas de chargement d'un fichier de données invalide.
 */
public class InvalidFileException extends AbstractException {
    /**
     *  Constructeur simple.
     *  @param message Message d'erreur.
     *  @param format Arguments de formatage.
     */
    public InvalidFileException(String message, Object... format) {
        super(message, format);
    }

    /**
     *  Constructeur à charge.
     *  @param cause Cause de l'erreur.
     *  @param message Message d'erreur.
     *  @param format Arguments de formatage.
     */
    public InvalidFileException(Throwable cause, String message, Object... format) {
        super(cause, message, format);
    }
}
