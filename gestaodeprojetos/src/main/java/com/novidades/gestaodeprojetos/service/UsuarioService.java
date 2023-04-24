package com.novidades.gestaodeprojetos.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.novidades.gestaodeprojetos.model.Usuario;
import com.novidades.gestaodeprojetos.repository.UsuarioRepository;

@Service
public class UsuarioService {
  
  @Autowired
  private UsuarioRepository repositorioUsuario;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public List<Usuario> obterTodos(){
    return repositorioUsuario.findAll();

  }
  public Optional<Usuario> obterPorId(Long id){
    return repositorioUsuario.findById(id);

  }
  public Optional<Usuario> obterPorEmail(String email){
    return repositorioUsuario.findByEmail(email);

  }
  public Usuario edicionar(Usuario usuario){
    //para não atualizar o id deve ser nulo
    usuario.setId(null);
    //valida se ja exite um usuario cadastrado com esse endereco de email
    if(obterPorEmail(usuario.getEmail()).isPresent()){
      //aqui poderia lançar uma excecao informando que o usuario ja existe
      throw new InputMismatchException("Ja existe um usuario cadastrado com o email "+usuario.getEmail());
    }
    //quero que ele codifique a senha do usuario
    //codificando a senha para não ficar publica gerando um hash
    String senha = passwordEncoder.encode(usuario.getSenha());

    usuario.setSenha(senha);
    return repositorioUsuario.save(usuario);
  }
}
