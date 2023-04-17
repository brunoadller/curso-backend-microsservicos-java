package com.caderneta.caderneta.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.caderneta.caderneta.Model.Notebook;
import com.caderneta.caderneta.repository.NotebookRepository;
import com.caderneta.caderneta.shared.NotebookDTO;

import jakarta.el.ELException;

@Service
public class NotebookService {
  @Autowired
  private NotebookRepository notebookRepository;

 
  public List<Notebook> getAll(){
    ModelMapper mapper = new ModelMapper();
    List<Notebook> notebookList = notebookRepository.getAllData();

    /* 
    List<NotebookDTO> notebookDtoList = notebookList.stream()
    .map(notebook -> mapper.map(notebookList, NotebookDTO.class))
    .collect(Collectors.toList());
    */
    return notebookList;
  }

  public Optional<NotebookDTO> getForId(Long id){
    ModelMapper mapper = new ModelMapper();

    Optional<Notebook> notebookList = notebookRepository.findById(id);

    if(notebookList.isEmpty()){
      throw new ELException("ID not found");
    }

    NotebookDTO notebookDto = mapper.map(notebookList, NotebookDTO.class);

    return Optional.of(notebookDto);


  }

  public NotebookDTO register(NotebookDTO notebookDto){
    ModelMapper mapper = new ModelMapper();

    notebookDto.setId(null);


    Notebook notebook = mapper.map(notebookDto,  Notebook.class);


    notebook = notebookRepository.save(notebook);

    notebookDto.setId(notebook.getId());

    return notebookDto;

  }

  public Double valuesSums(){
    return notebookRepository.valuesForProductsSum();
  }

}
