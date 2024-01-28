package com.apitest.springtest.mappers.impl;

import com.apitest.springtest.domain.dto.BookDto;
import com.apitest.springtest.domain.entities.BookEntity;
import com.apitest.springtest.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements Mapper<BookEntity, BookDto> {

  private ModelMapper modelMapper;

  public BookMapperImpl(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public BookDto mapTo(BookEntity bookEntity) {
    return modelMapper.map(bookEntity, BookDto.class);
  }

  @Override
  public BookEntity mapFrom(BookDto BookDto) {
    return modelMapper.map(BookDto, BookEntity.class);
  }
}
