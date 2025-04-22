package com.example.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  Usuario findByNombre(String nombre);

  Boolean existsByNombre(String nombre);

  Boolean existsByEmail(String email);
}
