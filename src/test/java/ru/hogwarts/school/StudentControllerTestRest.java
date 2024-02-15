package ru.hogwarts.school;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class StudentControllerTestRest {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetFaculty() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Kate");
        student.setAge(4);

        Student actual = this.restTemplate.getForObject("http://localhost:" + port + "/faculty/{1L}", Student.class, student.getId());

        Assertions.assertThat(actual.getId()).isEqualTo(1L);
        Assertions.assertThat(actual.getName()).isEqualTo("Kate");
        Assertions.assertThat(actual.getAge()).isEqualTo(4);
    }

    @Test
    public void testPostStudent() throws Exception {
        Student student = new Student();
        student.setName("Kate");
        student.setAge(4);
        Student actual = this.restTemplate.postForObject("http://localhost:" + port + "/student", student, Student.class);

        Assertions.assertThat(actual.getId()).isNotNull();
        Assertions.assertThat(actual.getName()).isEqualTo("Kate");
        Assertions.assertThat(actual.getAge()).isEqualTo(4);
    }

    @Test
    public void testPutStudent() throws Exception {
        Student student = new Student();
        student.setName("Mary");
        student.setAge(30);
        Student actual = this.restTemplate.postForObject("http://localhost:" + port + "/student", student, Student.class);
        actual.setName("Robin");
        actual.setAge(8);
        ResponseEntity <Student> updated = this.restTemplate.exchange
                ("http://localhost:" + port + "/student/" + actual.getId(), HttpMethod.PUT, new HttpEntity<>(actual), Student.class);

        Assertions.assertThat(updated.getBody().getId()).isNotNull();
        Assertions.assertThat(updated.getBody().getName()).isEqualTo("Robin");
        Assertions.assertThat(updated.getBody().getAge()).isEqualTo(8);

    }

    @Test
    public void testDeleteStudent() throws Exception {
        Student student = new Student();
        student.setName("Mary");
        student.setAge(30);
        Faculty actual = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", student, Faculty.class);
        ResponseEntity <String> response = restTemplate.exchange
                ("http://localhost:" + port + "/student/" + actual.getStudents(), HttpMethod.DELETE, null, String.class);

        Student s = restTemplate.getForObject("http://localhost:" + port + "/student/" +
                actual.getStudents(), Student.class);
        Assertions.assertThat(s).isNull();

    }

    @Test
    public void testGetStudentsByAge() throws Exception {

    }

    @Test
    public void TestGetFacultyByStudentId() throws Exception {

    }
}
