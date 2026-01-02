# 🌤️ Weather Microservices Platform

Plateforme de microservices pour la consultation météo avec gestion des villes et historique des requêtes.

---

## 🧩 Architecture du Projet

Le système est composé de **4 microservices** :

| Service             | Port | Rôle                              |
|---------------------|------|-----------------------------------|
| **Eureka Server**   | 8761 | Service de découverte             |
| **City Service**    | 8081 | Gestion des villes                |
| **Weather Service** | 8082 | Récupération météo                |
| **Alert Service**   | 8083 | Notification selon des conditions |
| **History Service** | 8084 | Historique des requêtes           |

## 🛠️ Technologies

- Java 17
- Spring Boot 3
- Spring Cloud Netflix Eureka
- Spring Data JPA
- H2 Database
- OpenFeign
- RestTemplate
- Resilience4j (Circuit Breaker)
- OpenWeather API
- Maven

## 🚀 Démarrage du projet

### 1️⃣ Lancer les services dans l’ordre
1. Eureka Server
2. City Service
3. History Service
4. Weather Service

### Accès aux services
| Service          | URL                                                                  |
| ---------------- | -------------------------------------------------------------------- |
| Eureka Dashboard | [http://localhost:8761](http://localhost:8761)                       |
| H2 City DB       | [http://localhost:8081/h2-console](http://localhost:8081/h2-console) |
| H2 History DB    | [http://localhost:8084/h2-console](http://localhost:8084/h2-console) |

### Endpoints Principaux
1. City Service
GET  /cities
GET  /cities/{name}
POST /cities

2. Weather Service
GET /weather/{city}

3. History Service
GET /history

4. Alert Service
GET /alerts/{city}

### 🧠 Fonctionnement du Weather Service
1. Vérifie si la ville existe via City Service
2. Appelle l’API OpenWeather
3. Enregistre la requête dans History Service
4. Retourne les données météo au client
5. En cas d’échec → Circuit Breaker / Fallback

### 🔐 Configuration importante
Dans application.properties du Weather Service :
weather.api.key=VOTRE_API_KEY_OPENWEATHER

### 🧯 Résilience
Le projet utilise Resilience4j :
Circuit Breaker
Fallback automatique
Protection contre pannes de services

### 👥 Auteurs
1. TAHIRI Sara : https://github.com/SaraTahiri || https://www.linkedin.com/in/tahiri-sara/
2. LAHMIDI Anas : https://github.com/anasthe03 || https://www.linkedin.com/in/lahmidianas/

### 📞 Contact
Pour toute question : tahirisara911@gmail.com || anaslahmidi03@gmail.com