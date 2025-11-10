# 🧠 Quiz Game – Application Web (Java / Angular / Docker / PostgreSQL)

## 📋 Description du projet

**Quiz Game** est une application web interactive développée en **Java (Spring Boot)** et **Angular**, inspirée de *Kahoot!*.

L’application repose sur une **architecture 3-tiers** :
- **Frontend Angular** : interface utilisateur réactive et dynamique.
- **Backend Spring Boot** : logique métier, gestion des utilisateurs, quiz et scores.
- **Base de données PostgreSQL** : stockage des données, conteneurisée via **Docker**.

L’interface propose **différents sujets de quiz classés par niveaux de difficulté**.  
Chaque joueur peut choisir :
- un **sujet** parmi : 🎬 *Cinéma et Séries TV*, 📡 *Sciences et Technologies*, 🏛️ *Histoire de France*, et d'autres .....
- un **niveau de difficulté** : *Facile*, *Moyen* ou *Difficile*

### ⏱️ Temps imparti selon la difficulté

| Niveau | Temps de réponse |
|:--------|:----------------|
| 🟢 **Facile** | 35 secondes |
| 🟡 **Moyen** | 25 secondes |
| 🔴 **Difficile** | 15 secondes |

### 💯 Système de score et classement

Le système de score attribue des points en fonction :
- de la **justesse des réponses**,
- et de la **rapidité** : plus la réponse est donnée vite, plus le score augmente.

À la fin de chaque partie, un **classement général** affiche les meilleurs joueurs selon leur score cumulé.  
Les scores et historiques sont enregistrés dans une base de données **PostgreSQL** initialisée automatiquement via **Docker**.

---

## 👥 Équipe

- Lamyae TALA
- Safae BERRICHI
- Xiner GU

---

## 🗂️ Architecture exacte du dépôt

```
test_app_quiz/
├─ back-skeleton/                         # Backend Spring Boot
│  ├─ .env.sample                         # Exemple de configuration DB
│  ├─ docker-compose.yml                  # Service PostgreSQL (container api_database)
│  ├─ pom.xml                             # Dépendances Maven
│  ├─ mvnw / mvnw.cmd                     # Wrapper Maven
│  ├─ initdb/                             # Scripts SQL d'initialisation
│  │  ├─ 1_TABLES.sql
│  │  ├─ 2_DEFAULT_ENTRIES.sql
│  │  └─ 3_SEED_QUESTIONS.sql
│  └─ src/
│     ├─ main/java/com/app_quiz/backskeleton/
│     │  ├─ controllers/                  # AuthController, QuizController, QuestionController, ScoreController, UserController
│     │  ├─ services/                     # ScoreService, UserService, etc.
│     │  ├─ DAO/                          # Repositories JPA
│     │  ├─ models/                       # Entités JPA
│     │  └─ config/                       # CorsConfig
│     └─ main/resources/application.properties
│         # server.port=8082
│         # spring.datasource.url=jdbc:postgresql://localhost:5432/${DATABASE_NAME}
│         # spring.datasource.username=${DATABASE_USER}
│         # spring.datasource.password=${DATABASE_PASSWORD}
│
├─ front-skeleton/                        # Frontend Angular
│  ├─ package.json                        # scripts: start (ng serve), build, test
│  └─ src/app/
│     ├─ auth/                            # login/register
│     ├─ quiz/                            # liste & jeu
│     ├─ scores/leaderboard/              # classement
│     └─ services/                        # services REST
│
├─ .git
└─ README.md
```

---

## ⚙️ Prérequis

Avant de lancer le projet, installez :

- 🐳 **Docker Desktop** (ou Docker Engine)
- 🟢 **Node.js ≥ 18** et **npm**
- ☕ **Java JDK 17+** et **Maven** (ou `mvnw` fourni)

---

## 🧩 Configuration des variables d’environnement (Backend)

Dupliquez `back-skeleton/.env.sample` vers `back-skeleton/.env` puis renseignez :

```env
DATABASE_USER=root
DATABASE_PASSWORD=toor
DATABASE_NAME=defaultdb
```

