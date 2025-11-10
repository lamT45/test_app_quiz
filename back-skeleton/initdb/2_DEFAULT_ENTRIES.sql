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
INSERT INTO quizzes
(title, category, description, level, players, duration, rating, rating_count, created_by_id)
VALUES
    ('Culture Générale', 'Culture Générale', 'Testez vos connaissances générales avec ce quiz pour débutants', 'Facile', 4, 35, 2.0, 1, 1),
    ('Les Capitales du Monde', 'Géographie', 'Connaissez-vous toutes les capitales ? Prouvez-le !', 'Moyen', 4, 25, 1.5, 2, 1),
    ('Histoire de France', 'Histoire', 'De la Gaule à nos jours, testez vos connaissances historiques', 'Difficile', 4, 15, 3.0, 3, 1),
    ('Sciences et Technologies', 'Sciences', 'Physique, chimie, biologie... êtes-vous un scientifique ?', 'Moyen', 4, 25, 2.5, 4, 1),
    ('Cinéma et Séries TV', 'Divertissement', 'Êtes-vous un vrai cinéphile ? Testez vos connaissances !', 'Facile', 4, 35, 3.5, 5, 1),
    ('Sport - Champions et Records', 'Sport', 'Connaissez-vous les plus grands exploits sportifs ?', 'Moyen', 4, 25, 4.0, 2, 1);


-- SCORES
INSERT INTO scores (user_id, quiz_id, score_obtained, time_taken_seconds) VALUES
-- 🌍 Culture Générale (Facile, 35s)
(2, 1, 48, 28),  -- rapide
(3, 1, 42, 33),  -- proche de la limite
(4, 1, 35, 34),  -- lent
(5, 1, 50, 26),  -- parfait et rapide

-- 🏙️ Capitales du Monde (Moyen, 25s)
(2, 2, 44, 21),  -- rapide et bon
(3, 2, 40, 24),
(6, 2, 36, 25),  -- juste à temps
(7, 2, 48, 20),  -- excellent

-- 🏰 Histoire de France (Difficile, 15s)
(2, 3, 39, 13),
(4, 3, 33, 15),  -- limite atteinte
(5, 3, 45, 11),
(6, 3, 28, 15),  -- lent et erreurs

-- ⚗️ Sciences & Technologies (Moyen, 25s)
(2, 4, 41, 22),
(3, 4, 47, 19),
(5, 4, 49, 18),
(7, 4, 37, 25),

-- 🎬 Cinéma & Séries TV (Facile, 35s)
(2, 5, 50, 27),
(4, 5, 43, 33),
(5, 5, 47, 29),
(6, 5, 45, 31),

-- ⚽ Sport - Champions & Records (Moyen, 25s)
(3, 6, 42, 22),
(4, 6, 47, 20),
(6, 6, 34, 25),
(7, 6, 44, 21);