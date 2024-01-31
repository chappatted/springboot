package com.example.demo.dto;

import com.example.demo.entity.Book;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookCreationDto {

    @NotNull
    @Size(min=3, max=100)
    private String title;

    public Book toEntity() {
        return new Book(title);
    }

}
