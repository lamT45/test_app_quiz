-- 3_SEED_QUESTIONS.sql
-- Inserts 10 questions per quiz for the 6 default quizzes inserted in 2_DEFAULT_ENTRIES.sql.
-- Assumes quizzes ids are 1..6 in the order they are inserted there.

-- ===== Culture Générale - Niveau Facile (quiz_id = 1) =====
INSERT INTO questions (quiz_id, question_text, type, points, choice1, choice2, choice3, choice4, correct_answer, true_false) VALUES
(1, 'Quelle est la capitale de la France ?', 'Quiz', 5, 'Lyon', 'Paris', 'Marseille', 'Nice', 'Paris', FALSE),
(1, 'Combien font 2 + 2 ?', 'Quiz', 5, '3', '4', '5', '6', '4', FALSE),
(1, 'La Tour Eiffel a été inaugurée en 1889.', 'Vrai/Faux', 2, 'Vrai', 'Faux', NULL, NULL, 'Vrai', TRUE),
(1, 'Quel océan borde la côte ouest de l''Afrique ?', 'Quiz', 5, 'Atlantique', 'Pacifique', 'Indien', 'Arctique', 'Atlantique', FALSE),
(1, 'Le symbole chimique de l''eau est H2O.', 'Vrai/Faux', 2, 'Vrai', 'Faux', NULL, NULL, 'Vrai', TRUE),
(1, 'Quel pays est surnommé "le pays du Soleil-Levant" ?', 'Quiz', 5, 'Chine', 'Japon', 'Corée du Sud', 'Thaïlande', 'Japon', FALSE),
(1, 'La Terre a une lune appelée "Luna".', 'Vrai/Faux', 2, 'Vrai', 'Faux', NULL, NULL, 'Vrai', TRUE),
(1, 'Quel est l''animal terrestre le plus rapide ?', 'Quiz', 5, 'Guépard', 'Lion', 'Gazelle', 'Tigre', 'Guépard', FALSE),
(1, 'Combien y a-t-il de continents ?', 'Quiz', 5, '5', '6', '7', '8', '7', FALSE),
(1, 'Qui a peint la Joconde ?', 'Quiz', 5, 'Michel-Ange', 'Leonard de Vinci', 'Raphaël', 'Botticelli', 'Leonard de Vinci', FALSE);

-- ===== Les Capitales du Monde (quiz_id = 2) =====
INSERT INTO questions (quiz_id, question_text, type, points, choice1, choice2, choice3, choice4, correct_answer, true_false) VALUES
(2, 'Capitale de l''Espagne ?', 'Quiz', 5, 'Madrid', 'Barcelone', 'Valence', 'Séville', 'Madrid', FALSE),
(2, 'Capitale du Canada ?', 'Quiz', 5, 'Toronto', 'Vancouver', 'Ottawa', 'Montréal', 'Ottawa', FALSE),
(2, 'Capitale de l''Australie ?', 'Quiz', 5, 'Sydney', 'Canberra', 'Melbourne', 'Perth', 'Canberra', FALSE),
(2, 'Capitale du Maroc ?', 'Quiz', 5, 'Casablanca', 'Rabat', 'Fès', 'Marrakech', 'Rabat', FALSE),
(2, 'La capitale de la Suisse est Zurich.', 'Vrai/Faux', 2, 'Vrai', 'Faux', NULL, NULL, 'Faux', TRUE),
(2, 'Capitale de la Turquie ?', 'Quiz', 5, 'Istanbul', 'Ankara', 'Izmir', 'Bursa', 'Ankara', FALSE),
(2, 'Capitale de l''Afrique du Sud ?', 'Quiz', 5, 'Pretoria', 'Le Cap', 'Johannesburg', 'Bloemfontein', 'Pretoria', FALSE),
(2, 'La capitale de la Nouvelle-Zélande est Wellington.', 'Vrai/Faux', 2, 'Vrai', 'Faux', NULL, NULL, 'Vrai', TRUE),
(2, 'Capitale de la Norvège ?', 'Quiz', 5, 'Oslo', 'Bergen', 'Trondheim', 'Stavanger', 'Oslo', FALSE),
(2, 'Capitale du Brésil ?', 'Quiz', 5, 'Brasília', 'Rio de Janeiro', 'São Paulo', 'Salvador', 'Brasília', FALSE);

