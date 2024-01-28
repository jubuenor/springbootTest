package com.apitest.springtest.services;

import com.apitest.springtest.domain.entities.BookEntity;
import java.util.List;
import java.util.Optional;

public interface BookService {

  BookEntity createBook(String isbn, BookEntity book);
  List<BookEntity> findAll();
  Optional findOne(String isbn);
  Boolean exists(String isbn);
  void delete(String isbn);
}
