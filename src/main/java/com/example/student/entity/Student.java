package com.example.student.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long roll_no;

        @Column(name = "name", nullable = false, length = 20)
        private String name;

        @Column(name = "class", nullable = false)
        private String clas;

        @Column(name = "city", nullable = false)
        private String city;

        @Column(name = "marks", nullable = false)
        private int marks;


}
