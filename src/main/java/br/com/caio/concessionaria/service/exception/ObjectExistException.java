package br.com.caio.concessionaria.service.exception;

public class ObjectExistException extends RuntimeException{
    private static final long serialVersionUID = -5203737031550322208L;

    public ObjectExistException(String message) {
        super(message);
    }

    public ObjectExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
