package com.mowitnow.main.core;

import com.mowitnow.main.exceptions.InvalidOrientationException;
import com.mowitnow.main.exceptions.InvalidRotationException;
import com.mowitnow.main.interfaces.IOrientation;

/**
 *  OrientationImpl.
 *  Classe descriptive de l'orientation d'une tondeuse à gazon. Basée sur les cardinalités anglo-saxones : "north",
 *  "est", "west" et "south".
 */
public class OrientationImpl implements IOrientation {
    /**
     *  Liste des caractères descriptifs d'une orientation.
     *  Les entrées doivent etre classées par ordre de rotation horaire.
     */
    private static final char[] VALID_KEYS = new char[] {'N', 'E', 'S', 'W'};

    /**
     *  Caractère rattaché à l'orientation (N, E, W ou S).
     */
    private char key;

    /**
     *  Constructeur.
     *  @param key Caractère rattaché à l'orientation.
     *  @throws InvalidOrientationException En cas de paramètre invalide.
     */
    public OrientationImpl(char key) throws InvalidOrientationException {
        if (this.getKeyIndex(key) == -1) {
            throw new InvalidOrientationException(key);
        }
        this.key = key;
    }

    /**
     *  Retourne le caractère rattaché à l'orientation.
     *  @return Caractère rattaché à l'orientation.
     */
    @Override
    public char getKey() {
        return this.key;
    }

    /**
     *  Exécute une opération de rotation sur l'orientation courante.
     *  @param direction Direction de la rotation.
     *  @throws InvalidRotationException En cas de direction invalide.
     */
    @Override
    public void rotate(char direction) throws InvalidRotationException {
        switch (direction) {
            case 'G':
                this.key = this.getSiblingKey(false);
                break;
            case 'D':
                this.key = this.getSiblingKey(true);
                break;
            default:
                throw new InvalidRotationException(direction);
        }
    }

    /**
     *  Retourne le nombre de cellules de décalage sur l'axe horizontal.
     *  @return Nombre de cellules de décalage sur l'axe horizontal.
     */
    @Override
    public int xDecal() {
        switch (this.key) {
            case 'E':
                return 1;
            case 'W':
                return -1;
            default:
                return 0;
        }
    }

    /**
     *  Retourne le nombre de cellules de décalage sur l'axe vertical.
     *  @return Nombre de cellules de décalage sur l'axe vertical.
     */
    @Override
    public int yDecal() {
        switch (this.key) {
            case 'N':
                return 1;
            case 'S':
                return -1;
            default:
                return 0;
        }
    }

    /**
     *  Recherche et retourne la position d'un caractère descriptif d'une orientation dans la liste ordonnée des valeurs
     *  disponibles.
     *  @param key Caractère descriptif.
     *  @return Position du caractère ou -1 si absent de la liste.
     */
    private int getKeyIndex(char key) {
        for (int i = 0; i < OrientationImpl.VALID_KEYS.length; i++) {
            if (OrientationImpl.VALID_KEYS[i] == key) {
                return i;
            }
        }
        return -1;
    }

    /**
     *  Recherche et retourne le caractère descriptif de l'orientation courante après une rotation horaire ou
     *  anti-horaire.
     *  @param direction Direction de la rotation.
     *                   Si true, indique une rotation horaire ; false pour anti-horaire.
     *  @return Caractère descriptif de l'orientation après rotation.
     */
    private char getSiblingKey(boolean direction) {
        /* Recherche de l'index courant */
        int index = this.getKeyIndex(this.key);

        /* Mise à jour */
        index += direction ? 1 : -1;

        /* Vérification du dépassement */
        if (index >= OrientationImpl.VALID_KEYS.length) {
            index = 0;
        }
        if (index < 0) {
            index = OrientationImpl.VALID_KEYS.length - 1;
        }

        /* Renvoi */
        return OrientationImpl.VALID_KEYS[index];
    }
}
