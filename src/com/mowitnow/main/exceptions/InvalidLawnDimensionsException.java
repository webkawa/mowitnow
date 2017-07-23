package com.mowitnow.main.exceptions;

/**
 *  Dimensions de la pelouse invalide.
 *  Erreur levée lors de l'utilisation d'un lot de dimensions invalide pour une pelouse.
 */
public class InvalidLawnDimensionsException extends AbstractException {
    /**
     *  Constructeur.
     *  @param width Largeur de la pelouse.
     *  @param height Hauteur de la pelouse.
     */
    public InvalidLawnDimensionsException(int width, int height) {
        super("Dimensions de la pelouse invalide (%d:%d)", width, height);
    }
}
