package com.mowitnow.main.core;

import com.mowitnow.main.interfaces.IOrientation;
import com.mowitnow.main.interfaces.IPosition;

/**
 *  Position.
 *  Classe descriptive de la position d'une tondeuse sur (ou en dehors) d'une pelouse.
 */
public class PositionImpl implements IPosition {
    /**
     *  Position sur l'axe horizontal.
     */
    private int x;

    /**
     *  Position sur l'axe vertical.
     */
    private int y;

    /**
     *  Constructeur.
     *  @param x Position sur l'axe horizontal.
     *  @param y Position sur l'axe vertical.
     */
    public PositionImpl(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     *  Retourne la position sur l'axe horizontal.
     *  @return Position sur l'axe horizontal.
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     *  Retourne la position sur l'axe vertical.
     *  @return Position sur l'axe vertical.
     */
    @Override
    public int getY() {
        return this.y;
    }

    /**
     *  Décale la position dans une direction passée en paramètre.
     *  @param orientation Direction du déplacement.
     */
    @Override
    public void push(IOrientation orientation) {
        this.x += orientation.xDecal();
        this.y += orientation.yDecal();
    }
}
