package com.mowitnow.main.exceptions;

/**
 *  Erreur de rotation.
 *  Erreur levée suite à l'utilisation d'un paramètre invalide pour décrire la rotation d'une tondeuse.
 */
public class InvalidRotationException extends AbstractException {
    /**
     *  Constructeur.
     *  @param direction Paramètre utilisé.
     */
    public InvalidRotationException(char direction) {
        super("Le paramètre '%c' n'est pas compatible avec une opération de rotation... les valeurs acceptées sont 'G' et 'D'", direction);
    }
}
