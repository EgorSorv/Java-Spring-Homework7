package ru.gb.Homework7.repository;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import ru.gb.Homework7.model.Student;

import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class StudentRepository {
    // список всех студентов
    List<Student> students = new ArrayList<>();

    // добавление нового студента
    public void addStudent(Student student) {
        students.add(student);
    }

    // удаление студента
    public void deleteStudent(int id) {
        students.removeIf(student -> student.getId() == id);
    }
}
