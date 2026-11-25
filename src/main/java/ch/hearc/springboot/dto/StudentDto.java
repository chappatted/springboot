package ch.hearc.springboot.dto;

import ch.hearc.springboot.entity.StudentCard;

import java.time.LocalDateTime;
import java.util.List;

public record StudentDto(
        Long id,
        String firstName,
        String lastName,
        StudentCard studentCard,
        List<Long> courseIds,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate) {}
