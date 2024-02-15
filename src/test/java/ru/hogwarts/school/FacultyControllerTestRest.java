package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)


public class FacultyControllerTestRest {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Nikki");
        faculty.setColor("Green");

        Faculty actual = this.restTemplate.getForObject(
                "http://localhost:" + port +"/faculty/{facultyId}", Faculty.class, faculty.getId());

        Assertions.assertThat(actual.getId()).isEqualTo(faculty.getId());
        Assertions.assertThat(actual.getName()).isEqualTo(faculty.getName());
        Assertions.assertThat(actual.getColor()).isEqualTo(faculty.getColor());
}

@Test
    public void testPostFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Zoo");
        faculty.setColor("Pink");
        Faculty actual = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, Faculty.class);

        Assertions.assertThat(actual.getId()).isNotNull();
        Assertions.assertThat(actual.getName()).isEqualTo("Zoo");
        Assertions.assertThat(actual.getColor()).isEqualTo("Pink");
    }

    @Test
    public void testPutFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Zoo");
        faculty.setColor("Pink");
        Faculty actual = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, Faculty.class);
        actual.setName("Cat");
        actual.setColor("Red");
      ResponseEntity <Faculty> updated = this.restTemplate.exchange
              ("http://localhost:" + port + "/faculty/" + actual.getId(), HttpMethod.PUT, new HttpEntity<>(actual), Faculty.class);
        Assertions.assertThat(updated.getBody().getId()).isNotNull();
        Assertions.assertThat(updated.getBody().getName()).isEqualTo("Cat");
        Assertions.assertThat(updated.getBody().getColor()).isEqualTo("Red");
        }

    @Test
    public void testDeleteFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Zoo");
        faculty.setColor("Pink");
        Faculty actual = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, Faculty.class);
        ResponseEntity <String> response = restTemplate.exchange
                ("http://localhost:" + port + "/faculty/" + actual.getId(), HttpMethod.DELETE, null, String.class);
        Faculty f = restTemplate.getForObject("http://localhost:" + port + "/faculty/" +
                actual.getId(), Faculty.class);
        Assertions.assertThat(f).isNull();
    }

@BeforeEach
void setup(){
        facultyRepository.deleteAll();
}
    @Test
    public void testGetFacultyByColor() {
        String color = "Green";
        List <Faculty> expectedFaculties = new ArrayList<>();

        Faculty faculty = new Faculty(null, "name", color,null);
        expectedFaculties.add(faculty);
        facultyRepository.save(faculty);

        ResponseEntity<List<Faculty>> response = restTemplate.exchange(
                "http://localhost:" + port + "/faculty/color/{color}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Faculty>>() {},
                color);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<Faculty> actualFaculties = response.getBody();
        assertThat(actualFaculties).isEqualTo(expectedFaculties);
    }

    @Test
    public void testGetStudentByFaculty() {
        Long facultyId = 1L;
        List <Student> expectedStudents = new ArrayList<>();
        expectedStudents.add(new Student(1L, "John", 1));

        ResponseEntity<List<Student>> response = restTemplate.exchange(
                "http://localhost:" + port + "/faculty/{id}/students",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Student>>() {},
                facultyId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<Student> actualStudents = response.getBody();
        assertThat(actualStudents).isEqualTo(expectedStudents);
    }
}