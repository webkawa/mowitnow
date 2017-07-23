package com.mowitnow.main.program;

import com.mowitnow.main.exceptions.AbstractException;
import com.mowitnow.main.interfaces.ILawn;
import com.mowitnow.main.interfaces.IMower;
import com.mowitnow.main.io.AbstractSequenceReader;
import com.mowitnow.main.io.FileSequenceReader;
import com.mowitnow.main.io.StringSequenceReader;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 *  Programme console.
 *  Classe de lancement du programme en ligne de commande.
 *  Voir README.md pour le détail des arguments.
 */
public class Runner {
    /**
     *  Méthode de démarrage.
     *  @param args Liste des arguments.
     */
    public static void main(String[] args) {
        /* Création des paramètres par défaut */
        RunnerMode mode = RunnerMode.FILE;
        boolean verbose = false;

        /* Lecture des paramètres */
        for (int i = 0; i < args.length - 1; i++) {
            String arg = args[i];
            if ("-folder".equals(arg)) {
                mode = RunnerMode.FOLDER;
            }
            if ("-file".equals(arg)) {
                mode = RunnerMode.FILE;
            }
            if ("-raw".equals(arg)) {
                mode = RunnerMode.RAW;
            }
            if ("-verbose".equals(arg)) {
                verbose = true;
            }
        }
        String input = args[args.length - 1];

        /* Traitement */
        try {
            /* Chargement des séquences */
            Set<AbstractSequenceReader> readers = new HashSet<>();
            switch (mode) {
                case FOLDER:
                    if (verbose) {
                        System.out.println(String.format("Lecture du dossier '%s'", input));
                    }
                    for (File file : new File(input).listFiles()) {
                        System.out.println(String.format("Lecture du fichier '%s'", file.getAbsolutePath()));
                        readers.add(new FileSequenceReader(file.getAbsolutePath()));
                    }
                    break;
                case RAW:
                    if (verbose) {
                        System.out.println(String.format("Lecture d'une chaine de caractère brute"));
                    }
                    readers.add(new StringSequenceReader(input));
                    break;
                case FILE:
                default:
                    if (verbose) {
                        System.out.println(String.format("Lecture du fichier '%s'", input));
                    }
                    readers.add(new FileSequenceReader(input));
                    break;
            }

            /* Exécution */
            for (AbstractSequenceReader asr : readers) {
                if (verbose) {
                    System.out.println("INPUT");
                    System.out.println(asr.getSequence());
                }

                ILawn lawn = asr.execute();

                if (verbose) {
                    System.out.println("OUTPUT");
                }
                for (int i = 0; lawn.hasMower(i); i++) {
                    IMower mower = lawn.getMower(i);
                    System.out.print(String.format("%d %d %c", mower.getPosition().getX(), mower.getPosition().getY(), mower.getOrientation().getKey()));
                    if (lawn.hasMower(i + 1)) {
                        System.out.print("\n");
                    }
                }
            }
        } catch (AbstractException exception) {
            /* TODO : logs... */
            exception.printStackTrace();
        }
    }
}
