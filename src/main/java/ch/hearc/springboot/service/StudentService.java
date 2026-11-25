package ch.hearc.springboot.service;

import ch.hearc.springboot.dto.CreateStudentDto;
import ch.hearc.springboot.dto.StudentDto;
import ch.hearc.springboot.dto.UpdateStudentDto;
import ch.hearc.springboot.entity.Course;
import ch.hearc.springboot.entity.Student;
import ch.hearc.springboot.exception.CourseNotFoundException;
import ch.hearc.springboot.exception.StudentNotFoundException;
import ch.hearc.springboot.repository.CourseRepository;
import ch.hearc.springboot.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public StudentDto create(final CreateStudentDto dto) {
        Student student = new Student(dto.firstName(), dto.lastName());
        student = studentRepository.save(student);

        return toDto(student);
    }

    public StudentDto getOne(final Long id) {
        Student student = findStudent(id);

        return toDto(student);
    }

    public List<StudentDto> getAll() {
        return studentRepository.findAll().stream().map(this::toDto).toList();
    }

    public StudentDto update(final Long id, final UpdateStudentDto dto) {
        Student student = findStudent(id);
        student.update(dto.firstName(), dto.lastName());

        return toDto(student);
    }

    public void addCourse(final Long studentId, final Long courseId) {
        Student student = findStudent(studentId);
        Course course = findCourse(courseId);
        student.addCourse(course);
    }

    public void removeCourse(final Long studentId, final Long courseId) {
        Student student = findStudent(studentId);
        Course course = findCourse(courseId);
        student.removeCourse(course);
    }

    public void delete(final Long id) {
        studentRepository.deleteById(id);
    }

    public Student findStudent(final Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    public Course findCourse(final Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
    }

    private StudentDto toDto(final Student student) {
        List<Long> courseIds = student.getCourses().stream().map(Course::getId).toList();

        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getStudentCard() == null ? null : student.getStudentCard(),
                courseIds,
                student.getCreatedDate(),
                student.getLastModifiedDate());
    }
}
