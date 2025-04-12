package com.example.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.app.domain.Empleado;
import com.example.app.domain.Genero;
import com.example.app.exceptions.EmpleadoNotFoundException;
import com.example.app.exceptions.EmpleadosEmptyException;
import com.example.app.repositories.EmpleadoRepository;

@Service
@Primary
public class EmpleadoServiceImplBd implements EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    public List<Empleado> obtenerTodos() {
        List<Empleado> listaEmpleados = empleadoRepository.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
        if (listaEmpleados.isEmpty())
            throw new EmpleadosEmptyException();
        return listaEmpleados;
    }

    public List<Empleado> obtenerPorSalarioMayor(Float salario) {
        return empleadoRepository.findEmpleadosConSalarioMayorIgual(salario);
    }

    public Empleado obtenerMaxIdEmpleado() {
        return empleadoRepository.obtenerMaxIdEmpleado();
    }

    public Empleado obtenerPorId(Long id) {
        return empleadoRepository.findById(id).orElseThrow(() -> new EmpleadoNotFoundException(id));
    }

    public Empleado add(Empleado empleado) {

        // Solamente debemos cambiar el .add por .save

        // Lanzamos excepción si el salario es inferior a 1800
        if (empleado.getSalario() < 18000) {
            throw new RuntimeException("El salario no puede ser inferior a 18.000€");
        }

        return empleadoRepository.save(empleado); // .save ya nos devuelve un empleado
    }

    public Empleado actualizar(Empleado empleado) {
        // Comprobación de que el salario introducido no es menor a 18.000€,
        // Si es así lanza una excepción
        if (empleado.getSalario() < 18000) {
            throw new RuntimeException("El salario no puede ser inferior a 18.000€");
        }
        // Ya no tenemos que buscar el Empleado porque directamente busca el id y
        // actualiza los campos,
        // Si no lo encuentra lo crea

        return empleadoRepository.save(empleado);
    }

    public void eliminarPorId(Long id) {
        Empleado empleadoABorrar = empleadoRepository.findById(id)
        .orElseThrow(() -> new EmpleadoNotFoundException(id));
        empleadoRepository.deleteById(empleadoABorrar.getId());
    }

    public void eliminar(Empleado empleado) {
        Empleado empleadoABorrar = empleadoRepository.findById(empleado.getId())
        .orElseThrow(() -> new EmpleadoNotFoundException(empleado.getId()));

        empleadoRepository.delete(empleadoABorrar);
    }

    // BUSCADOR
    public List<Empleado> buscarPorNombre(String textoNombre) {

        // Convertimos todo a minúscula para que no no sea case sensitive
        textoNombre = textoNombre.toLowerCase();

        // Creamos un nuevo arrayList para almacenar los empleados encontrados
        List<Empleado> encontrados = empleadoRepository.findByNombreContainingIgnoreCase(textoNombre);

        return encontrados;
    }

    // BÚSQUEDA POR GÉNERO
    public List<Empleado> buscarPorGenero(Genero genero) {
        List<Empleado> encontrados = empleadoRepository.findByGenero(genero);

        return encontrados;
    }
}
