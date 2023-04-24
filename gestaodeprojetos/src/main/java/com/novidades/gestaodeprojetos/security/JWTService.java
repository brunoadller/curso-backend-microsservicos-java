package com.novidades.gestaodeprojetos.security;

import java.util.Date;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.novidades.gestaodeprojetos.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
/*
 * JWTService é o servico do jwt
 * classe responsável por gerar um token jwt
 * annotation @Component para poder acessar esta classe de qualquer lugar
 * e o spring boot consiga injetar ela dentro do seu container de injeção de dependencia
 */
@Component
public class JWTService {
  //criando uma chave privada para criptografar e descriptografar o token
  private static final String chavePrivadaJWT = "secretKey";

  public String gerarToken(Authentication authentication){
    //tempo de expiração do token tem que ser em milisegundos
    //1 dia em milisegundos
    //Aqui pode variar de acordo com a sua regra de negocio
    int tempoExpiracao = 86400000;

    //Aqui estou criando uma data de expiração para o token com base no tempo de expiração
    // ele pega a a data atuyal e soma mais um dia em milliceconds
   
    Date dataExpiracao = new Date(new Date().getTime() + tempoExpiracao);

    //aqui pegamos o usuario atual da autenticação
    Usuario usuario = (Usuario) authentication.getPrincipal();
    /*
     * Crinado o token jwts
     * pegando o id do usuario
     * pegando a data atual que gero ele
     * definindo a data de expiração
     * definindo qual o tipo de criptografia que vai utilizar
     * passando o tipo de criptografia e a chave
     * compctar cria o token e te retorna a string
     */
    return Jwts.builder()
          .setSubject(usuario.getId().toString())
          .setIssuedAt(new Date())
          .setExpiration(dataExpiracao)
          .signWith(SignatureAlgorithm.HS512,chavePrivadaJWT )
          .compact();
  }
  /*
   * Metodo para retornar o id do usuario dono do token
   */
  public Optional<Long> obterIdDoUsuario(String token){
    try {
      //pegando o corpo do que ele esta me devolvendo
      //retorna as permissoes do token
      Claims claims = parse(token).getBody();
      
      //retorna o id de dentro do token se encontrar
      //caso contrario retorna nulo
      return Optional.ofNullable(Long.parseLong(claims.getSubject()));
    } catch (Exception e) {
      //retorna um optional vazio caso não encontre
      return Optional.empty();
    }
  }
  /*
   * Vai la dentro do token com a chave jwt e retorna um jwt de claims
   * Metodo que sabe descobrir de dentro do token com base 
   * na chave privada qual as permissoes do usuario
   */
  private Jws<Claims> parse(String token) {
    return Jwts.parser().setSigningKey(chavePrivadaJWT)
    .parseClaimsJws(token);
  }
}
