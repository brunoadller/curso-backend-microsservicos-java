package com.crud.crudfirst.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.crudfirst.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
 
}
