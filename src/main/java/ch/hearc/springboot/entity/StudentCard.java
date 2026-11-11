package ch.hearc.springboot.entity;

import jakarta.persistence.*;

@Entity
public class StudentCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer number;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
