package com.mowitnow.main.exceptions;

/**
 *  OrientationImpl invalide.
 *  Erreur levée en cas d'utilisation d'un caractère invalide en description d'une orientation.
 */
public class InvalidOrientationException extends AbstractException {
    /**
     *  Constructeur.
     *  @param key Clef à l'origine du problème.
     */
    public InvalidOrientationException(char key) {
        super("Le caractère '%c' ne correspond pas à une orientation... les valeurs acceptées sont : 'N', 'E', 'W' et 'S'", key);
    }
}
