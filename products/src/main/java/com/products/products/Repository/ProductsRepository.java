package com.products.products.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.products.products.models.Product;

public interface ProductsRepository extends JpaRepository<Product, Long>{
  
}
