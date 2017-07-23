package com.mowitnow.main.exceptions;

import com.mowitnow.main.interfaces.IPosition;

/**
 *  Collision de tondeuses.
 *  Exception levée lors d'une collision entre deux tondeuses.
 */
public class MowerCrashException extends AbstractException {
    /**
     *  Constructeur.
     *  @param position Position de la collision.
     */
    public MowerCrashException(IPosition position) {
        super("Collision entre deux tondeuses à la position %d:%d", position.getX(), position.getY());
    }
}
