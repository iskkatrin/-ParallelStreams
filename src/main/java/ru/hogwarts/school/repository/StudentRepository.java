package ru.hogwarts.school.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository <Student, Long>{

    List<Student> getByAge(int age);
    List<Student> findByAgeBetween(int minAge, int maxAge);
    Student getById(Long studentId);

    @Query(value = "SELECT COUNT(*) FROM Student", nativeQuery = true)
    int getStudentCount();

    @Query(value = "SELECT AVG(age) FROM Student", nativeQuery = true)
    double getAverageAge();

    @Query(value = "SELECT * FROM Student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> getLastFiveStudents();
}
