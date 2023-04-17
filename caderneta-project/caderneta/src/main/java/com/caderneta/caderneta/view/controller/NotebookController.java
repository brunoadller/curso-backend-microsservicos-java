package com.caderneta.caderneta.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caderneta.caderneta.Model.Notebook;
import com.caderneta.caderneta.services.NotebookService;
import com.caderneta.caderneta.shared.NotebookDTO;
import com.caderneta.caderneta.view.model.NotebookRequest;
import com.caderneta.caderneta.view.model.NotebookResponse;

@RestController
@RequestMapping("/api/caderno")
@CrossOrigin(origins = "*")
public class NotebookController {
  @Autowired
  private NotebookService notebookService;


  @GetMapping("")
  public ResponseEntity<List<Notebook>> getAll(){
     ModelMapper mapper = new ModelMapper();

     List<Notebook> notebookDtoList = notebookService.getAll();
     return  new ResponseEntity<>(notebookDtoList, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<NotebookResponse>> getForId(@PathVariable Long id){
    ModelMapper mapper = new ModelMapper();
    try{
      Optional<NotebookDTO> notebookDtoList = notebookService.getForId(id);
      
      
      NotebookResponse notebookResponseList = mapper.map(notebookDtoList, NotebookResponse.class);
      
      return new ResponseEntity<>(Optional.of(notebookResponseList), HttpStatus.OK);
    }catch(Exception e){
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
  }
  @PostMapping("")
  public ResponseEntity<NotebookResponse> register(@RequestBody NotebookRequest notebookReq){
    ModelMapper mapper = new ModelMapper();


    NotebookDTO notebookDto = mapper.map(notebookReq, NotebookDTO.class);


    notebookDto =  notebookService.register(notebookDto);


    NotebookResponse notebookResponse = mapper.map(notebookDto, NotebookResponse.class);
    
    return new ResponseEntity<>(notebookResponse, HttpStatus.CREATED);

  }
  @GetMapping("/produtosSomados")
  public Double valuesSums(){
    return notebookService.valuesSums();
  }
}
