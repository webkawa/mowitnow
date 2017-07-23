package com.mowitnow.main.io;

import com.mowitnow.main.exceptions.InvalidSequenceException;
import com.mowitnow.main.exceptions.InvalidFileException;

import java.io.*;

/**
 *  Classe de lecture d'une séquence enregistrée dans un fichier.
 */
public class FileSequenceReader extends AbstractSequenceReader {
    /**
     *  Constructeur.
     *  @param path Chemin du fichier lu.
     *  @throws InvalidSequenceException En cas de séquence invalide.
     *  @throws InvalidFileException En cas de fichier source invalide.
     */
    public FileSequenceReader(String path) throws InvalidSequenceException, InvalidFileException {
        super(FileSequenceReader.readFile(path));
    }

    /**
     *  Réalise la lecture d'une séquence enregistrée dans un fichier.
     *  @param path Chemin du fichier.
     *  @return Séquence correspondante.
     */
    private static String readFile(String path) throws InvalidFileException {
        /* Chargement du fichier et vérifications initiales */
        File file = new File(path);
        if (!file.canRead()) {
            throw new InvalidFileException("Le fichier '%s' est inaccessible en lecture", file.getAbsolutePath());
        }

        /* Lecture */
        try {
            /* Création du lecteur */
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            try {
                /* Lecture */
                fis.read(data);
                fis.close();

                /* Renvoi */
                return new String(data, "UTF-8");
            } catch (IOException exception) {
                throw new InvalidFileException("Le fichier '%s' est illisible", file.getAbsolutePath());
            }
        } catch (FileNotFoundException exception) {
            throw new InvalidFileException(exception, file.getAbsolutePath());
        }
    }
}
