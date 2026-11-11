package ch.hearc.springboot.controller;

import ch.hearc.springboot.dto.CreateStudentDto;
import ch.hearc.springboot.dto.StudentDto;
import ch.hearc.springboot.service.StudentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "", consumes =  "application/json", produces = "application/json")
    public StudentDto create(@RequestBody CreateStudentDto dto) {
        return studentService.create(dto);
    }
}
