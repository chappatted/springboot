package ch.hearc.springboot.service;

import ch.hearc.springboot.dto.CreateTeacherDto;
import ch.hearc.springboot.dto.TeacherDto;
import ch.hearc.springboot.dto.UpdateTeacherDto;
import ch.hearc.springboot.entity.Course;
import ch.hearc.springboot.entity.Teacher;
import ch.hearc.springboot.exception.TeacherNotFoundException;
import ch.hearc.springboot.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public TeacherDto create(final CreateTeacherDto dto) {
        return toDto(teacherRepository.save(new Teacher(dto.firstName(), dto.lastName())));
    }

    public TeacherDto getOne(final Long id) {
        return toDto(findTeacher(id));
    }

    public List<TeacherDto> getAll() {
        return teacherRepository.findAll().stream().map(this::toDto).toList();
    }

    public TeacherDto update(final Long id, final UpdateTeacherDto dto) {
        Teacher teacher = findTeacher(id);
        teacher.update(dto.firstName(), dto.lastName());

        return toDto(teacherRepository.save(teacher));
    }

    public void delete(final Long id) {
        Teacher teacher = findTeacher(id);

        for(Course course : teacher.getCourses()) {
            course.removeTeacher();
        }

        teacherRepository.delete(teacher);
    }

    public Teacher findTeacher(Long id) {
        return teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException(id));
    }

    private TeacherDto toDto(Teacher teacher) {
        List<Long> courseIds = teacher.getCourses().stream().map(Course::getId).toList();

        return new TeacherDto(
                teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                courseIds,
                teacher.getCreatedDate(),
                teacher.getLastModifiedDate());
    }
}
