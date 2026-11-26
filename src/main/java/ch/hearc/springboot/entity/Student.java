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
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    @OneToOne(mappedBy = "student")
    private StudentCard studentCard;

    @ManyToMany
    @JoinTable( name = "student_course",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
    private List<Course> courses = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    public Student() {
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public StudentCard getStudentCard() {
        return studentCard;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void update(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addCourse(Course course) {
        if(!courses.contains(course)) {
            courses.add(course);
            course.getStudents().add(this);
        }
    }

    public void removeCourse(Course course) {
        if(courses.contains(course)) {
            courses.remove(course);
            course.getStudents().remove(this);
        }
    }
}
