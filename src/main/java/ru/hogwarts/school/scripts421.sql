ALTER TABLE student

    ADD CONSTRAINT age_constraint CHECK (age > 16);

ALTER  TABLE student
    ALTER COLUMN name SET NOT NULL;

ALTER TABLE student
    ADD CONSTRAINT name_unique UNIQUE (name);

ALTER TABLE faculty
       ADD CONSTRAINT unique_faculty_color UNIQUE (name, color);

ALTER TABLE Student
    ALTER COLUMN age SET DEFAULT 20;
