package com.crud.crudfirst.view.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.crudfirst.services.ProdutoService;
import com.crud.crudfirst.shared.ProdutoDTO;
import com.crud.crudfirst.view.Model.ProdutoRequest;
import com.crud.crudfirst.view.Model.ProdutoResponse;

@RestController
@RequestMapping("/api/products")
public class ProdutoController {

  @Autowired
  private ProdutoService produtoService;

  @PostMapping
  public ResponseEntity<ProdutoResponse> add(@RequestBody ProdutoRequest produtoRequest){
    ModelMapper mapper = new ModelMapper();

    ProdutoDTO produtoDto = mapper.map(produtoRequest, ProdutoDTO.class);

    produtoDto = produtoService.add(produtoDto);

    ProdutoResponse produtoResponse = mapper.map(produtoDto, ProdutoResponse.class);

    return new ResponseEntity<>(produtoResponse, HttpStatus.CREATED);

  }

  @GetMapping
  public ResponseEntity<List<ProdutoResponse>> getAll(){

    ModelMapper mapper = new ModelMapper();

    List<ProdutoDTO> produtosDto = produtoService.getAll();

    List<ProdutoResponse> response = produtosDto.stream()
    .map(produtoDto -> mapper.map(produtoDto, ProdutoResponse.class))
    .collect(Collectors.toList());  
    
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  
}
