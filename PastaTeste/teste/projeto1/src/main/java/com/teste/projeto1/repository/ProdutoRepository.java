package com.teste.projeto1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.projeto1.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
  
}
