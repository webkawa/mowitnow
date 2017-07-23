package com.mowitnow.main.io;

import com.mowitnow.main.core.LawnImpl;
import com.mowitnow.main.exceptions.*;
import com.mowitnow.main.interfaces.ILawn;
import com.mowitnow.main.interfaces.IMower;

/**
 *  Utilitaire de lecture.
 *  Classe représentative d'un utilitaire permettant la lecture d'une séquence formatée selon la norme
 *  définie dans les spécifications.
 */
public abstract class AbstractSequenceReader {
    /**
     *  Validateur.
     *  Expression régulière permettant la validation d'une séquence d'entrée.
     */
    private static final String VALIDATOR = "(\\d \\d)\\n(\\d \\d [NEWS]\\n[GAD]*\\n)*(\\d \\d [NEWS]\\n[GAD]*)";

    /**
     *  Séquence traitée.
     */
    private final String sequence;

    /**
     *  Constructeur.
     *  @throws InvalidSequenceException En cas de séquence invalide.
     */
    public AbstractSequenceReader(String sequence) throws InvalidSequenceException {
        if (!sequence.matches(AbstractSequenceReader.VALIDATOR)) {
            throw new InvalidSequenceException(sequence);
        }
        this.sequence = sequence;
    }

    /**
     *  Retourne la séquence.
     *  @return Séquence.
     */
    public String getSequence() {
        return this.sequence;
    }

    /**
     *  Exécution.
     *  Exécute la séquence décrite par l'objet courant et retourne la pelouse après traitement.
     *  @return Pelouse après traitement.
     *  @throws InvalidLawnDimensionsException En cas de dimensionnement invalide.
     *  @throws MowerOutOfBoundsException En cas d'initialisation d'une tondeuse en dehors des limites.
     *  @throws MowerCrashException En cas de collision entre deux tondeuses.
     *  @throws InvalidOrientationException En cas d'orientation initiale invalide d'une tondeuse.
     *  @throws InvalidRotationException En cas de rotation invalide.
     */
    public ILawn execute() throws InvalidLawnDimensionsException, MowerOutOfBoundsException, MowerCrashException, InvalidOrientationException, InvalidRotationException {
        /* Validation */
        /* Création des variables utiles */
        ILawn lawn = null;
        IMower mower = null;

        /* Lecture ligne à ligne
        *  A partir de ce point, le contenu de la chaine d'entrée est considéré comme fiable en raison de la validation
        *  initiale. En conséquence, les opérations de conversion (en entier, caractère...) ne sont pas testées. */
        String[] lines = this.sequence.split("\n");
        for (int i = 0; i < lines.length; i++) {
            /* Extraction de la ligne */
            String line = lines[i];

            if (i == 0) {
                /* Création de la pelouse */
                String[] dimensions = line.split(" ");
                lawn = new LawnImpl(
                        Integer.parseInt(dimensions[0]) + 1,
                        Integer.parseInt(dimensions[1]));
            } else if (i % 2 == 1) {
                /* Création d'une tondeuse */
                String[] parameters = line.split(" ");
                mower = lawn.addMower(
                        parameters[2].charAt(0),
                        Integer.parseInt(parameters[0]),
                        Integer.parseInt(parameters[1]));
            } else {
                /* Déplacement */
                for (char action : line.toCharArray()) {
                    if (action == 'A') {
                        mower.push();
                    } else {
                        mower.rotate(action);
                    }
                }
            }
        }

        /* Renvoi */
        return lawn;
    }
}
