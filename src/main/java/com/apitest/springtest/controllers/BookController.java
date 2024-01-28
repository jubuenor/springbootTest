package com.apitest.springtest.controllers;

import com.apitest.springtest.domain.dto.BookDto;
import com.apitest.springtest.domain.entities.BookEntity;
import com.apitest.springtest.mappers.Mapper;
import com.apitest.springtest.services.BookService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

  private BookService bookService;
  private Mapper<BookEntity, BookDto> bookMapper;

  public BookController(Mapper<BookEntity, BookDto> bookMapper,
                        BookService bookService) {
    this.bookMapper = bookMapper;
    this.bookService = bookService;
  }

  @PutMapping(path = "/books/{isbn}")
  public ResponseEntity<BookDto> createBook(@PathVariable String isbn,
                                            @RequestBody BookDto bookDto) {
    BookEntity bookEntity = bookMapper.mapFrom(bookDto);
    boolean exists = bookService.exists(isbn);
    BookEntity savedBookEntity = bookService.createBook(isbn, bookEntity);
    BookDto savedUpdatedBookDto = bookMapper.mapTo(savedBookEntity);
    if (exists) {
      return new ResponseEntity(savedUpdatedBookDto, HttpStatus.OK);
    }
    return new ResponseEntity(savedUpdatedBookDto, HttpStatus.CREATED);
  }

  // @PostMapping(path = "/books")
  // public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
  //   BookEntity bookEntity = bookMapper.mapFrom(bookDto);
  //   BookEntity savedBookEntity = bookService.createBook(bookEntity.getIsbn(),
  //   bookEntity); BookDto savedUpdatedBookDto =
  //   bookMapper.mapTo(savedBookEntity); return new
  //   ResponseEntity(savedUpdatedBookDto, HttpStatus.CREATED);
  // }
  //

  @GetMapping(path = "/books")
  public List<BookDto> listBooks() {
    List<BookEntity> books = bookService.findAll();
    return books.stream().map(bookMapper::mapTo).collect(Collectors.toList());
  }

  @GetMapping(path = "/books/{isbn}")
  public ResponseEntity<BookDto> getBook(@PathVariable String isbn) {
    Optional<BookEntity> book = bookService.findOne(isbn);
    if (book.isPresent()) {
      return new ResponseEntity<>(bookMapper.mapTo(book.get()), HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping(path = "/books/{isbn}")
  public ResponseEntity deleteBook(@PathVariable String isbn) {
    if (!bookService.exists(isbn)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    bookService.delete(isbn);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
