package ch.hearc.springboot.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    public Course() {
    }

    public Course(String name, String description, Teacher teacher) {
        this.name = name;
        this.description = description;
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void update(String name, String description, Teacher teacher) {
        this.name = name;
        this.description = description;
        this.teacher = teacher;
    }

    public void addStudent(Student student) {
        if(!students.contains(student)) {
            students.add(student);
            student.getCourses().add(this);
        }
    }

    public void removeStudent(Student student) {
        if(students.contains(student)) {
            students.remove(student);
            student.getCourses().remove(this);
        }
    }

    public void removeTeacher() {
        this.teacher = null;
    }
}
