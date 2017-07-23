package com.mowitnow.test;

/**
 *  Position attendue.
 *  Classe représentative de la position unitaire d'une tondeuse, attendue à l'issue d'un test unitaire.
 */
public class MowerTestingExpectation {
    /**
     *  Position sur l'axe horizontal.
     */
    private final int x;

    /**
     *  Position sur l'axe vertical.
     */
    private final int y;

    /**
     *  Orientation.
     */
    private final char orientation;

    /**
     *  Constructeur.
     *  @param x Position sur l'axe horizontal.
     *  @param y Position sur l'axe vertical.
     *  @param orientation Orientation de la tondeuse.
     */
    MowerTestingExpectation(int x, int y, char orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    /**
     *  Retourne la position sur l'axe horizontal.
     *  @return Position sur l'axe horizontal.
     */
    public int getX() {
        return this.x;
    }

    /**
     *  Retourne la position sur l'axe vertical.
     *  @return Position sur l'axe vertical.
     */
    public int getY() {
        return this.y;
    }

    /**
     *  Retourne l'orientation.
     *  @return Orientation.
     */
    public char getOrientation() {
        return this.orientation;
    }
}
