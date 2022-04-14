package br.com.ufg.tcc.medicamentos.common;

public class ResourceNotFoundException extends RuntimeException{


    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException() {

    }
}
