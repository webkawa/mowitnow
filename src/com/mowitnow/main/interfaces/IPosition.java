package com.mowitnow.main.interfaces;

/**
 *  Interface descriptive de la position d'une tondeuse sur une pelouse.
 */
public interface IPosition {
    /**
     *  Retourne la position sur l'axe horizontal.
     *  0 pour le point situé à gauche, plus pour un décalage vers la droite.
     *  @return Position sur l'axe horizontal.
     */
    int getX();

    /**
     *  Retourne la position sur l'axe vertical.
     *  0 pour le point situé en bas, plus pour un décalage vers le haut.
     *  @return Position sur l'axe vertical.
     */
    int getY();

    /**
     *  Décale la position d'une case dans une direction passée en paramètre.
     *  @param orientation Direction du déplacement.
     */
    void push(IOrientation orientation);
}
