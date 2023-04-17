package com.crud.crudsecond.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.crudsecond.model.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
  
}
