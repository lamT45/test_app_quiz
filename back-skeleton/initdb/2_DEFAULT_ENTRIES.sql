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
    ('Culture Générale - Niveau Facile', 'Culture Générale', 'Testez vos connaissances générales avec ce quiz pour débutants', 'Facile', 1234, 45, 1),
    ('Les Capitales du Monde', 'Géographie', 'Connaissez-vous toutes les capitales ? Prouvez-le !', 'Moyen', 856, 30, 1),
    ('Histoire de France', 'Histoire', 'De la Gaule à nos jours, testez vos connaissances historiques', 'Difficile', 543, 15, 1),
    ('Sciences et Technologies', 'Sciences', 'Physique, chimie, biologie... êtes-vous un scientifique ?', 'Moyen', 978, 30, 1),
    ('Cinéma et Séries TV', 'Divertissement', 'Êtes-vous un vrai cinéphile ? Testez vos connaissances !', 'Facile', 1567, 45, 1),
    ('Sport - Champions et Records', 'Sport', 'Connaissez-vous les plus grands exploits sportifs ?', 'Moyen', 876, 30, 1);

-- QUESTIONS
INSERT INTO questions (quiz_id, question_text, type, points, choice1, choice2, choice3, choice4, correct_answer, true_false) VALUES
                                                                                                                                 (1, 'Quelle planète est la plus proche du Soleil ?', 'choix_multiple', 1, 'Venus', 'Mercure', 'Terre', 'Mars', 'Mercure', FALSE),
                                                                                                                                 (1, 'Quel gaz les plantes absorbent-elles ?', 'choix_multiple', 1, 'Oxygène', 'Carbone', 'Dioxyde de carbone', 'Hydrogène', 'Dioxyde de carbone', FALSE),
                                                                                                                                 (2, 'Qui a découvert l’Amérique ?', 'choix_multiple', 1, 'Christophe Colomb', 'Napoléon', 'Jules César', 'Magellan', 'Christophe Colomb', FALSE),
                                                                                                                                 (2, 'En quelle année la Révolution française a-t-elle commencé ?', 'choix_multiple', 1, '1789', '1776', '1804', '1812', '1789', FALSE),
                                                                                                                                 (3, 'Combien de joueurs dans une équipe de football ?', 'choix_multiple', 1, '9', '10', '11', '12', '11', FALSE),
                                                                                                                                 (3, 'Combien de sets maximum dans un match de tennis masculin ?', 'choix_multiple', 1, '3', '4', '5', '7', '5', FALSE);

-- SCORES
INSERT INTO scores (user_id, quiz_id, score_obtained, time_taken_seconds) VALUES
                                                                              (2, 1, 8, 160),
                                                                              (4, 3, 9, 120),
                                                                              (6, 2, 5, 210),
                                                                              (7, 3, 6, 220),
                                                                              (5, 2, 7, 180),
                                                                              (3, 2, 6, 200);
