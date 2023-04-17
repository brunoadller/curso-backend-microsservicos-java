package com.teste.projeto1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.projeto1.model.Produto;
import com.teste.projeto1.repository.ProdutoRepository;
import com.teste.projeto1.repository.ProdutoRepositoryOld;

@Service
public class ProdutoService {
  @Autowired
  private ProdutoRepository produtoRepository;
  
  /**
   * Metodo para retornar todos os produtos
   * @return
   */
  public List<Produto> obterTodos(){
    return produtoRepository.findAll();
  }
  
  /**
   * Retorna o elemento pelo seu id
   * @param id
   * @return
   */
  public Optional<Produto> obterPorId(Integer id){
    return produtoRepository.findById(id);
  }

  /**
   * Adiciona o produto
   * @param produto
   * @return
   */
  public Produto adicionar(Produto produto){
    //Poderia ter uma regra de negocio para validar o produto cadastrado
    return produtoRepository.save(produto);
  }
  /**
   * Deletar produto por id
   * @param id
   */
  public void deletar(Integer id){
    produtoRepository.deleteById(id);
  }

  /**
   * Produto para atualizar na lista
   * @param produto
   * @param id
   * @return
   */
  public Produto atualizar(Produto produto, Integer id){
    //ter alguma validação no ID
    produto.setId(id);

    return produtoRepository.save(produto);

  }
}
