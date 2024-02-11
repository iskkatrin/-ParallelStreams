CREATE TABLE Person (
    person_id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age INT,
    has_license BOOLEAN
);

CREATE TABLE Car (
                     car_id INT PRIMARY KEY,
                     brand VARCHAR(50) NOT NULL,
                     model VARCHAR(50) NOT NULL,
                     price DECIMAL(10,2) NOT NULL
);


CREATE TABLE Ownership (
                           person_id INT,
                           car_id INT,
                           PRIMARY KEY (person_id, car_id),
                           FOREIGN KEY (person_id) REFERENCES Person (person_id),
                           FOREIGN KEY (car_id) REFERENCES Car(car_id)
);