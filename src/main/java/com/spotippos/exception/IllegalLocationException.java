package com.spotippos.exception;

/**
 * Exceção de localização inválida (quando excede o limite das provincias).
 * 
 * @author Felipe
 *
 */
public class IllegalLocationException extends Exception {

    private static final long serialVersionUID = 8093584122359254594L;

    public IllegalLocationException(String massege) {
        super(massege);
    }

}
