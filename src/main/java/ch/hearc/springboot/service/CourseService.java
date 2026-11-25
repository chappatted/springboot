package ch.hearc.springboot.service;

import ch.hearc.springboot.dto.CourseDto;
import ch.hearc.springboot.dto.CreateCourseDto;
import ch.hearc.springboot.dto.UpdateCourseDto;
import ch.hearc.springboot.entity.Course;
import ch.hearc.springboot.entity.Student;
import ch.hearc.springboot.entity.Teacher;
import ch.hearc.springboot.exception.CourseNotFoundException;
import ch.hearc.springboot.exception.StudentNotFoundException;
import ch.hearc.springboot.exception.TeacherNotFoundException;
import ch.hearc.springboot.repository.CourseRepository;
import ch.hearc.springboot.repository.StudentRepository;
import ch.hearc.springboot.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    public CourseDto create(final CreateCourseDto dto) {
        Course course = new Course(dto.name(), dto.description(), findTeacher(dto.teacherId()));
        return toDto(courseRepository.save(course));
    }

    public CourseDto getOne(final Long id) {
        return toDto(findCourse(id));
    }

    public List<CourseDto> getAll() {
        return courseRepository.findAll().stream().map(this::toDto).toList();
    }

    public CourseDto update(final Long id, final UpdateCourseDto dto) {
        Course course = findCourse(id);
        Teacher teacher = dto.teacherId() == null ? null : findTeacher(dto.teacherId());
        course.update(dto.name(), dto.description(), teacher);

        return toDto(courseRepository.save(course));
    }

    public void addStudent(final Long courseId, final Long studentId) {
        Course course = findCourse(courseId);
        Student student = findStudent(studentId);
        course.addStudent(student);
    }

    public void removeStudent(final Long courseId, final Long studentId) {
        Course course = findCourse(courseId);
        Student student = findStudent(studentId);
        course.removeStudent(student);
    }

    public void delete(final Long id) {
        courseRepository.delete(findCourse(id));
    }

    public Course findCourse(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
    }

    public Student findStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    public Teacher findTeacher(Long id) {
        return teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException(id));
    }

    private CourseDto toDto(Course course) {
        List<Long> studentIds = course.getStudents().stream().map(Student::getId).toList();

        return new CourseDto(
                course.getId(),
                course.getName(),
                course.getDescription(),
                course.getTeacher() == null ? null : course.getTeacher().getId(),
                studentIds,
                course.getCreatedDate(),
                course.getLastModifiedDate());
    }
}
