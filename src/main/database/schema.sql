DROP database IF EXISTS SchoolApp;
CREATE DATABASE IF NOT EXISTS SchoolApp;

CREATE TABLE IF NOT EXISTS teacher (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(40) NOT NULL,
    last_name VARCHAR (50) NOT NULL,
    date_of_birth DATE NOT NULL,
    hourly_wage INT NOT NULL
);

CREATE TABLE IF NOT EXISTS teacher_has_subject (
    subject_code INT NOT NULL,
    subject_name VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS student (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(40) NOT NULL,
    last_name VARCHAR (50) NOT NULL,
    date_of_birth DATE NOT NULL,
    grade INT NOT NULL,
    school VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS lesson (
    id INT PRIMARY KEY NOT NULL,
    topic VARCHAR(45),
    local_data_time DATETIME NOT NULL,
    duration INT NOT NULL,
    price INT NOT NULL,
    number_of_room INT NOT NULL
);

ALTER TABLE lesson
ADD teacher_id INT  NOT NULL,
 ADD FOREIGN KEY (teacher_id) REFERENCES teacher (id);


ALTER TABLE teacher
ADD name_of_subject VARCHAR (30),
ADD FOREIGN KEY (name_of_subject) REFERENCES teacher_has_subject (subject_name);

ALTER TABLE teacher_has_subject
ADD teacher_id INT NOT NULL,
ADD FOREIGN KEY (teacher_id) REFERENCES teacher (id);

CREATE TABLE IF NOT EXISTS student_has_lesson (
student_id INT NOT NULL,
lesson_id INT NOT NULL,
FOREIGN KEY (student_id) REFERENCES student (id),
FOREIGN KEY (lesson_id) REFERENCES lesson (id)
);