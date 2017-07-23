package com.mowitnow.main.exceptions;

import com.mowitnow.main.interfaces.IPosition;

/**
 *  Sortie de terrain.
 *  Erreur levée lorsqu'une tondeuse sort des limites de sa pelouse.
 */
public class MowerOutOfBoundsException extends AbstractException {
    /**
     *  Constructeur.
     *  @param position Position de la tondeuse.
     */
    public MowerOutOfBoundsException(IPosition position) {
        super("Les coordonnées %d:%d sortent des limites de la pelouse", position.getX(), position.getY());
    }
}
