package com.mowitnow.test;

import com.mowitnow.main.exceptions.*;
import com.mowitnow.main.interfaces.ILawn;
import com.mowitnow.main.interfaces.IMower;
import com.mowitnow.main.io.FileSequenceReader;
import org.junit.Assert;
import org.junit.Test;

/**
 *  Classe principale des tests unitaires.
 */
public class MowerTesting {
    /**
     *  Test de la séquence valide 1.
     *  Exemple fourni avec les spécifications.
     *  @throws AbstractException En cas d'erreur lors d'un des traitements.
     */
    @Test
    public void testValidSequence01() throws AbstractException {
        Assert.assertTrue(this.testFile(
                "./resources/valid-case-01",
                new MowerTestingExpectation(1, 3, 'N'),
                new MowerTestingExpectation(5, 1, 'E')));
    }

    /**
     *  Test de la séquence valide 2.
     *  Mouvement simple d'une tondeuse vers le nord avec dépassement.
     *  @throws AbstractException En cas d'erreur lors des traitements.
     */
    @Test
    public void testValidSequence02() throws AbstractException {
        Assert.assertTrue(this.testFile(
                "./resources/valid-case-02",
                new MowerTestingExpectation(0, 2, 'N')));
    }

    /**
     *  Test de la séquence valide 3.
     *  Tour complet d'une tondeuse autour d'une pelouse carrée de coté 3 (sens horaire).
     *  @throws AbstractException En cas d'erreur lors des traitements.
     */
    @Test
    public void testValidSequence03() throws AbstractException {
        Assert.assertTrue(this.testFile(
                "./resources/valid-case-03",
                new MowerTestingExpectation(0, 0, 'W')));
    }

    /**
     *  Test de la séquence valide 4.
     *  Tour complet d'une tondeuse autour d'une pelouse carrée de coté 3 (sens anti-horaire).
     *  @throws AbstractException En cas d'erreur lors des traitements.
     */
    @Test
    public void testValidSequence04() throws AbstractException {
        Assert.assertTrue(this.testFile(
                "./resources/valid-case-04",
                new MowerTestingExpectation(0, 0, 'S')));
    }

    /**
     *  Test de la séquence valide 5.
     *  Tour complet d'une tondeuse autour d'une pelouse carrée de coté 5 (sens horaire).
     *  Traversée de la meme pelouse en diagonale par une seconde tondeuse.
     *  @throws AbstractException En cas d'erreur lors des traitements.
     */
    @Test
    public void testValidSequence05() throws AbstractException {
        Assert.assertTrue(this.testFile(
                "./resources/valid-case-05",
                new MowerTestingExpectation(5, 0, 'S'),
                new MowerTestingExpectation(5, 5, 'E')));
    }

    /**
     *  Test de la séquence valide 6.
     *  Quatre tondeuses suivant les cotés d'une pelouse carrée de taille 3.
     *  @throws AbstractException En cas d'erreur lors des traitements.
     */
    @Test
    public void testValidSequence06() throws AbstractException {
        Assert.assertTrue(this.testFile(
                "./resources/valid-case-06",
                new MowerTestingExpectation(0, 3, 'N'),
                new MowerTestingExpectation(3, 3, 'E'),
                new MowerTestingExpectation(3, 0, 'S'),
                new MowerTestingExpectation(0, 0, 'W')));
    }

    /**
     *  Test de la séquence invalide 1.
     *  Chaine de caractères aléatoire.
     *  @throws AbstractException En cas d'erreur lors des traitements.
     */
    @Test(expected = InvalidSequenceException.class)
    public void testInvalidSequence01() throws AbstractException {
        this.testFile("./resources/invalid-case-01");
    }

    /**
     *  Test de la séquence invalide 2.
     *  Information manquante.
     *  @throws AbstractException En cas d'erreur lors des traitements.
     */
    @Test(expected = InvalidSequenceException.class)
    public void testInvalidSequence02() throws AbstractException {
        this.testFile("./resources/invalid-case-02");
    }

    /**
     *  Test de la séquence invalide 3.
     *  Action invalide.
     *  @throws AbstractException En cas d'erreur lors des traitements.
     */
    @Test(expected = InvalidSequenceException.class)
    public void testInvalidSequence03() throws AbstractException {
        this.testFile("./resources/invalid-case-03");
    }

    /**
     *  Test d'un fichier manquant.
     *  @throws AbstractException En cas d'erreur lors des traitements.
     */
    @Test(expected = InvalidFileException.class)
    public void testMissingFile() throws AbstractException {
        this.testFile("./foo");
    }

    /**
     *  Test d'une collision entre deux tondeuses 1.
     *  Sur position initiale.
     *  @throws AbstractException En cas d'erreur lors des traitements.
     */
    @Test(expected = MowerCrashException.class)
    public void testCrashSequence01() throws AbstractException {
        this.testFile("./resources/crash-case-01");
    }

    /**
     *  Test d'une collision entre deux tondeuses 2.
     *  Après déplacement.
     *  @throws AbstractException En cas d'erreur lors des traitements.
     */
    @Test(expected = MowerCrashException.class)
    public void testCrashSequence02() throws AbstractException {
        this.testFile("./resources/crash-case-02");
    }

    /**
     *  Charge un fichier et vérifie que le résultat correspond à une liste d'attentes passées en paramètre.
     *  @param path Chemin du fichier.
     *  @param expectations Liste des attentes.
     *  @return true si le fichier valide les attentes, false sinon.
     *  @throws AbstractException En cas d'erreur lors du traitement.
     */
    private boolean testFile(String path, MowerTestingExpectation... expectations) throws AbstractException {
        FileSequenceReader fsr = new FileSequenceReader(path);
        ILawn lawn = fsr.execute();
        return this.meetExpectations(lawn, expectations);
    }

    /**
     *  Indique si l'état final d'une pelouse correspond aux attentes stipulées par un test unitaire.
     *  @param lawn Pelouse après traitement.
     *  @param expectations Liste des attentes.
     *  @return true si la pelouse correspond aux attentes, false sinon.
     */
    private boolean meetExpectations(ILawn lawn, MowerTestingExpectation... expectations) {
        for (int i = 0; i < expectations.length; i++) {
            /* Vérification d'existence */
            if (!lawn.hasMower(i)) {
                return false;
            }

            /* Récupération de l'attente */
            MowerTestingExpectation expectation = expectations[i];

            /* Vérification de la position */
            IMower mower = lawn.getMower(i);
            if (mower.getPosition().getX() != expectation.getX() || mower.getPosition().getY() != expectation.getY() || mower.getOrientation().getKey() != expectation.getOrientation()) {
                return false;
            }
        }
        return true;
    }
}
