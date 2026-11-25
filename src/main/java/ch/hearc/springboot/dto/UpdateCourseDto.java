package ch.hearc.springboot.dto;

public record UpdateCourseDto(
        String name,
        String description,
        Long teacherId) {}
