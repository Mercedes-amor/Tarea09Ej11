package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.app.domain.Empleado;
import com.example.app.domain.Genero;
import com.example.app.modelo.Rol;
import com.example.app.modelo.Usuario;
import com.example.app.repositories.UsuarioRepository;
import com.example.app.services.EmpleadoService;

@SpringBootApplication
public class Main {

	@Autowired
	EmpleadoService empleadoService;

	@Autowired
	UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		System.out.println("Tarea 9 API REST");
	}

	// @Bean
	// CommandLineRunner initData(EmpleadoService empleadoService) {
	// 	return args -> {
	// 		// Crear un usuario "admin"
	// 		Usuario admin = new Usuario(
	// 				null,
	// 				"admin",
	// 				"admin@admin.com",
	// 				"1234",
	// 				Rol.ADMIN);
	// 		admin = usuarioRepository.save(admin);

	// 		empleadoService.add(
	// 				new Empleado(null, "pepe", "pepe@gmail.com", 25000f, true, Genero.MASCULINO, admin));
	// 		empleadoService.add(
	// 				new Empleado(null, "ana", "ana@gmail.com", 28000f, true, Genero.FEMENINO, admin));
	// 		empleadoService.add(
	// 				new Empleado(null, "Mercedes", "Mercedesamor@gmail.com", 30000f, true, Genero.FEMENINO, admin));
	// 		empleadoService.add(
	// 				new Empleado(null, "Indiana Jones", "laxsiempre@gmail.com", 128000f, false, Genero.OTROS, admin));
	// 	};
	// }
}