> Le backend lit ces variables pour se connecter à PostgreSQL (voir `application.properties`).  
> Port backend par défaut : **8082**

---

## ▶️ Comment lancer **le Backend** (Spring Boot + PostgreSQL)

### 1️⃣ Démarrer PostgreSQL avec Docker

```bash
cd back-skeleton
docker-compose up -d   # lance le conteneur api_database (PostgreSQL, port 5432)
```

### 2️⃣ Initialiser la base de données

> Exécutez **toujours** les trois scripts dans cet ordre précis 1️⃣ 2️⃣ 3️⃣:

```bash
docker cp initdb/1_TABLES.sql api_database:/1_TABLES.sql
docker cp initdb/2_DEFAULT_ENTRIES.sql api_database:/2_DEFAULT_ENTRIES.sql
docker cp initdb/3_SEED_QUESTIONS.sql api_database:/3_SEED_QUESTIONS.sql

docker exec -i api_database psql -U ${DATABASE_USER} -d ${DATABASE_NAME} -f /1_TABLES.sql
docker exec -i api_database psql -U ${DATABASE_USER} -d ${DATABASE_NAME} -f /2_DEFAULT_ENTRIES.sql
docker exec -i api_database psql -U ${DATABASE_USER} -d ${DATABASE_NAME} -f /3_SEED_QUESTIONS.sql
```

> 💡 **Astuce Windows PowerShell :** remplacez les variables par leurs valeurs si `$env:DATABASE_USER` n’est pas reconnu.

### 3️⃣ Démarrer l’API Spring Boot

```bash
# Option A : avec le wrapper Maven fourni
./mvnw spring-boot:run

# Option B : Maven installé sur la machine
mvn spring-boot:run
```

- API accessible sur **http://localhost:8082**
- Connexion DB : `jdbc:postgresql://localhost:5432/${DATABASE_NAME}`

---

## 💻 Comment lancer **le Frontend** (Angular)

```bash
cd front-skeleton
npm install
npm start      # équivaut à: ng serve
```

- Interface accessible sur **http://localhost:4200**
- Le frontend communique avec l’API **http://localhost:8082**

---

## 🚀 Comment lancer **l’application complète** (ordre recommandé)

1️⃣ **Backend / Base de données**
```bash
cd back-skeleton
docker-compose up -d
./mvnw spring-boot:run
```

2️⃣ **Frontend**
```bash
cd ../front-skeleton
npm install
npm start
```

3️⃣ **Accès**
- UI : **http://localhost:4200**
- API : **http://localhost:8082**

---

## 🧪 Vérification rapide

- `GET http://localhost:8082/api/quiz`, `/score`, `/user`
- L’interface affiche la liste des quiz, les questions et le **leaderboard**.

---

## 🧭 Endpoints API principaux

| Contrôleur | Méthode | Endpoint | Description |
|-------------|----------|-----------|--------------|
| **AuthController** | POST | `/auth/login` | Authentification d’un utilisateur |
| **UserController** | GET | `/users` | Liste des utilisateurs |
| **QuizController** | GET | `/quiz` | Récupération des quiz disponibles |
| **QuestionController** | GET | `/questions/{id}` | Récupération des questions d’un quiz |
| **ScoreController** | GET | `/scores` | Récupération du classement global |

---

## 🔧 Dépannage

| Problème | Cause probable | Correctif                                           |
|-----------|----------------|-----------------------------------------------------|
| `Connection refused localhost:5432` | Conteneur DB arrêté | `docker ps` / `docker start api_database`           |
| Quiz ou scores vides | Scripts SQL non exécutés | Rejouer 1️⃣ 2️⃣ 3️⃣ via `docker exec ... psql -f`   |
| Port 8082 occupé | Process déjà actif | Changer `server.port` dans `application.properties` |
| `npm start` échoue | Conflit de dépendances | Supprimer `node_modules` puis `npm install`         |

---

## 📸 Captures d’écran (à ajouter)

Placez vos images dans `docs/img/` et référencez-les ainsi :

```markdown
![Accueil](docs/img/home.png)
![Sélection du quiz](docs/img/select_quiz.png)
![Question](docs/img/question.png)
![Classement](docs/img/ranking.png)
```

