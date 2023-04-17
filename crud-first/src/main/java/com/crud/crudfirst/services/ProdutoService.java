package com.crud.crudfirst.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.crudfirst.model.Produto;
import com.crud.crudfirst.repository.ProdutoRepository;
import com.crud.crudfirst.shared.ProdutoDTO;

@Service
public class ProdutoService {
  @Autowired
  private ProdutoRepository produtoRepository;

  public List<ProdutoDTO> getAll(){
    ModelMapper mapper = new ModelMapper();    
    List<Produto> produtos = produtoRepository.findAll();

    List<ProdutoDTO> produtoDto = produtos.stream()
    .map(produto -> mapper.map(produtos, ProdutoDTO.class))
    .collect(Collectors.toList());

    return produtoDto;
  }

  public Optional<ProdutoDTO> getForId(Integer id){

      Optional<Produto> produto = produtoRepository.findById(id);

      ModelMapper  mapper = new ModelMapper();

      ProdutoDTO dto = mapper.map(produto.get(), ProdutoDTO.class);

      return Optional.of(dto);
  }

  public ProdutoDTO add(ProdutoDTO produtoDto){

    ModelMapper mapper = new ModelMapper();

    Produto produto = mapper.map(produtoDto, Produto.class);

    produto = produtoRepository.save(produto);

    produtoDto.setId(produto.getId());

    return produtoDto;
  }
  

}
