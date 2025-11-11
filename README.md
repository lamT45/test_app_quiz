# 🧠 QuizMaster – Application Web (Java / Angular / Docker / PostgreSQL)

## 👥 Équipe

Ce projet est réalisé par :
- Safae BERRICHI
- Lamyae TALA
- Xiner GU

---

## 📋 Description du projet

QuizMaster est une application web interactive développée en Java (Spring Boot) et *Angular, inspirée de *Kahoot!.

L’application repose sur une architecture 3-tiers :
- Frontend Angular : interface utilisateur réactive et dynamique
- Backend Spring Boot : logique métier, gestion des utilisateurs, quiz, scores et évaluations
- Base de données PostgreSQL : stockage des données, conteneurisée via Docker

L’interface propose différents quiz prédéfinis classés par thèmes et niveaux de difficulté.  
Chaque joueur peut choisir :
- un sujet parmi :  
  🎬 Cinéma et Séries TV,  
  🧪 Sciences et Technologies,  
  🗺 Les Capitales du Monde,  
  🧠 Culture Générale,  
  ⚽ Sport – Champions et Records,  
  🏛 Histoire de France
- un niveau de difficulté : Facile, Moyen ou Difficile

---

### ⏱ Temps imparti selon la difficulté

| Niveau | Temps de réponse |
|:--------|:----------------|
| 🟢 Facile | 35 secondes |
| 🟡 Moyen | 25 secondes |
| 🔴 Difficile | 15 secondes |

---

### 💯 Système de score, classement et notation

Le système de score attribue des points en fonction :
- de la justesse des réponses
- et de la rapidité : plus la réponse est donnée vite, plus le score augmente.

À la fin de chaque partie :
- le joueur obtient un score individuel
- un classement général unique affiche la moyenne des scores obtenus dans tous les quiz confondus
- le joueur peut évaluer le quiz avec un système de notation (rating) sur 5 ⭐ étoiles.

Les scores, évaluations et historiques sont enregistrés dans une base de données PostgreSQL initialisée automatiquement via Docker.

---

## 🛠 Panneau d’Administration

Le panneau d’administration permet de gérer entièrement le contenu et les utilisateurs du système :

### 🎯 Gestion des Quiz
- Chaque sujet comporte 10 questions.
- Chaque question possède :
    - un énoncé clair,
    - 4 choix de réponses (dont une seule correcte),
    - et 1 ou 2 questions Vrai/Faux intégrées par sujet.
- Le temps de réponse attribué dépend du niveau de difficulté sélectionné :  
  Facile (35s), Moyen (25s), Difficile (15s).
- Les administrateurs peuvent :
    - Créer, modifier ou supprimer un quiz complet,
    - Ajouter ou supprimer des questions dans un quiz,
    - Modifier les réponses ou changer la difficulté.

### 👥 Gestion des Utilisateurs
- Possibilité de créer, modifier ou supprimer des utilisateurs.
- La suppression d’un utilisateur entraîne également la suppression automatique de ses scores et participations dans tous les quiz.
- L’administrateur peut consulter la liste complète des utilisateurs, leurs **scores moyens, et leurs **évaluations (ratings) laissées sur les quiz.



## 🗂 Architecture exacte du dépôt


