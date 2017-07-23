package com.mowitnow.main.interfaces;

import com.mowitnow.main.exceptions.InvalidOrientationException;
import com.mowitnow.main.exceptions.MowerCrashException;
import com.mowitnow.main.exceptions.MowerOutOfBoundsException;

/**
 *  Interface descriptive d'une pelouse.
 */
public interface ILawn {
    /**
     *  Retourne la largeur de la pelouse.
     *  @return Largeur de la pelouse.
     */
    int getWidth();

    /**
     *  Retourne la hauteur de la pelouse.
     *  @return Hauteur de la pelouse.
     */
    int getHeight();

    /**
     *  Indique si la pelouse accueille une tondeuse à un index donné.
     *  @param idx Index recherché.
     *  @return true si la pelouse contient une tondeuse à l'index stipulé, false sinon.
     */
    boolean hasMower(int idx);

    /**
     *  Retourne la tondeuse située à une position passée en paramètre.
     *  @param idx Index recherché.
     *  @return Tondeuse correspondante.
     */
    IMower getMower(int idx);

    /**
     *  Ajoute et retourne une tondeuse située aux coordonnées passées en paramètres.
     *  @param orientation Orientation initiale de la tondeuse.
     *  @param x Position initiale sur l'axe horizontal.
     *  @param y Position initiale sur l'axe vertical.
     *  @return Tondeuse ajoutée.
     *  @throws MowerOutOfBoundsException En cas de position initiale invalide.
     *  @throws MowerCrashException En cas d'instanciation sur une position déjà occupée.
     *  @throws InvalidOrientationException En cas d'orientation initiale invalide.
     */
    IMower addMower(char orientation, int x, int y) throws MowerOutOfBoundsException, MowerCrashException, InvalidOrientationException;

    /**
     *  Indique si une position se situe dans les limites tracées par la pelouse.
     *  @param position Position évaluée.
     *  @return true si la position se situe dans les limites de la pelouse, false sinon.
     */
    boolean isInBounds(IPosition position);

    /**
     *  Indique si une position sur la pelouse est couramment occupée.
     *  @param position Position évaluée.
     *  @return true si la position est occupée, false sinon.
     */
    boolean isOccupied(IPosition position);
}
