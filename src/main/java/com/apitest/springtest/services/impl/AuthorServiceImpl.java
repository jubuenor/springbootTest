package com.apitest.springtest.services.impl;

import com.apitest.springtest.domain.entities.AuthorEntity;
import com.apitest.springtest.repositories.AuthorRepository;
import com.apitest.springtest.services.AuthorService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

  private AuthorRepository authorRepository;

  public AuthorServiceImpl(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  @Override
  public AuthorEntity save(AuthorEntity authorEntity) {
    return authorRepository.save(authorEntity);
  }

  @Override
  public List<AuthorEntity> findAll() {
    return StreamSupport.stream(authorRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<AuthorEntity> findOne(Long id) {
    return authorRepository.findById(id);
  }

  @Override
  public Boolean exists(Long id) {
    return authorRepository.existsById(id);
  }

  @Override
  public void delete(Long id) {
    authorRepository.deleteById(id);
  }
}
