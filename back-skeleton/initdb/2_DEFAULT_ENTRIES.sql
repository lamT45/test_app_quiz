-- 2-DEFAULT_ENTRIES.sql (version PostgreSQL)

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(100) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(150) NOT NULL UNIQUE,
                       role VARCHAR(50) DEFAULT 'user',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE quizzes (
                         id SERIAL PRIMARY KEY,
                         title VARCHAR(255) NOT NULL,
                         description TEXT,
                         time_limit_per_question_seconds INT,
                         created_by INT,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         leaderboard JSONB,
                         FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE SET NULL
);

CREATE TABLE questions (
                           id SERIAL PRIMARY KEY,
                           quiz_id INT NOT NULL,
                           text TEXT NOT NULL,
                           type VARCHAR(50) NOT NULL,
                           points INT DEFAULT 1,
                           choices JSONB,
                           image_url VARCHAR(255),
                           FOREIGN KEY (quiz_id) REFERENCES quizzes(id) ON DELETE CASCADE
);

CREATE TABLE scores (
                        id SERIAL PRIMARY KEY,
                        user_id INT NOT NULL,
                        quiz_id INT NOT NULL,
                        score FLOAT DEFAULT 0,
                        time_taken INT,
                        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                        FOREIGN KEY (quiz_id) REFERENCES quizzes(id) ON DELETE CASCADE
);
