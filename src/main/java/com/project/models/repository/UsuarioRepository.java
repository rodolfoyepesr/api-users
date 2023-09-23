package com.project.models.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.project.models.entity.Usuario;


public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(String email);
}
