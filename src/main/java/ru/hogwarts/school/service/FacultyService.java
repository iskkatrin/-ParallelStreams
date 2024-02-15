package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.List;

@Service
public class FacultyService {

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty createFaculty(String name, String color) {
        logger.info("Метод createFaculty был вызван");
        Faculty faculty = new Faculty();
        faculty.setName(name);
        faculty.setColor(color);
        return facultyRepository.save(faculty);
    }
    public Faculty getFacultyById(long facultyId) {
        logger.info("Метод getFacultyById был вызван");
        return facultyRepository.findById(facultyId).orElse(null);
    }

    public Faculty updateFaculty(Long Id, String name, String color) {
        logger.info("Метод updateFaculty был вызван");
        Faculty faculty = facultyRepository.findById(Id).get();
        faculty.setName(name);
        faculty.setColor(color);
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long facultyId) {
        logger.info("Метод deleteFaculty был вызван");
        facultyRepository.deleteById(facultyId);
    }

    public List<Faculty> getFacultyByColor(String color) {
        logger.info("Метод getFacultyByColor был вызван");
        return facultyRepository.findByColor(color);
    }

    public List<Student> getStudentByFaculty(Long facultyId) {
        logger.info("Метод getStudentByFaculty был вызван");
        Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(
                () -> new FacultyNotFoundException("Faculty not found with id: " + facultyId));
        return faculty.getStudents();

    }
}

