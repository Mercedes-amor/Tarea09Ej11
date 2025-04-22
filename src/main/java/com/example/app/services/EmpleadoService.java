package com.example.app.services;

import java.util.List;

import com.example.app.domain.Empleado;
import com.example.app.domain.Genero;
import com.example.app.modelo.Usuario;

//La interface no la cambiamos
public interface EmpleadoService {
    List<Empleado> obtenerTodos();

    List<Empleado> obtenerPorSalarioMayor(Float salario);

    Empleado obtenerPorId(Long id);

    Empleado add(Empleado empleado);

    Empleado actualizar(Empleado empleado);

    void eliminarPorId(Long id);

    void eliminar(Empleado empleado);

    Empleado obtenerMaxIdEmpleado();

    List<Empleado> buscarPorNombre(String textoNombre);

    List<Empleado> buscarPorGenero(Genero genero);

    Usuario getUsuarioActual();
   

}
