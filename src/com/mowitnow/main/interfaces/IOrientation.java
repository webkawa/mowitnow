package com.mowitnow.main.interfaces;

import com.mowitnow.main.exceptions.InvalidRotationException;

/**
 *  Interface descriptive de l'orientation d'une tondeuse.
 */
public interface IOrientation {
    /**
     *  Retourne la clef descriptive de l'orientation.
     *  @return Clef descriptive.
     */
    char getKey();

    /**
     *  Exécute une opération de rotation.
     *  @param direction Clef descriptive de la direction de rotation.
     *  @throws InvalidRotationException En cas de rotation invalide.
     */
    void rotate(char direction) throws InvalidRotationException;

    /**
     *  Retourne le nombre de cellules de décalage sur l'axe horizontal lors d'un déplacement dans l'orientation
     *  courante.
     *  @return Nombre de cellules de décalage horizontal.
     */
    int xDecal();

    /**
     *  Retourne le nombre de cellules de décalage sur l'axe vertical lors d'un déplacement dans l'orientation
     *  courante.
     *  @return Nombre de cellules de déclage vertical.
     */
    int yDecal();
}