-- ===== Histoire de France (quiz_id = 3) =====
INSERT INTO questions (quiz_id, question_text, type, points, choice1, choice2, choice3, choice4, correct_answer, true_false) VALUES
(3, 'En quelle année a eu lieu la Révolution française ?', 'Quiz', 5, '1776', '1789', '1799', '1815', '1789', FALSE),
(3, 'Qui a été couronné Empereur des Français en 1804 ?', 'Quiz', 5, 'Louis XVI', 'Napoléon Bonaparte', 'Charles X', 'Louis-Philippe', 'Napoléon Bonaparte', FALSE),
(3, 'Jeanne d''Arc a vécu au XVe siècle.', 'Vrai/Faux', 2, 'Vrai', 'Faux', NULL, NULL, 'Vrai', TRUE),
(3, 'Quel roi a fait construire le Château de Versailles tel qu''on le connaît ?', 'Quiz', 5, 'Louis XIV', 'Henri IV', 'François Ier', 'Louis XIII', 'Louis XIV', FALSE),
(3, 'La bataille de Verdun a eu lieu pendant la Première Guerre mondiale.', 'Vrai/Faux', 2, 'Vrai', 'Faux', NULL, NULL, 'Vrai', TRUE),
(3, 'Qui a fondé la Ve République en 1958 ?', 'Quiz', 5, 'Charles de Gaulle', 'Georges Pompidou', 'François Mitterrand', 'Jacques Chirac', 'Charles de Gaulle', FALSE),
(3, 'La prise de la Bastille a eu lieu le 14 juillet.', 'Vrai/Faux', 2, 'Vrai', 'Faux', NULL, NULL, 'Vrai', TRUE),
(3, 'Quel traité met fin à la guerre de Cent Ans ?', 'Quiz', 5, 'Traité de Verdun', 'Traité de Troyes', 'Traité de Brétigny', 'Traité de Paris (1259)', 'Traité de Troyes', FALSE),
(3, 'Qui fut le premier Président de la République française ?', 'Quiz', 5, 'Jules Grévy', 'Adolphe Thiers', 'Louis-Napoléon Bonaparte', 'Patrice de Mac Mahon', 'Louis-Napoléon Bonaparte', FALSE),
(3, 'En 1944, le droit de vote est accordé aux femmes en France.', 'Quiz', 5, 'Vrai', 'Faux', NULL, NULL, 'Vrai', TRUE);

-- ===== Sciences et Technologies (quiz_id = 4) =====
INSERT INTO questions (quiz_id, question_text, type, points, choice1, choice2, choice3, choice4, correct_answer, true_false) VALUES
(4, 'Quelle planète est la plus proche du Soleil ?', 'Quiz', 5, 'Vénus', 'Mercure', 'Terre', 'Mars', 'Mercure', FALSE),
(4, 'L''unité de mesure de la force est le Newton.', 'Vrai/Faux', 2, 'Vrai', 'Faux', NULL, NULL, 'Vrai', TRUE),
(4, 'Qui a formulé la théorie de la relativité ?', 'Quiz', 5, 'Isaac Newton', 'Albert Einstein', 'Galilée', 'Niels Bohr', 'Albert Einstein', FALSE),
(4, 'L''ADN est une molécule en double hélice.', 'Vrai/Faux', 2, 'Vrai', 'Faux', NULL, NULL, 'Vrai', TRUE),
(4, 'Quel est l''organe responsable de la production d''insuline ?', 'Quiz', 5, 'Foie', 'Pancréas', 'Rate', 'Reins', 'Pancréas', FALSE),
(4, 'Quelle est la vitesse de la lumière dans le vide (approx.) ?', 'Quiz', 5, '3×10^6 m/s', '3×10^8 m/s', '3×10^5 m/s', '3×10^7 m/s', '3×10^8 m/s', FALSE),
(4, 'Le langage Python a été créé avant Java.', 'Vrai/Faux', 2, 'Vrai', 'Faux', NULL, NULL, 'Faux', TRUE),
(4, 'Quel gaz est le plus abondant dans l''atmosphère terrestre ?', 'Quiz', 5, 'Oxygène', 'Azote', 'CO2', 'Argon', 'Azote', FALSE),
(4, 'Quelle particule a une charge négative ?', 'Quiz', 5, 'Proton', 'Électron', 'Neutron', 'Photon', 'Électron', FALSE),
(4, 'Quel est le langage de base du web côté navigateur ?', 'Quiz', 5, 'C++', 'Java', 'JavaScript', 'Go', 'JavaScript', FALSE);

