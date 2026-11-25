package ch.hearc.springboot.controller;

import ch.hearc.springboot.dto.CourseDto;
import ch.hearc.springboot.dto.CreateCourseDto;
import ch.hearc.springboot.dto.UpdateCourseDto;
import ch.hearc.springboot.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CourseDto> create(@RequestBody final CreateCourseDto dto) {
        return ResponseEntity.ok(courseService.create(dto));
    }

    @GetMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<CourseDto> getOne(@PathVariable final Long id) {
        return ResponseEntity.ok(courseService.getOne(id));
    }

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<List<CourseDto>> getAll() {
        return ResponseEntity.ok(courseService.getAll());
    }

    @PutMapping(value = "{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CourseDto> update(@PathVariable final Long id, @RequestBody final UpdateCourseDto dto) {
        return ResponseEntity.ok(courseService.update(id, dto));
    }

    @PutMapping(value = "{id}/add/{studentId}")
    public ResponseEntity<Void> addStudent(@PathVariable final Long id, @PathVariable final Long studentId) {
        courseService.addStudent(id, studentId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "{id}/remove/{studentId}")
    public ResponseEntity<Void> removeStudent(@PathVariable final Long id, @PathVariable final Long studentId) {
        courseService.removeStudent(id, studentId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
