package com.mowitnow.main.interfaces;

import com.mowitnow.main.exceptions.InvalidRotationException;
import com.mowitnow.main.exceptions.MowerCrashException;

/**
 *  Interface descriptive d'une pelouse.
 */
public interface IMower {
    /**
     *  Retourne la pelouse d'appartenance.
     *  @return Pelouse d'appartenance.
     */
    ILawn getLawn();

    /**
     *  Retourne l'orientation de la tondeuse.
     *  @return Orientation de la tondeuse.
     */
    IOrientation getOrientation();

    /**
     *  Retourne la position de la tondeuse.
     *  @return Position de la tondeuse.
     */
    IPosition getPosition();

    /**
     *  Modifie l'orientation de la tondeuse.
     *  @param direction Direction de la rotation.
     *  @throws InvalidRotationException En cas de direction invalide.
     */
    void rotate(char direction) throws InvalidRotationException;

    /**
     *  Déplace la tondeuse dans la rotation courante.
     *  @throws MowerCrashException En cas de collisions entre deux tondeuses.
     */
    void push() throws MowerCrashException;
}
