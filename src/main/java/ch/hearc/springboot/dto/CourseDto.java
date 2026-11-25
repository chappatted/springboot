package ch.hearc.springboot.dto;

import java.time.LocalDateTime;
import java.util.List;

public record CourseDto(
        Long id,
        String name,
        String description,
        Long teacherId, List<Long> studentIds,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate) {}
