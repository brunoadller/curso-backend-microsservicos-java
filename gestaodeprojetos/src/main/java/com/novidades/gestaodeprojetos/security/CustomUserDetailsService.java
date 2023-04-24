package com.novidades.gestaodeprojetos.security;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.novidades.gestaodeprojetos.model.Usuario;
import com.novidades.gestaodeprojetos.service.UsuarioService;

/*
 * 
 */
@Service
public class CustomUserDetailsService implements UserDetailsService{

  @Autowired
  private UsuarioService usuarioService;
  /*
   * obtem o usuario pelo user name
   * vai la no banco chama o repositorio
   */
  @Override
  public UserDetails loadUserByUsername(String email) {
    //pega o usuario que retorna um optional e o get retorna o conteudo de dentro
    //manda o usuario pra função getUser e retorna ele se conseguir se não lança a exception
    //Usuario usuario = getUser(() -> usuarioService.obterPorEmail(email));
    //return usuario;
    return usuarioService.obterPorEmail(email).get();
  }

  public UserDetails obterUsuarioPorId(Long id){

    return usuarioService.obterPorId(id).get();
  }

  //supplier recebe um optional de um usuario
  //supplier e como se fosse um lambda
  //ele é tipo um if condicional
  //private Usuario getUser(Supplier<Optional<Usuario>> supplier){
  //  return supplier.get()
  //  .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));
  //}
  
}
