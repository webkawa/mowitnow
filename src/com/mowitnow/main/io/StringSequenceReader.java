package com.mowitnow.main.io;

import com.mowitnow.main.exceptions.InvalidSequenceException;

/**
 *  Classe de lecture d'une chaine d'entrée passée directement en paramètre.
 */
public class StringSequenceReader extends AbstractSequenceReader {
    /**
     *  Constructeur.
     *  @param sequence Séquence brute.
     *  @throws InvalidSequenceException En cas de séquence invalide.
     */
    public StringSequenceReader(String sequence) throws InvalidSequenceException {
        super(sequence);
    }
}
