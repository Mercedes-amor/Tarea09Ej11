package com.example.app.exceptions;

public class EmpleadoNotFoundException extends RuntimeException {

    public EmpleadoNotFoundException(Long id) {
        super("No se ha podido encontrar el empleado de ID: " + id);
    }
}