QuizMaster/
├── back-skeleton/                                  # 🧠 Backend — Spring Boot (API REST)
│   ├── pom.xml
│   ├── mvnw / mvnw.cmd
│   ├── docker-compose.yml                          # PostgreSQL (service: api_database)
│   ├── .env.sample                                 # DATABASE_NAME / USER / PASSWORD
│   ├── initdb/
│   │   ├── 1_TABLES.sql
│   │   ├── 2_DEFAULT_ENTRIES.sql
│   │   └── 3_SEED_QUESTIONS.sql
│   └── src/
│       └── main/
│           ├── java/com/app_quiz/backskeleton/
│           │   ├── controllers/
│           │   │   ├── AuthController.java         # 🔐 login/register
│           │   │   ├── QuizController.java         # 🎯 endpoints publics quiz
│           │   │   ├── QuestionController.java     # ❓ endpoints publics questions
│           │   │   ├── ScoreController.java        # 🏆 scores / leaderboard
│           │   │   └── admin/                      # 🧑‍💻 endpoints admin (CRUD)
│           │   │       ├── QuizAdminController.java
│           │   │       ├── QuestionAdminController.java
│           │   │       └── UserAdminController.java
│           │   │
│           │   ├── services/
│           │   │   ├── QuizService.java
│           │   │   ├── QuestionService.java
│           │   │   ├── ScoreService.java
│           │   │   └── UserService.java
│           │   │
│           │   ├── DAO/
│           │   │   ├── QuizDao.java
│           │   │   ├── QuestionDao.java            # contient findByQuiz_Id(...) ou @Query
│           │   │   ├── UserDao.java
│           │   │   └── ScoreDao.java
│           │   │
│           │   ├── models/
│           │   │   ├── User.java
│           │   │   ├── Quiz.java
│           │   │   ├── Question.java               # expose getQuizId() pour le JSON
│           │   │   └── Score.java
│           │   │
│           │   ├── DTO/
│           │   │   ├── QuizDto.java
│           │   │   ├── QuestionDto.java
│           │   │   └── ScoreDto.java
│           │   │
│           │   └── config/
│           │       └── CorsConfig.java
│           │
│           └── resources/
│               └── application.properties          # server.port=8082 + datasource
│
├── front-skeleton/                                 # 💻 Frontend — Angular
│   ├── package.json
│   ├── angular.json / tsconfig.json
│   └── src/
│       ├── main.ts
│       ├── index.html / styles.scss
│       └── app/
│           ├── app.module.ts                       # 📦 module racine
│           ├── app-routing.module.ts               # 🧭 routes principales (/home, /quiz, /admin, ...)
│           │
│           ├── components/
│           │   ├── home/                           # page d’accueil publique
│           │   └── auth/                           # login & register (si hors /auth)
│           │
│           ├── auth/
│           │   ├── login/
│           │   └── register/
│           │
│           ├── quizzes/
│           │   ├── quiz-list/
│           │   ├── quiz-detail/
│           │   └── quiz-play/
│           │
│           ├── scores/
│           │   └── leaderboard/
│           │
│           ├── admin/                              # 🧑‍💻 dashboard admin (lazy ou non)
│           │   ├── admin-dashboard/
│           │   │   ├── admin-dashboard.component.ts / html / scss
│           │   │   └── (héberge le layout: header + sidebar + <router-outlet>)
│           │   ├── manage-quizzes/
│           │   │   ├── manage-quizzes.component.ts / html / scss
│           │   ├── manage-questions/
│           │   │   ├── manage-questions.component.ts / html / scss
│           │   └── manage-users/
│           │       ├── manage-users.component.ts / html / scss
│           │
│           ├── guards/
│           │   └── auth.guards.ts                  # protège /play/:id et /admin/**
│           │
│           ├── models/
│           │   ├── user.model.ts
│           │   ├── quiz.model.ts
│           │   └── question.model.ts               # (quizId, text/type/points/choices...)
│           │
│           └── services/
│               ├── auth.service.ts                 # login/register + currentUser/redirect
│               ├── quiz.service.ts                 # endpoints publics
│               ├── score.service.ts
│               ├── quiz-admin.service.ts           # /api/admin/quizzes (create/update/delete/getAll)
│               ├── question-admin.service.ts       # (optionnel si externalisé)
│               └── user-admin.service.ts
│
├── README.md
└── .gitignore



---

## ⚙ Prérequis

Avant de lancer le projet, installez :

- 🐳 Docker Desktop (ou Docker Engine)
- 🟢 Node.js ≥ 18 et npm
- ☕ Java JDK 17+ et Maven (ou mvnw fourni)

---

## 🧩 Configuration des variables d’environnement (Backend)

Dupliquez back-skeleton/.env.sample vers back-skeleton/.env puis renseignez :

env
DATABASE_USER=root
DATABASE_PASSWORD=toor
DATABASE_NAME=defaultdb


