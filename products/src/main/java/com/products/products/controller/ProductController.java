package com.products.products.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.products.products.models.Product;
import com.products.products.services.ProductsServices;

@RestController
@RequestMapping("/api")
public class ProductController {
  
  @Autowired
  private ProductsServices ps;

  @GetMapping("/")
  public Iterable<Product> showProducts(){
    return ps.showProducts(); 
  }
  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody Product p){
    return ps.registerAndUpdateProducts(p, "register");
  }

  @PutMapping("/update")
  public ResponseEntity<?> update(@RequestBody Product p){
    return ps.registerAndUpdateProducts(p, "update");
  }
}
