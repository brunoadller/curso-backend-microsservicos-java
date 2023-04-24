package com.novidades.gestaodeprojetos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.novidades.gestaodeprojetos.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  
  //so de fazer aqui o jpa ja vai saber encontrar por padrao
  Optional<Usuario> findByEmail(String email);
}
