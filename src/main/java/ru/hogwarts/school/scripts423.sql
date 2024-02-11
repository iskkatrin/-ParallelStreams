SELECT student.name, student.age, faculty.name
FROM student
         JOIN faculty ON student.faculty_id = faculty.faculty_id
WHERE student.school = 'Hogwarts';

SELECT *
FROM student
         JOIN avatar ON student.id = avatar.student_id
