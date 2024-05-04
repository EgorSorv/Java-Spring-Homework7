package ru.gb.Homework7.model;

import lombok.Data;

@Data
public class Student {
    private int id; // id студента
    private String firstName; // имя студента
    private String lastName; // фамилия студента
    private int age; // возраст студента
}
