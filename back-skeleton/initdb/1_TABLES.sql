-- 1_TABLES.sql
DROP TABLE IF EXISTS scores CASCADE;
DROP TABLE IF EXISTS questions CASCADE;
DROP TABLE IF EXISTS quizzes CASCADE;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(150) NOT NULL UNIQUE,
                       role VARCHAR(50) DEFAULT 'user',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE quizzes (
                         id SERIAL PRIMARY KEY,
                         title VARCHAR(255) NOT NULL,
                         category VARCHAR(100),
                         description TEXT,
                         level VARCHAR(50),
                         players INT DEFAULT 0,
                         duration INT DEFAULT 0,
                         rating DOUBLE PRECISION DEFAULT 0.0,
                         rating_count INT DEFAULT 0,
                         created_by_id INT,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (created_by_id) REFERENCES users(id) ON DELETE SET NULL
);


CREATE TABLE questions (
                           id SERIAL PRIMARY KEY,
                           question_text VARCHAR(255),
                           choice1 VARCHAR(255),
                           choice2 VARCHAR(255),
                           choice3 VARCHAR(255),
                           choice4 VARCHAR(255),
                           correct_answer VARCHAR(255),
                           points INT,
                           type VARCHAR(100),
                           true_false BOOLEAN DEFAULT FALSE,
                           quiz_id INT,
                           FOREIGN KEY (quiz_id) REFERENCES quizzes(id)
);

CREATE TABLE scores (
                        id SERIAL PRIMARY KEY,
                        user_id INT NOT NULL,
                        quiz_id INT NOT NULL,
                        score_obtained INT DEFAULT 0,
                        time_taken_seconds INT,
                        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                        FOREIGN KEY (quiz_id) REFERENCES quizzes(id) ON DELETE CASCADE
);
