package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    private final StudentRepository studentRepository;

    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public ResponseEntity<Student> getStudent(@PathVariable int Id) {
        Student student = studentService.getStudentById(Id);
        return new ResponseEntity<>(new Student(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestParam String name, @RequestParam int age) {
        Student student = studentService.createStudent(name, age);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestParam String name, @RequestParam int age) {
        Student student = studentService.updateStudent(studentId, name, age);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>("Faculty deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/age/{age}")
    public List<Student> getStudentsByAge(@PathVariable int age) {
        return studentService.getStudentsByAge(age);
    }

    @GetMapping("/{id}")
    public Faculty getFacultyByStudentId(@PathVariable Long studentId) {
        return studentService.getFacultyByStudentId(studentId);
    }

    @GetMapping("/namesStartingWithA")
    public ResponseEntity<List<String>> getStudentNamesStartingWithA() {
        List<Student> students = studentRepository.findAll();

        List<String> namesStartingWithA = students.stream()
                .filter(student -> student.getName().toUpperCase().startsWith("A"))
                .map(Student::getName)
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());

        return ResponseEntity.ok(namesStartingWithA);
    }

    @GetMapping("/averageAge")
    public ResponseEntity<Double> getAverageStudentAge() {
        List<Student> students = studentRepository.findAll();

        double averageAge = students.stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElse(0);

        return ResponseEntity.ok(averageAge);
    }

    @GetMapping("/print-parallel")
    public void printStudentNamesParallel() {
        studentService.printStudentNamesParallel();
    }
    @GetMapping("/print-synchronized")
    public void printStudentsSynchronized() {
        studentService.printStudentsSynchronized();
    }

    @GetMapping
    public void printStudentNamesParalell() {
        studentService.findStudent(getFacultyByStudentId(getFacultyByStudentId(getFacultyByStudentId(Long id))));
    }
}
