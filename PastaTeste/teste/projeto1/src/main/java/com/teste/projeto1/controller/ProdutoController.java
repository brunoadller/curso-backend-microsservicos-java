package com.teste.projeto1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.teste.projeto1.model.Produto;

import com.teste.projeto1.services.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

  @Autowired
  private ProdutoService produtoService;

  @GetMapping
  public List<Produto> obterTodos(){
    return produtoService.obterTodos();
  }
  @GetMapping("/{id}")
  public Optional<Produto> obterPorId(@PathVariable Integer id){
    return produtoService.obterPorId(id);
  }
  @PutMapping("/atualizar/{id}")
  public Produto atualizar(@RequestBody Produto produto, @PathVariable Integer id){
    return produtoService.atualizar(produto,id);
  }
  @PostMapping("/adicionar")
  public Produto adicionar(@RequestBody Produto produto){
    return produtoService.adicionar(produto);
  }
  @DeleteMapping("/deletar/{id}")
  public void deletar(@PathVariable Integer id){
     produtoService.deletar(id);
  }
}
