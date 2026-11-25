package ch.hearc.springboot.controller;

import ch.hearc.springboot.dto.CreateStudentCardDto;
import ch.hearc.springboot.dto.StudentCardDto;
import ch.hearc.springboot.dto.UpdateStudentCardDto;
import ch.hearc.springboot.service.StudentCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student-card")
public class StudentCardController {

    private final StudentCardService studentCardService;

    public StudentCardController(StudentCardService studentCardService) {
        this.studentCardService = studentCardService;
    }

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<StudentCardDto> create(@RequestBody final CreateStudentCardDto dto) {
        return ResponseEntity.ok(studentCardService.create(dto));
    }

    @GetMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<StudentCardDto> getOne(@PathVariable final Long id) {
        return ResponseEntity.ok(studentCardService.getOne(id));
    }

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<List<StudentCardDto>> getAll() {
        return ResponseEntity.ok(studentCardService.getAll());
    }

    @PutMapping(value = "{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<StudentCardDto> update(@PathVariable final Long id, @RequestBody final UpdateStudentCardDto dto) {
        return ResponseEntity.ok(studentCardService.update(id, dto));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        studentCardService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
