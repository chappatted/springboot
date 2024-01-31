package com.example.demo.dto;

import com.example.demo.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookResponseDto {

    private Long id;
    private String title;
    private Integer authorCount;

    public static BookResponseDto fromEntity(Book book) {
        return new BookResponseDto(book.getId(), book.getTitle(), book.getAuthors().size());
    }

}