> Le backend lit ces variables pour se connecter à PostgreSQL (voir application.properties).  
> Port backend par défaut : 8082

---

## ▶ Comment lancer le Backend (Spring Boot + PostgreSQL)

### 1️⃣ Démarrer PostgreSQL avec Docker

bash
cd back-skeleton
docker-compose up -d   # lance le conteneur api_database (PostgreSQL, port 5432)


### 2️⃣ Initialiser la base de données

> Exécutez toujours les trois scripts dans cet ordre précis 1️⃣ 2️⃣ 3️⃣ :

bash
docker cp initdb/1_TABLES.sql api_database:/1_TABLES.sql
docker cp initdb/2_DEFAULT_ENTRIES.sql api_database:/2_DEFAULT_ENTRIES.sql
docker cp initdb/3_SEED_QUESTIONS.sql api_database:/3_SEED_QUESTIONS.sql

docker exec -i api_database psql -U ${DATABASE_USER} -d ${DATABASE_NAME} -f /1_TABLES.sql
docker exec -i api_database psql -U ${DATABASE_USER} -d ${DATABASE_NAME} -f /2_DEFAULT_ENTRIES.sql
docker exec -i api_database psql -U ${DATABASE_USER} -d ${DATABASE_NAME} -f /3_SEED_QUESTIONS.sql


> 💡 Astuce Windows PowerShell : remplacez les variables par leurs valeurs si $env:DATABASE_USER n’est pas reconnu.

### 3️⃣ Démarrer l’API Spring Boot

bash
# Option A : avec le wrapper Maven fourni
./mvnw spring-boot:run

# Option B : Maven installé sur la machine
mvn spring-boot:run


- API accessible sur http://localhost:8082
- Connexion DB : jdbc:postgresql://localhost:5432/${DATABASE_NAME}

---

## 💻 Comment lancer le Frontend (Angular)

bash
cd front-skeleton
npm install
npm start      # équivaut à: ng serve


- Interface accessible sur http://localhost:4200
- Le frontend communique avec l’API http://localhost:8082

---

## 🚀 Comment lancer l’application complète (ordre recommandé)

1️⃣ Backend / Base de données
bash
cd back-skeleton
docker-compose up -d
./mvnw spring-boot:run


2️⃣ Frontend
bash
cd ../front-skeleton
npm install
npm start


3️⃣ Accès
- UI : http://localhost:4200
- API : http://localhost:8082

---

## 🧪 Vérification rapide

- GET http://localhost:8082/api/quiz, /score, /user, /ratings
- L’interface affiche la liste des quiz, les questions, les évaluations ⭐ et le classement général unique.

---

## 🧭 Endpoints API principaux

| Contrôleur | Méthode | Endpoint | Description |
|-------------|----------|-----------|--------------|
| AuthController | POST | /auth/login | Authentification d’un utilisateur |
| UserController | GET | /users | Liste des utilisateurs |
| QuizController | GET | /quiz | Récupération des quiz disponibles |
| QuestionController | GET | /questions/{id} | Récupération des questions d’un quiz |
| ScoreController | GET | /scores | Récupération du classement global |
| RatingController | POST | /ratings | Ajout d’une évaluation (rating) |
| RatingController | GET | /ratings/{quizId} | Récupération de la moyenne d’un quiz |

---

## 🔧 Dépannage

| Problème | Cause probable | Correctif |
|-----------|----------------|-----------|
| Connection refused localhost:5432 | Conteneur DB arrêté | docker ps / docker start api_database |
| Quiz ou scores vides | Scripts SQL non exécutés | Rejouer les 3 scripts via docker exec ... psql -f |
| Port 8082 occupé | Process déjà actif | Modifier server.port dans application.properties |
| npm start échoue | Conflit de dépendances | Supprimer node_modules puis npm install |

---

## 📸 Démonstration

Placez vos images dans docs/img/ et référencez-les ainsi :

markdown
![Accueil](docs/img/home.png)
![Sélection du quiz](docs/img/select_quiz.png)
![Question](docs/img/question.png)
![Évaluation](docs/img/rating.png)
![Classement général](docs/img/ranking.png)