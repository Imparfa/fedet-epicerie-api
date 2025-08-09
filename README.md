# FEDET Épicerie Solidaire – API

## 📌 Présentation / Overview

**FR**  
Cette API backend fait partie du projet **FEDET Épicerie Solidaire**, développé pour la **Fédération des Étudiants
Toulonnais (FEDET)**.  
Elle gère la logique métier, les données et la sécurité d’une application mobile multi-plateformes permettant aux
étudiants de s’inscrire, générer un QR Code personnel et participer à des distributions alimentaires.  
L’architecture est **hexagonale**, développée en **Java 11** avec **Spring Boot 2.7.17**, et déployée sur un serveur *
*VPS Linux** via **Docker**.

**EN**  
This backend API is part of the **FEDET Épicerie Solidaire** project, developed for the **Fédération des Étudiants
Toulonnais (FEDET)**.  
It handles business logic, data management, and security for a cross-platform mobile app that allows students to
register, generate a personal QR code, and participate in food distributions.  
Built with a **hexagonal architecture** in **Java 11** with **Spring Boot 2.7.17**, and deployed on a **Linux VPS** via
**Docker**.

---

## 🛠️ Stack Technique / Tech Stack

- **Java 11**
- **Spring Boot 2.7.17**
- **Maven**
- **PostgreSQL**
- **Docker / Docker Compose**
- **Vault (HashiCorp)** pour la gestion sécurisée des secrets
- **JWT Authentication**
- **Swagger / OpenAPI** pour la documentation API

---

## 🏗️ Architecture Hexagonale / Hexagonal Architecture

```
[ Controllers (REST) ]
        |
[ Application / Use Cases ]
        |
[ Domain (Entities, Ports) ]
        |
[ Infrastructure (Adapters: JPA, Vault, etc.) ]
```

Cette structure sépare les couches métier, cas d’utilisation et implémentations techniques, facilitant la maintenance,
les tests et l’évolution.

---

## 🚀 Installation & Lancement / Installation & Run

### **Prérequis / Requirements**

- Java 11
- Maven
- Docker & Docker Compose
- Accès réseau à PostgreSQL ou utilisation de la stack Docker fournie

### **Lancement avec Docker Compose / Run with Docker Compose**

```bash
# Cloner le dépôt / Clone the repository
git clone https://github.com/Imparfa/fedet-epicerie-api.git
cd fedet-epicerie-api

# Construire le jar / Build the jar
mvn clean install

# Démarrer avec Docker Compose / Start with Docker Compose
docker-compose up -d --build
```

📄 L’API sera disponible sur :

- **Swagger UI
  ** : [src/main/resources/api-spec/fedetEpicerieSolidaire.yaml](src/main/resources/api-spec/fedetEpicerieSolidaire.yaml)

---

## ⚙️ Variables d’Environnement / Environment Variables

| Variable                 | Description FR                          | Description EN                        |
|--------------------------|-----------------------------------------|---------------------------------------|
| `SPRING_PROFILES_ACTIVE` | Profil Spring actif (ex: `dev`, `prod`) | Active Spring profile (`dev`, `prod`) |
| `DB_URL`                 | URL JDBC de la base PostgreSQL          | PostgreSQL JDBC URL                   |
| `DB_USER`                | Nom d’utilisateur BDD                   | Database username                     |
| `DB_PASSWORD`            | Mot de passe BDD                        | Database password                     |
| `JWT_SECRET`             | Clé secrète JWT                         | JWT secret key                        |
| `VAULT_ADDR`             | URL du serveur Vault                    | Vault server URL                      |
| `VAULT_TOKEN`            | Token d’authentification Vault          | Vault authentication token            |

ℹ️ Les valeurs par défaut sont déjà définies dans `src/main/resources/application.yml` pour le développement.

---

## 📚 Utilisation / Usage

### Exemple d’Endpoints (Swagger)

- **POST** `/auth/register` → Créer un compte étudiant
- **POST** `/auth/login` → Connexion utilisateur
- **GET** `/student/profile` → Récupérer le profil étudiant
- **POST** `/collect/scan` → Scanner un QR code
- **POST** `/collect/validate` → Valider un passage
- **GET** `/management/stats` → Statistiques d’utilisation

Documentation complète via **Swagger UI**.

---

## 🧪 Tests

```bash
# Lancer les tests unitaires
mvn test
```

- **JUnit 5**
- **Mockito**
- **Testcontainers** pour PostgreSQL en test

---

## 📦 Build

```bash
mvn clean install
```

Génère le fichier `.jar` dans `target/` prêt à être packagé avec Docker.

---

## 🔗 Projet Associé / Related Project

- **Application mobile Ionic Angular** : [fedet-epicerie-app](https://github.com/Imparfa/fedet-epicerie-app)

---

## 🤝 Contribution / Contributing

1. Forkez le projet
2. Créez une branche (`feature/ma-fonctionnalite`)
3. Committez vos modifications
4. Poussez la branche
5. Ouvrez une Pull Request

---

## 📄 Licence

© 2025 FEDET – Tous droits réservés / All rights reserved
