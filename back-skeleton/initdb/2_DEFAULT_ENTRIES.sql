-- -----------------------------
-- UTILISATEURS (users)
-- -----------------------------
INSERT INTO users (username, password, email, role)
VALUES
    ('admin', 'admin123', 'admin@example.com', 'ADMIN'),
    ('player1', 'player123', 'player1@example.com', 'PLAYER'),
    ('player2', 'player234', 'player2@example.com', 'PLAYER');

-- -----------------------------
-- QUIZZES
-- -----------------------------
INSERT INTO quizzes (title, description, time_limit_per_question_seconds, created_by)
VALUES
    ('quizzes Science', 'quizzes sur la science générale', 20, 1),
    ('quizzes Histoire', 'quizzes sur l’histoire mondiale', 20, 1),
    ('quizzes Sport', 'quizzes sur le sport', 20, 1);

-- -----------------------------
-- QUESTIONS
-- Chaque quiz a 2 questions pour l'exemple
-- -----------------------------
INSERT INTO questions (quiz_id, text, type, points, choices, correct_answer)
VALUES
-- quizzes Science
(1, 'Quelle planète est la plus proche du Soleil ?', 'choix_multiple', 1,
 '[ "Venus", "Mercure", "Terre", "Mars" ]', 'Mercure'),
(1, 'Quel gaz les plantes absorbent-elles ?', 'choix_multiple', 1,
 '[ "Oxygène", "Carbone", "Dioxyde de carbone", "Hydrogène" ]', 'Dioxyde de carbone'),
-- quizzes Histoire
(2, 'Qui a découvert l’Amérique ?', 'choix_multiple', 1,
 '[ "Christophe Colomb", "Napoléon", "Jules César", "Magellan" ]', 'Christophe Colomb'),
(2, 'En quelle année la Révolution française a-t-elle commencé ?', 'choix_multiple', 1,
 '[ "1789", "1776", "1804", "1812" ]', '1789'),
-- quizzes Sport
(3, 'Combien de joueurs dans une équipe de football ?', 'choix_multiple', 1,
 '[ "9", "10", "11", "12" ]', '11'),
(3, 'Combien de sets maximum dans un match de tennis masculin ?', 'choix_multiple', 1,
 '[ "3", "4", "5", "7" ]', '5');

-- -----------------------------
-- SCORES
-- -----------------------------
INSERT INTO scores (user_id, quiz_id, score, time_taken)
VALUES
    (2, 1, 8, 160),
    (2, 2, 7, 180),
    (2, 3, 9, 120),
    (3, 1, 6, 200),
    (3, 2, 5, 210);
