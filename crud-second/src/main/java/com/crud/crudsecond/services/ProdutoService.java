package com.crud.crudsecond.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.crudsecond.Repository.ProdutoRepository;
import com.crud.crudsecond.model.Produto;
import com.crud.crudsecond.shared.ProdutoDTO;

@Service
public class ProdutoService {
  @Autowired
  ProdutoRepository produtoRepository;


  public List<ProdutoDTO> getAll(){
    
    List<Produto> produtos = produtoRepository.findAll();

    List<ProdutoDTO> produtosDto = produtos.stream()
    .map(produto -> new ModelMapper()
    .map(produtos, ProdutoDTO.class))
    .collect(Collectors.toList());


    return produtosDto;


  }
}
