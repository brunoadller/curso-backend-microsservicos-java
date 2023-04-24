package com.novidades.gestaodeprojetos.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
/*
 * Spring-boot-starter-security trabalha com segurança
 * jjwt é para trabalhar com autenticação em si
 */
@Entity
//faz o hibernate criar no banco uma sequencia de usuario
@SequenceGenerator(name = "generator_usuario", 
sequenceName = "sequence_usuario", initialValue = 1, allocationSize = 1)
//para poder utilizar o security tem que usar uma interface implementada
public class Usuario implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_usuario")
  private Long id;

  @Column(nullable = false)
  private String nome;

  //cada email do banco será unico e não pode ser nulo
  @Column(nullable = false, unique = true)
  private String email;

  //grava-se so a hash para que a senha não fique publica
  @Column(nullable = false)
  private String senha;

  public Usuario() {
  }

  //usa-se esse construtor para botar colocar o usuario no banco
  public Usuario(Long id, String nome, String email, String senha) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.senha = senha;
  }
  public Usuario(String nome, String email, String senha) {
    this.nome = nome;
    this.email = email;
    this.senha = senha;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }
  // Aqui em baixo é a implementação do user details
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getPassword() {
    // TODO Auto-generated method stub
    return senha;
  }

  @Override
  public String getUsername() {
    // TODO Auto-generated method stub
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  //define se o usuário esta ativo
  @Override
  public boolean isEnabled() {
    // TODO Auto-generated method stub
    return true;
  }
  
}
