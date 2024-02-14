package ru.hogwarts.school.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long Id;
    private String name;
    private int age;

    @ManyToOne
    private Faculty faculty;

    public Student(Long Id, String name, int age) {
        this.Id = Id;
        this.name = name;
        this.age = age;
    }

}
