package ch.hearc.springboot.dto;

import java.time.LocalDateTime;

public record StudentCardDto(
        Long id,
        Integer number,
        Long studentId,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate) {}
