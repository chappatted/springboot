package ch.hearc.springboot.service;

import ch.hearc.springboot.dto.CreateStudentDto;
import ch.hearc.springboot.dto.StudentDto;
import ch.hearc.springboot.entity.Student;
import ch.hearc.springboot.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentDto create(final CreateStudentDto dto) {
        Student student = new Student(dto.firstName(), dto.lastName());
        student = studentRepository.save(student);

        return new StudentDto(student.getId(), student.getFirstName(), student.getLastName());
    }

    public StudentDto getOne(final Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));

        return new StudentDto(student.getId(), student.getFirstName(), student.getLastName());
    }

    public List<StudentDto> getAll() {
        List<Student> students = studentRepository.findAll();

        return students.stream()
                .map(student -> new StudentDto(student.getId(), student.getFirstName(), student.getLastName()))
                .toList();
    }

    public void delete(final Long id) {
        studentRepository.deleteById(id);
    }
}
