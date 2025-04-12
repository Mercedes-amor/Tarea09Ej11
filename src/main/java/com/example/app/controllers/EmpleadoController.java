package com.example.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.server.ResponseStatusException;

import com.example.app.domain.Empleado;
import com.example.app.domain.Genero;
import com.example.app.exceptions.EmpleadoNotFoundException;
import com.example.app.exceptions.EmpleadosEmptyException;
import com.example.app.services.EmpleadoService;

import jakarta.validation.Valid;

@RestController

@SessionAttributes("txtErr")

public class EmpleadoController {

    @Autowired(required = true)
    EmpleadoService empleadoService;

    @GetMapping("/empleados")
    public List<Empleado> showList() {

        // Primero instanciamos la lista de empleados
        List<Empleado> listaEmpleados;

        // Usamos el método obtenerTodos() dentro de un try-catch
        // El catch captura la excepción y la lanza si es el caso
        try {
            listaEmpleados = empleadoService.obtenerTodos(); // Lanza 200 si todo ok
        } catch (EmpleadosEmptyException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return listaEmpleados;
    }

    // Aquí ya declaramos que siempre devolverá un Empleado, ya que si hay alguna
    // excepción el catch la recogerá y lanzará
    @GetMapping("/empleado/{id}")
    public Empleado showOne(@PathVariable Long id) {

        // Declaramos la variable para almacenar el empleado
        Empleado empleado;

        try {
            empleado = empleadoService.obtenerPorId(id); // Lanza 200 si todo ok
        } catch (EmpleadoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return empleado;
    }

    @PostMapping("/empleado/")

    // Añadimos el @RequestBody y eliminamos BindingResult (ya no aplicable)
    public ResponseEntity<?> showNew(@Valid @RequestBody Empleado empleadoForm) {

        // Si los datos no son correctos @Valid ya lanzará error 400
        // Por ello no necesitamos BindingResult

        // Creamos el empleado
        Empleado empleado = empleadoService.add(empleadoForm);

        // Si creamos correctamente el empleado
        if (empleado != null) {
            // Devolvemos status CREATED (201) y el empleado
            return ResponseEntity.status(HttpStatus.CREATED).body(empleado);

        } else {
            // Si no se crea correctamente el empleado devolvemos status BAD_REQUEST
            // versión equivalente: ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return ResponseEntity.badRequest().body("Error en alta empleado");
        }

    }

    // EDITAR EMPLEADO
    @PutMapping("/empleado/{id}")
    public ResponseEntity<?> showEdit(@PathVariable Long id,
            @Valid @RequestBody Empleado empleadoForm) {

        try {
            Empleado empleadoAEditar = empleadoService.actualizar(empleadoForm);
            return ResponseEntity.status(HttpStatus.OK).body(empleadoAEditar);
        } catch (EmpleadoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }

    // BORRAR EMPLEADO
    @DeleteMapping("/empleado/{id}")
    public ResponseEntity<?> showDelete(@PathVariable Long id) {

        try {
            empleadoService.eliminarPorId(id);
            return ResponseEntity.status(HttpStatus.OK).build();

        } catch (EmpleadoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
       
    }

    // BUSCADORES
    @GetMapping("/bysalary/{salario}")
    public ResponseEntity<?> geBySalary(@PathVariable Float salario) {

        // Obtenemos los empleados por salario
        List<Empleado> empleados = empleadoService.obtenerPorSalarioMayor(salario);
        if (empleados.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok().body(empleados);
        }
    }

    @GetMapping("/maxid")
    public ResponseEntity<?> getMaxId(Model model) {

        // Obtenemos el empleado de maxId
        Empleado empleado = empleadoService.obtenerMaxIdEmpleado();

        if (empleado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok().body(empleado);
        }
    }

    @PostMapping("/findByName")
    public ResponseEntity<?> showFindByName(
            @RequestParam("busqueda") String busqueda) {
        // Creamos el arrayList con los empleados encontrados
        List<Empleado> empleadosEncontrados = empleadoService.buscarPorNombre(busqueda);
        if (empleadosEncontrados.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok().body(empleadosEncontrados);
        }
    }

    // BÚSQUEDA POR GÉNERO
    @GetMapping("/findByGenero/{genero}")
    public ResponseEntity<?> showFindByGenero(
            @PathVariable Genero genero) {

        // Creamos el arrayList con los empleados encontrados
        List<Empleado> empleadosEncontrados = empleadoService.buscarPorGenero(genero);
        if (empleadosEncontrados.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok().body(empleadosEncontrados);
        }
    }
}
