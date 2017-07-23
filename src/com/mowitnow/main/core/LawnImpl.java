package com.mowitnow.main.core;

import com.mowitnow.main.exceptions.InvalidLawnDimensionsException;
import com.mowitnow.main.exceptions.InvalidOrientationException;
import com.mowitnow.main.exceptions.MowerCrashException;
import com.mowitnow.main.exceptions.MowerOutOfBoundsException;
import com.mowitnow.main.interfaces.ILawn;
import com.mowitnow.main.interfaces.IMower;
import com.mowitnow.main.interfaces.IPosition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *  Pelouse.
 *  Terrain rectangulaire pouvant accueillir une ou plusieurs tondeuses à gazon.
 */
public class LawnImpl implements ILawn {
    /**
     *  Liste des tondeuses.
     */
    private final List<IMower> mowers;

    /**
     *  Largeur de la pelouse.
     */
    private final int width;

    /**
     *  Hauteur de la pelouse.
     */
    private final int height;

    /**
     *  Constructeur.
     *  @param width Largeur de la pelouse.
     *  @param height Hauteur de la pelouse.
     *  @throws InvalidLawnDimensionsException En cas de dimensions invalides.
     */
    public LawnImpl(int width, int height) throws InvalidLawnDimensionsException {
        if (width < 1 || height < 1) {
            throw new InvalidLawnDimensionsException(width, height);
        }
        this.mowers = new ArrayList<>();
        this.width = width;
        this.height = height;
    }

    /**
     *  Retourne la largeur de la pelouse.
     *  @return Largeur de la pelouse.
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     *  Retourne la hauteur de la pelouse.
     *  @return Hauteur de la pelouse.
     */
    @Override
    public int getHeight() {
        return this.height;
    }

    /**
     *  Indique si la pelouse contient une tondeuse à un index donné.
     *  @param idx Index recherché.
     *  @return true si la pelouse contient la tondeuse, false sinon.
     */
    @Override
    public boolean hasMower(int idx) {
        return idx >= 0 && idx < this.mowers.size();
    }

    /**
     *  Retourne la tondeuse située à une position passée en paramètre.
     *  @param idx Index recherché.
     *  @return Tondeuse correspondante.
     */
    @Override
    public IMower getMower(int idx) {
        return this.mowers.get(idx);
    }

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
    @Override
    public IMower addMower(char orientation, int x, int y) throws MowerOutOfBoundsException, MowerCrashException, InvalidOrientationException {
        IMower mower = new MowerImpl(this, orientation, x, y);
        this.mowers.add(mower);
        return mower;
    }

    /**
     *  Indique si une position se situe dans les limites tracées par la pelouse.
     *  @param position Position évaluée.
     *  @return true si la position se situe dans les limites de la pelouse, false sinon.
     */
    @Override
    public boolean isInBounds(IPosition position) {
        return position.getX() >= 0 && position.getX() < this.width && position.getY() >= 0 && position.getY() < this.height;
    }

    /**
     *  Indique si une position sur la pelouse est couramment occupée par une tondeuse.
     *  @param position Position évaluée.
     *  @return true si la position est occupée, false sinon.
     */
    @Override
    public boolean isOccupied(IPosition position) {
        for (IMower mower : this.mowers) {
            if (mower.getPosition().getX() == position.getX() && mower.getPosition().getY() == position.getY()) {
                return true;
            }
        }
        return false;
    }
}
