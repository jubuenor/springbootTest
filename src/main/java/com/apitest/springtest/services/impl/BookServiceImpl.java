package com.apitest.springtest.services.impl;

import com.apitest.springtest.domain.entities.BookEntity;
import com.apitest.springtest.repositories.BookRepository;
import com.apitest.springtest.services.BookService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

  private BookRepository bookRepository;

  public BookServiceImpl(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public BookEntity createBook(String isbn, BookEntity book) {
    book.setIsbn(isbn);
    return bookRepository.save(book);
  }

  @Override
  public List<BookEntity> findAll() {
    return StreamSupport.stream(bookRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<BookEntity> findOne(String isbn) {
    return bookRepository.findById(isbn);
  }

  @Override
  public Boolean exists(String isbn) {
    return bookRepository.existsById(isbn);
  }

  @Override
  public void delete(String isbn) {
    bookRepository.deleteById(isbn);
  }
}
