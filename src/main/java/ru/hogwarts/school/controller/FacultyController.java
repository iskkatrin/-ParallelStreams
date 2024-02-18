package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private FacultyRepository facultyRepository;

    @GetMapping("/{facultyId}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long facultyId) {
        Faculty faculty = facultyService.getFacultyById(facultyId);
        return new ResponseEntity<>(faculty, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty f = facultyService.createFaculty(faculty.getName(), faculty.getColor());
        return new ResponseEntity<>(f, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable Long id, @RequestBody Faculty faculty) {
        Faculty f = facultyService.updateFaculty(id, faculty.getName(), faculty.getColor());
        return new ResponseEntity<>(f, HttpStatus.OK);
    }

    @DeleteMapping("/{facultyId}")
    public ResponseEntity<String> deleteFaculty(@PathVariable Long facultyId) {
        facultyService.deleteFaculty(facultyId);
        return new ResponseEntity<>("Faculty deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/color/{color}")
    public List<Faculty> getFacultyByColor(@PathVariable String color) {
        return facultyService.getFacultyByColor(color);
    }

    @GetMapping("/{id}/students")
    public List<Student> getStudentByFaculty(@PathVariable Long Id) {
        return facultyService.getStudentByFaculty(Id);
    }

    @GetMapping("/longestFacultyName")
    public ResponseEntity<String> getLongestFacultyName() {
        List<Faculty> faculties = facultyRepository.findAll();

        Optional<String> longestName = faculties.stream()
                .map(Faculty::getName)
                .max((f1, f2) -> f1.length() - f2.length());

        return ResponseEntity.ok(longestName.orElse("No faculty found"));
    }

    @GetMapping("/calculateSum")
    public int calculateSum() {
        int sum = Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);
        return sum;
    }
}
