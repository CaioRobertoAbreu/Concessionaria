package br.com.caio.concessionaria.service.exception;

public class ObjectNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 6658756276525352035L;

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
