package com.caderneta.caderneta.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Notebook {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String nameProduct;
  private Double value;
  private String description;
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getNameProduct() {
    return nameProduct;
  }
  public void setNameProduct(String nameProduct) {
    this.nameProduct = nameProduct;
  }
  public Double getValue() {
    return value;
  }
  public void setValue(Double value) {
    this.value = value;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  
}
