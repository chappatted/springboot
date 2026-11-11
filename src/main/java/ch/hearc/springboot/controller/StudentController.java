package ch.hearc.springboot.controller;

import ch.hearc.springboot.dto.CreateStudentDto;
import ch.hearc.springboot.dto.StudentDto;
import ch.hearc.springboot.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "", consumes =  "application/json", produces = "application/json")
    public StudentDto create(@RequestBody final CreateStudentDto dto) {
        return studentService.create(dto);
    }

    @GetMapping(value = "{id}", produces = "application/json")
    public StudentDto getOne(@PathVariable final Long id) {
        return studentService.getOne(id);
    }

    @GetMapping(value = "", produces = "application/json")
    public List<StudentDto> getAll() {
        return studentService.getAll();
    }

    @DeleteMapping(value = "{id}", produces = "application/json")
    public void delete(@PathVariable final Long id) {
        studentService.delete(id);
    }
}
