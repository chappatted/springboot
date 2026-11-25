package ch.hearc.springboot.service;

import ch.hearc.springboot.dto.CreateStudentCardDto;
import ch.hearc.springboot.dto.StudentCardDto;
import ch.hearc.springboot.dto.UpdateStudentCardDto;
import ch.hearc.springboot.entity.Student;
import ch.hearc.springboot.entity.StudentCard;
import ch.hearc.springboot.exception.StudentCardNotFoundException;
import ch.hearc.springboot.exception.StudentNotFoundException;
import ch.hearc.springboot.repository.StudentCardRepository;
import ch.hearc.springboot.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class StudentCardService {

    private final StudentCardRepository studentCardRepository;
    private final StudentRepository studentRepository;

    public StudentCardService(StudentCardRepository studentCardRepository, StudentRepository studentRepository) {
        this.studentCardRepository = studentCardRepository;
        this.studentRepository = studentRepository;
    }

    public StudentCardDto create(final CreateStudentCardDto dto) {
        Student student = dto.studentId() == null ? null : findStudent(dto.studentId());
        StudentCard card = new StudentCard(dto.number(), student);

        return toDto(studentCardRepository.save(card));
    }

    public StudentCardDto getOne(final Long id) {
        return toDto(findStudentCard(id));
    }

    public List<StudentCardDto> getAll() {
        return studentCardRepository.findAll().stream().map(this::toDto).toList();
    }

    public StudentCardDto update(final Long id, final UpdateStudentCardDto dto) {
        StudentCard card = findStudentCard(id);
        Student student = dto.studentId() == null ? null : findStudent(dto.studentId());
        card.update(dto.number(), student);

        return toDto(studentCardRepository.save(card));
    }

    public void delete(final Long id) {
        studentCardRepository.deleteById(id);
    }

    public StudentCard findStudentCard(final Long id) {
        return studentCardRepository.findById(id).orElseThrow(() -> new StudentCardNotFoundException(id));
    }

    public Student findStudent(final Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    private StudentCardDto toDto(final StudentCard card) {
        return new StudentCardDto(
                card.getId(),
                card.getNumber(),
                card.getStudent() == null ? null : card.getStudent().getId(),
                card.getCreatedDate(),
                card.getLastModifiedDate());
    }
}
