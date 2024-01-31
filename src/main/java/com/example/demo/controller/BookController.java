package com.example.demo.controller;

import com.example.demo.dto.BookCreationDto;
import com.example.demo.dto.BookResponseDto;
import com.example.demo.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("book")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping(value = "", consumes =  "application/json", produces = "application/json")
    public BookResponseDto create(@Valid @RequestBody BookCreationDto dto) {
        return bookService.create(dto);
    }

    @GetMapping(value = "{id}", produces = "application/json")
    public BookResponseDto getOne(@PathVariable Long id) {
        Optional<BookResponseDto> book = bookService.getOne(id);

        if(book.isPresent()) {
            return book.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "", produces = "application/json")
    public List<BookResponseDto> getAll() {
        return bookService.getAll();
    }
}
