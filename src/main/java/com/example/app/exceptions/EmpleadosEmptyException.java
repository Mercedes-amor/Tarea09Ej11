package com.example.app.exceptions;

public class EmpleadosEmptyException extends RuntimeException {

    public EmpleadosEmptyException() {
        super("No hay empleados en el sistema");
    }
}
