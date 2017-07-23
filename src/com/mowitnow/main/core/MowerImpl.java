package com.mowitnow.main.core;

import com.mowitnow.main.exceptions.InvalidOrientationException;
import com.mowitnow.main.exceptions.InvalidRotationException;
import com.mowitnow.main.exceptions.MowerCrashException;
import com.mowitnow.main.exceptions.MowerOutOfBoundsException;
import com.mowitnow.main.interfaces.ILawn;
import com.mowitnow.main.interfaces.IMower;
import com.mowitnow.main.interfaces.IOrientation;
import com.mowitnow.main.interfaces.IPosition;

/**
 *  Tondeuse à gazon.
 *  Classe métier représentative d'une tondeuse à gazon active sur une pelouse pré-définie.
 */
public class MowerImpl implements IMower {
    /**
     *  Pelouse rattachée.
     */
    private final ILawn lawn;

    /**
     *  Orientation de la tondeuse.
     */
    private final IOrientation orientation;

    /**
     *  Position de la tondeuse.
     */
    private IPosition position;

    /**
     *  Constructeur.
     *  @param lawn Pelouse d'appartenance.
     *  @param orientation Orientation initiale.
     *  @param x Position initiale sur l'axe horizontal.
     *  @param y Position initiale sur l'axe vertical.
     *  @throws MowerOutOfBoundsException En cas de position initiale invalide.
     *  @throws MowerCrashException En cas d'instanciation sur une position déjà occupée.
     *  @throws InvalidOrientationException En cas d'orientation initiale invalide.
     */
    public MowerImpl(ILawn lawn, char orientation, int x, int y) throws MowerOutOfBoundsException, MowerCrashException, InvalidOrientationException {
        this.position = new PositionImpl(x, y);
        if (!lawn.isInBounds(this.position)) {
            throw new MowerOutOfBoundsException(this.position);
        }
        if (lawn.isOccupied(this.position)) {
            throw new MowerCrashException(this.position);
        }

        this.lawn = lawn;
        this.orientation = new OrientationImpl(orientation);
    }

    /**
     *  Retourne la pelouse d'appartenance.
     *  @return Pelouse d'appartenance.
     */
    @Override
    public ILawn getLawn() {
        return this.lawn;
    }

    /**
     *  Retourne l'orientation.
     *  @return Orientation.
     */
    @Override
    public IOrientation getOrientation() {
        return this.orientation;
    }

    /**
     *  Retourne la position.
     *  @return Position.
     */
    @Override
    public IPosition getPosition() {
        return this.position;
    }

    /**
     *  Modifie l'orientation de la tondeuse.
     *  @param direction Direction de la rotation.
     *  @throws InvalidRotationException En cas de direction invalide.
     */
    @Override
    public void rotate(char direction) throws InvalidRotationException {
        this.orientation.rotate(direction);
    }

    /**
     *  Avance la tondeuse d'une case dans l'orientation courante.
     *  @throws MowerCrashException En cas de collisions entre deux tondeuses.
     */
    @Override
    public void push() throws MowerCrashException {
        /* Création de la position après avancement */
        IPosition advance = new PositionImpl(this.position.getX(), this.position.getY());

        /* Décalage */
        advance.push(this.orientation);

        /* Vérification de disponibilité */
        if (this.lawn.isOccupied(advance)) {
            throw new MowerCrashException(advance);
        }

        /* Vérification des nouvelles coordonnées et affectation */
        if (this.lawn.isInBounds(advance)) {
            this.position = advance;
        }
    }
}
