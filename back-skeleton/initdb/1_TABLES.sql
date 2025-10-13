-- 1_TABLES.sql (version PostgreSQL)

-- Création des séquences automatiques avec SERIAL
CREATE TABLE majors (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        description TEXT
);

CREATE TABLE students (
                          id SERIAL PRIMARY KEY,
                          first_name VARCHAR(100),
                          last_name VARCHAR(100),
                          birthdate DATE,
                          major_id INT,
                          FOREIGN KEY (major_id) REFERENCES majors(id)
);

CREATE TABLE courses (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(100),
                         hours INT
);

CREATE TABLE student_course (
                                student_id INT,
                                course_id INT,
                                PRIMARY KEY (student_id, course_id),
                                FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
                                FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

-- Insertion des données
DO $$
    DECLARE
        _STUDENT_1 INT;
        _MAJOR_1 INT;
        _COURSE_1 INT;
    BEGIN
        INSERT INTO majors (name, description)
        VALUES ('Informatique', 'Ouaiiis du code partout')
        RETURNING id INTO _MAJOR_1;

        INSERT INTO majors (name, description) VALUES
                                                   ('Construction', 'Beaucoup de béton et des poutres'),
                                                   ('Aéronautique', 'Vive le vent'),
                                                   ('Data', 'Trop cool plein de données à ordonner'),
                                                   ('Energie & Environnement', 'On est full green'),
                                                   ('Management', 'Des managers de qualité'),
                                                   ('Santé', 'On connait tous les os et les muscles du corps humain'),
                                                   ('Architecture durable', 'Objectif 0 carbone'),
                                                   ('Design Industriel Durable', 'On résistera à la fin du pétrole');

        INSERT INTO students (first_name, last_name, birthdate, major_id)
        VALUES ('Paul', 'Harrohide', '2002-06-15', _MAJOR_1)
        RETURNING id INTO _STUDENT_1;

        INSERT INTO students (first_name, last_name, birthdate, major_id)
        VALUES
            ('Jean', 'Bonbeur', '2001-08-21', _MAJOR_1),
            ('Alain', 'Térieur', '2000-01-11', _MAJOR_1);

        INSERT INTO courses (name, hours)
        VALUES ('Java', 30)
        RETURNING id INTO _COURSE_1;

        INSERT INTO courses (name, hours) VALUES
                                              ('German', 30),
                                              ('Internet of Things', 30),
                                              ('Thermodynamic', 30),
                                              ('Anatomy', 30),
                                              ('Maths', 30),
                                              ('Spanish', 30),
                                              ('Lean Management', 30);

        INSERT INTO student_course (student_id, course_id)
        VALUES (_STUDENT_1, _COURSE_1);
    END $$;
