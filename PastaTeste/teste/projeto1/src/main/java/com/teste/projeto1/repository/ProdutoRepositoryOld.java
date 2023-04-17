package com.teste.projeto1.repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.teste.projeto1.model.Produto;
import com.teste.projeto1.model.exception.ResourceNotFoundException;

@Repository
public class ProdutoRepositoryOld {
  private List<Produto> produtos = new ArrayList<Produto>();
  private Integer ultimoId = 0;

  /**
   * retorna uma lista de produtos
   * @return
   */
  public List<Produto> obterTodos(){
    return produtos;
  }

  /**
   * Metodo que retorna o produto encontrado pelo seu Id.
   * @param id do produto que sera localizado
   * @return Retorna um produto caso tenha encontrado
   */
  public Optional<Produto> obterPorId(Integer id){
    return  produtos
      .stream()
      .filter(produto -> produto.getId() == id)
      .findFirst();
  }
  /**
   * Metodo para adicionar produto na lista
   * @param produto que será adicionado
   * @return  Retona o produto adicionado na lista
   */
  public Produto adicionar(Produto produto){
      ultimoId++;

      produto.setId(ultimoId);
      produtos.add(produto);
      return produto;
  }
  /**
   * Metodo para deletar um produto po id
   * @param id do produto a ser deletado
   */
  public void deletar(Integer id){
    produtos.removeIf(produto -> produto.getId() == id);
  }

  /**
   * Metodo para atualizar o produto na lista
   * @param produto que será atualizado
   * @return retorna o produto atualizado
   */
  public Produto atualizar(Produto produto){
    //Encontrar um produto na lista
    Optional<Produto> produtoEncontrado = obterPorId(produto.getId());

    if(produtoEncontrado.isEmpty()){
      throw new ResourceNotFoundException("Produto nao pode ser encontrado pois não existe");
    } 
    //remover o produto antigo da lista
    deletar(produto.getId());

    //depois adicionar o produto atualizado na lista.
    produtos.add(produto);

    return produto;

  }

}
