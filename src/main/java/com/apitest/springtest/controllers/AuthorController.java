package com.apitest.springtest.controllers;

import com.apitest.springtest.domain.dto.AuthorDto;
import com.apitest.springtest.domain.entities.AuthorEntity;
import com.apitest.springtest.mappers.Mapper;
import com.apitest.springtest.services.AuthorService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

  private AuthorService authorService;

  private Mapper<AuthorEntity, AuthorDto> authorMapper;

  public AuthorController(AuthorService authorService,
                          Mapper<AuthorEntity, AuthorDto> authorMapper) {
    this.authorService = authorService;
    this.authorMapper = authorMapper;
  }

  @PostMapping("/authors")
  public ResponseEntity createAuthor(@RequestBody AuthorDto author) {
    AuthorEntity authorEntity = authorMapper.mapFrom(author);
    AuthorEntity savedAuthorEntity = authorService.save(authorEntity);
    return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity),
                                HttpStatus.CREATED);
  }

  @GetMapping(path = "/authors")
  public List<AuthorDto> listAuthors() {
    List<AuthorEntity> authors = authorService.findAll();
    return authors.stream()
        .map(authorMapper::mapTo)
        .collect(Collectors.toList());
  }

  @GetMapping(path = "/authors/{id}")
  public ResponseEntity<AuthorDto> getAuthor(@PathVariable Long id) {
    Optional<AuthorEntity> author = authorService.findOne(id);
    if (author.isPresent()) {
      return new ResponseEntity<>(authorMapper.mapTo(author.get()),
                                  HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PutMapping(path = "/authors/{id}")
  public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long id,
                                                @RequestBody AuthorDto author) {
    if (!authorService.exists(id)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    AuthorEntity authorEntity = authorMapper.mapFrom(author);
    authorEntity.setId(id);
    AuthorEntity savedAuthorEntity = authorService.save(authorEntity);
    return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity),
                                HttpStatus.OK);
  }

  @DeleteMapping(path = "/authors/{id}")
  public ResponseEntity deleteAuthor(@PathVariable Long id) {
    if (!authorService.exists(id)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    authorService.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
