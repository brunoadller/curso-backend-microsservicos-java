package com.products.products.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.products.products.Exception.ErrorException;
import com.products.products.Repository.ProductsRepository;
import com.products.products.models.Product;

@Service
public class ProductsServices {
  
  @Autowired
  private ProductsRepository pr;

  @Autowired
  private ErrorException ee;

  public Iterable<Product> showProducts(){

     return pr.findAll();
  }

  public ResponseEntity<?> registerAndUpdate(Product p, String action){
    if(p.getName().equals("")){
      ee.setMessage("O nome é obrigatório");
      return new ResponseEntity<ErrorException>(ee, HttpStatus.BAD_REQUEST);
    }else if(p.getPrice().equals("")){
      ee.setMessage("O nome da marca é obrigatorio");
      return new ResponseEntity<ErrorException>(ee, HttpStatus.BAD_REQUEST);
    }else{

      if(action.equals("register")){
        return new ResponseEntity<Product>(pr.save(p), HttpStatus.CREATED);
      }else if(action.equals("update")){
        return new ResponseEntity<Product>(pr.save(p), HttpStatus.OK);
      }
    }
  }
}
