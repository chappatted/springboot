package com.example.demo.service;

import com.example.demo.dto.BookCreationDto;
import com.example.demo.dto.BookResponseDto;
import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    @Value("${test.string}")
    private String testString;

    @Value("${test.integer}")
    private Integer testInteger;

    private final BookRepository bookRepository;

    public BookResponseDto create(BookCreationDto dto) {
        Book book = dto.toEntity();
        bookRepository.save(book);

        return BookResponseDto.fromEntity(book);
    }

    public Optional<BookResponseDto> getOne(Long id) {
        return bookRepository.findById(id).map(BookResponseDto::fromEntity);
    }

    public List<BookResponseDto> getAll() {
        return bookRepository.findAll().stream().map(BookResponseDto::fromEntity).toList();
    }
}
