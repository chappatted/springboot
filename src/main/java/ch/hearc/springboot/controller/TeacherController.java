package ch.hearc.springboot.controller;

import ch.hearc.springboot.dto.CreateTeacherDto;
import ch.hearc.springboot.dto.TeacherDto;
import ch.hearc.springboot.dto.UpdateTeacherDto;
import ch.hearc.springboot.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TeacherDto> create(@RequestBody final CreateTeacherDto dto) {
        return ResponseEntity.ok(teacherService.create(dto));
    }

    @GetMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<TeacherDto> getOne(@PathVariable final Long id) {
        return ResponseEntity.ok(teacherService.getOne(id));
    }

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<List<TeacherDto>> getAll() {
        return ResponseEntity.ok(teacherService.getAll());
    }

    @PutMapping(value = "{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TeacherDto> update(@PathVariable final Long id, @RequestBody final UpdateTeacherDto dto) {
        return ResponseEntity.ok(teacherService.update(id, dto));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        teacherService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
