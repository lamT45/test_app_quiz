-- Nettoyage propre avant insertion
TRUNCATE TABLE scores, questions, quizzes, users RESTART IDENTITY CASCADE;

-- USERS
INSERT INTO users (username, password, email, role) VALUES
                                                        ('admin', 'admin123', 'admin@example.com', 'ADMIN'),
                                                        ('player1', 'player123', 'player1@example.com', 'PLAYER'),
                                                        ('player2', 'player234', 'player2@example.com', 'PLAYER'),
                                                        ('player3', 'player345', 'player3@example.com', 'PLAYER'),
                                                        ('player4', 'player456', 'player4@example.com', 'PLAYER'),
                                                        ('player5', 'player567', 'player5@example.com', 'PLAYER'),
                                                        ('player6', 'player678', 'player6@example.com', 'PLAYER');


-- QUIZZES
INSERT INTO quizzes (title, category, description, level, players, duration, created_by_id)
VALUES
    ('Culture Générale', 'Culture Générale', 'Testez vos connaissances générales avec ce quiz pour débutants', 'Facile', 1234, 35, 1),
    ('Les Capitales du Monde', 'Géographie', 'Connaissez-vous toutes les capitales ? Prouvez-le !', 'Moyen', 856, 25, 1),
    ('Histoire de France', 'Histoire', 'De la Gaule à nos jours, testez vos connaissances historiques', 'Difficile', 543, 15, 1),
    ('Sciences et Technologies', 'Sciences', 'Physique, chimie, biologie... êtes-vous un scientifique ?', 'Moyen', 978, 25, 1),
    ('Cinéma et Séries TV', 'Divertissement', 'Êtes-vous un vrai cinéphile ? Testez vos connaissances !', 'Facile', 1567, 35, 1),
    ('Sport - Champions et Records', 'Sport', 'Connaissez-vous les plus grands exploits sportifs ?', 'Moyen', 876, 25, 1);

-- SCORES
INSERT INTO scores (user_id, quiz_id, score_obtained, time_taken_seconds) VALUES
                                                                              (2, 1, 8, 160),
                                                                              (4, 3, 9, 120),
                                                                              (6, 2, 5, 210),
                                                                              (7, 3, 6, 220),
                                                                              (5, 2, 7, 180),
                                                                              (3, 2, 6, 200);
