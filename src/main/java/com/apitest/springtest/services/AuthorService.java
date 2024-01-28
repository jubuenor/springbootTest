package com.apitest.springtest.services;

import com.apitest.springtest.domain.entities.AuthorEntity;
import java.util.List;
import java.util.Optional;

public interface AuthorService {
  AuthorEntity save(AuthorEntity authorEntity);
  List<AuthorEntity> findAll();
  Optional findOne(Long id);
  Boolean exists(Long id);
  void delete(Long id);
}
