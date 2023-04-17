package com.caderneta.caderneta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.caderneta.caderneta.Model.Notebook;
@Repository
public interface NotebookRepository extends JpaRepository<Notebook, Long> {
  
  @Query(value = "SELECT * FROM Notebook", nativeQuery = true)
  List<Notebook> getAllData();

  @Query(value = "SELECT sum(value) FROM Notebook", nativeQuery = true)
  Double valuesForProductsSum();

}
