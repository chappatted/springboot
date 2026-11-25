package ch.hearc.springboot.dto;

import java.time.LocalDateTime;
import java.util.List;

public record TeacherDto(
        Long id,
        String firstName,
        String lastName,
        List<Long> courseIds,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate) {}
