package com.capgeticket.user.errors;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BadRequestException(String email) {
        super("Ya existe un usuario con el email: " + email);
    }

    public BadRequestException() {
        super("Revisa los datos, no son correctos");
    }
}