-- ===== Cinéma et Séries TV (quiz_id = 5) =====
INSERT INTO questions (quiz_id, question_text, type, points, choice1, choice2, choice3, choice4, correct_answer, true_false) VALUES
(5, 'Qui a réalisé "Inception" ?', 'Quiz', 5, 'Steven Spielberg', 'Christopher Nolan', 'James Cameron', 'Ridley Scott', 'Christopher Nolan', FALSE),
(5, 'Dans "Friends", le nom du café est Central Perk.', 'Vrai/Faux', 2, 'Vrai', 'Faux', NULL, NULL, 'Vrai', TRUE),
(5, 'Quel film a remporté l''Oscar du Meilleur Film en 2020 ?', 'Quiz', 5, '1917', 'Parasite', 'Joker', 'Once Upon a Time in Hollywood', 'Parasite', FALSE),
(5, 'L''acteur principal de "The Matrix" est Keanu Reeves.', 'Vrai/Faux', 2, 'Vrai', 'Faux', NULL, NULL, 'Vrai', TRUE),
(5, 'Quel réalisateur est à l''origine de "Pulp Fiction" ?', 'Quiz', 5, 'Quentin Tarantino', 'Martin Scorsese', 'Guy Ritchie', 'Danny Boyle', 'Quentin Tarantino', FALSE),
(5, 'Dans "Harry Potter", la maison d''Hermione au départ est ?', 'Quiz', 5, 'Serdaigle', 'Gryffondor', 'Poufsouffle', 'Serpentard', 'Gryffondor', FALSE),
(5, 'La série "Breaking Bad" se déroule au Nouveau-Mexique.', 'Vrai/Faux', 2, 'Vrai', 'Faux', NULL, NULL, 'Vrai', TRUE),
(5, 'Quel film d''animation est un succès Pixar de 2004 ?', 'Quiz', 5, 'Les Indestructibles', 'Ratatouille', 'Cars', 'Vice-Versa', 'Les Indestructibles', FALSE),
(5, 'Qui joue Jack dans "Titanic" ?', 'Quiz', 5, 'Leonardo DiCaprio', 'Brad Pitt', 'Tom Cruise', 'Matt Damon', 'Leonardo DiCaprio', FALSE),
(5, 'Quel est le vrai nom de "The Rock" ?', 'Quiz', 5, 'Dwayne Johnson', 'Vin Diesel', 'Jason Statham', 'John Cena', 'Dwayne Johnson', FALSE);

-- ===== Sport - Champions et Records (quiz_id = 6) =====
INSERT INTO questions (quiz_id, question_text, type, points, choice1, choice2, choice3, choice4, correct_answer, true_false) VALUES
(6, 'Quel footballeur détient le plus de Ballons d''Or ?', 'Quiz', 5, 'Cristiano Ronaldo', 'Lionel Messi', 'Michel Platini', 'Johan Cruyff', 'Lionel Messi', FALSE),
(6, 'Usain Bolt détient le record du 100 m.', 'Vrai/Faux', 2, 'Vrai', 'Faux', NULL, NULL, 'Vrai', TRUE),
(6, 'Quel pays a remporté la Coupe du Monde 2018 ?', 'Quiz', 5, 'Brésil', 'Allemagne', 'France', 'Argentine', 'France', FALSE),
(6, 'Rafael Nadal a remporté plus de titres à Roland-Garros que Roger Federer.', 'Vrai/Faux', 2, 'Vrai', 'Faux', NULL, NULL, 'Vrai', TRUE),
(6, 'Quel club a remporté le plus de Ligues des Champions ?', 'Quiz', 5, 'Barcelone', 'Real Madrid', 'Liverpool', 'Milan AC', 'Real Madrid', FALSE),
(6, 'Quel est le sport de Michael Phelps ?', 'Quiz', 5, 'Athlétisme', 'Natation', 'Cyclisme', 'Gymnastique', 'Natation', FALSE),
(6, 'La NBA est la ligue de basket nord-américaine.', 'Vrai/Faux', 2, 'Vrai', 'Faux', NULL, NULL, 'Vrai', TRUE),
(6, 'Combien de joueurs sur le terrain par équipe au football ?', 'Quiz', 5, '9', '10', '11', '12', '11', FALSE),
(6, 'Quel tennisman est surnommé "Djoker" ?', 'Quiz', 5, 'Nadal', 'Djokovic', 'Federer', 'Murray', 'Djokovic', FALSE),
(6, 'Dans quel pays se déroule le Tour de France ?', 'Quiz', 5, 'Italie', 'Espagne', 'France', 'Suisse', 'France', FALSE);
