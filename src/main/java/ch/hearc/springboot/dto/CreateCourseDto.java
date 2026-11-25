package ch.hearc.springboot.dto;

public record CreateCourseDto(
        String name,
        String description,
        Long teacherId) {}
