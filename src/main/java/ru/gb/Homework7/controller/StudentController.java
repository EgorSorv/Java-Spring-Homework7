package ru.gb.Homework7.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.Homework7.model.Student;
import ru.gb.Homework7.repository.StudentRepository;

@Controller
@AllArgsConstructor
public class StudentController {
    private final StudentRepository repository;

    // страница входа
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // список студентов для пользователя
    @GetMapping("/user-profile")
    public String userGetStudents(Model model) {
        model.addAttribute("students", repository.getStudents());

        return "user-profile";
    }

    // список студентов для администратора
    @GetMapping("/admin-profile")
    public String adminGetStudents(Model model) {
        model.addAttribute("students", repository.getStudents());

        return "admin-profile";
    }

    // добавление студента администратором
    @PostMapping("/admin-profile")
    public String adminAddStudent(Student student, Model model) {
        repository.addStudent(student);
        model.addAttribute("students", repository.getStudents());

        return "redirect:/admin-profile";
    }

    // добавление студента пользователем
    @PostMapping("/user-profile")
    public String userAddStudent(Student student, Model model) {
        repository.addStudent(student);
        model.addAttribute("students", repository.getStudents());

        return "redirect:/user-profile";
    }

    // удаление пользователя администратором
    @GetMapping("delete-student/{id}")
    public String adminDeleteStudent(@PathVariable("id") int id) {
        repository.deleteStudent(id);

        return "redirect:/admin-profile";
    }
}
