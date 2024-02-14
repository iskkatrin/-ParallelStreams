package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.List;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(String name, String color) {
        Faculty faculty = new Faculty();
        faculty.setName(name);
        faculty.setColor(color);
        return facultyRepository.save(faculty);
    }
    public Faculty getFacultyById(long facultyId) {
        return facultyRepository.findById(facultyId).orElse(null);
    }

    public Faculty updateFaculty(Long Id, String name, String color) {
        Faculty faculty = facultyRepository.findById(Id).get();
        faculty.setName(name);
        faculty.setColor(color);
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long facultyId) {
        facultyRepository.deleteById(facultyId);
    }

    public List<Faculty> getFacultyByColor(String color) {
        return facultyRepository.findByColor(color);
    }

    public List<Student> getStudentByFaculty(Long facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(
                () -> new FacultyNotFoundException("Faculty not found with id: " + facultyId));
        return faculty.getStudents();


    }
}

