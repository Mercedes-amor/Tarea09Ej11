package com.example.app.exceptions;

public class NoPermitidoException extends RuntimeException {

    public NoPermitidoException() {
        super("No puedes modificar este Empleado, no lo  has creado tú");
    }
}
