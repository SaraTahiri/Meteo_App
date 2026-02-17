# 🌤️ Weather Microservices Platform

## 📌 Project Overview

A distributed microservices-based platform for weather consultation, featuring city management, weather data retrieval, weather-based alerts, and request history tracking. The application demonstrates a scalable microservices architecture with service discovery, fault tolerance, and resilient inter-service communication.

## 🧩 Architecture

The system is composed of **4 independent microservices**:

| Service | Port | Description |
|---------|------|-------------|
| **Eureka Server** | 8761 | Service discovery and registry |
| **City Service** | 8081 | City management (CRUD operations) |
| **Weather Service** | 8082 | Weather data retrieval and orchestration |
| **Alert Service** | 8083 | Weather-based alerts and notifications |
| **History Service** | 8084 | Weather request history persistence |

### System Workflow

```
Client → Weather Service → City Service (verify city)
              ↓
         OpenWeather API
              ↓
         History Service (store request)
              ↓
         Alert Service (check conditions)
              ↓
         Response to Client
```

## 🛠️ Technologies Used

### Core Technologies
- **Java 17**
- **Spring Boot 3**
- **Spring Cloud Netflix Eureka**
- **Spring Data JPA**
- **H2 In-Memory Database**
- **Maven**

### Communication & Integration
- **OpenFeign** (Declarative REST client)
- **RestTemplate** (HTTP communication)
- **OpenWeather API** (External weather data provider)

### Resilience & Fault Tolerance
- **Resilience4j** (Circuit Breaker pattern)
- Automatic fallback handling
- Service failure isolation

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven
- Internet connection (for OpenWeather API)
- OpenWeather API key ([Get one here](https://openweathermap.org/api))

### 1️⃣ Start Services in Order

**Important**: Services must be started in the following sequence to ensure proper registration and communication:

1. **Eureka Server** (Service Registry)
2. **City Service** (Reference Data)
3. **History Service** (Data Persistence)
4. **Weather Service** (Business Orchestrator)
5. **Alert Service** (Notification System)

### 2️⃣ Configuration

Update `application.properties` in **Weather Service**:

```properties
weather.api.key=YOUR_OPENWEATHER_API_KEY
```

To obtain an API key:
1. Visit [OpenWeatherMap](https://openweathermap.org/api)
2. Create a free account
3. Generate an API key from your dashboard

### 3️⃣ Running the Services

For each service, navigate to its directory and run:

```bash
mvn clean install
mvn spring-boot:run
```

## 🔗 Service Access

| Service | URL | Credentials |
|---------|-----|-------------|
| **Eureka Dashboard** | http://localhost:8761 | - |
| **City H2 Console** | http://localhost:8081/h2-console | JDBC URL: `jdbc:h2:mem:citydb` |
| **History H2 Console** | http://localhost:8084/h2-console | JDBC URL: `jdbc:h2:mem:historydb` |

## 📡 API Endpoints

### 1️⃣ City Service (Port 8081)

**Get All Cities**
```http
GET /cities
```

**Get City by Name**
```http
GET /cities/{name}
```

**Add New City**
```http
POST /cities
Content-Type: application/json

{
  "name": "Paris",
  "country": "FR"
}
```

### 2️⃣ Weather Service (Port 8082)

**Get Weather for City**
```http
GET /weather/{city}
```

Response example:
```json
{
  "city": "Paris",
  "temperature": 15.5,
  "humidity": 65,
  "description": "Partly cloudy",
  "timestamp": "2024-01-15T10:30:00"
}
```

### 3️⃣ History Service (Port 8084)

**Get Request History**
```http
GET /history
```

### 4️⃣ Alert Service (Port 8083)

**Get Weather Alerts for City**
```http
GET /alerts/{city}
```

## 🧠 Weather Service Workflow

The Weather Service acts as the main orchestrator:

1. **City Verification**: Checks if the requested city exists via City Service
2. **Data Retrieval**: Calls the OpenWeather API to fetch weather data
3. **History Logging**: Stores the request in History Service
4. **Alert Check**: Triggers Alert Service to check notification conditions
5. **Response**: Returns processed weather data to the client
6. **Fault Handling**: In case of failure → Circuit Breaker activates / Fallback response

## 🧯 Resilience & Fault Tolerance

The platform implements **Resilience4j** patterns for system stability:

### Circuit Breaker
- Monitors service health and prevents cascading failures
- Opens circuit when failure threshold is reached
- Automatically attempts recovery after cooldown period

### Fallback Mechanism
- Provides cached or default responses when services are unavailable
- Ensures graceful degradation of functionality
- Maintains user experience during partial outages

### Fault Isolation
- Service failures are contained and don't affect other microservices
- Independent failure domains for each service

## 📂 Project Structure

```
weather-microservices-platform/
│
├── eureka-server/          # Service discovery
├── city-service/           # City management
├── weather-service/        # Weather orchestrator
├── alert-service/          # Alert notifications
└── history-service/        # Request history
```

Each microservice follows the standard Spring Boot structure:
```
service-name/
├── src/main/java
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   └── config/
└── src/main/resources
    └── application.properties
```

## 📊 Project Status

✅ All microservices implemented and functional  
✅ Service discovery with Eureka  
✅ Circuit breaker and fault tolerance  
✅ External API integration  
⏳ Frontend dashboard (planned)  
⏳ Database migration to PostgreSQL (planned)

## 🛡️ Future Enhancements

- API Gateway implementation (Spring Cloud Gateway)
- Authentication and authorization (OAuth2/JWT)
- Persistent database migration (PostgreSQL/MySQL)
- Monitoring and logging (Prometheus, Grafana, ELK Stack)
- Docker containerization and Kubernetes orchestration
- Frontend dashboard with real-time weather updates
- Notification system (Email/SMS alerts)
- Unit and integration testing
- API documentation with Swagger/OpenAPI

## 👥 Authors

**Sara Tahiri**  
🔗 GitHub: [github.com/SaraTahiri](https://github.com/SaraTahiri)  
💼 LinkedIn: [linkedin.com/in/tahiri-sara](https://www.linkedin.com/in/tahiri-sara/)

**Anas Lahmidi**  
🔗 GitHub: [github.com/AnasLahmidi](https://github.com/AnasLahmidi)  
💼 LinkedIn: [linkedin.com/in/anas-lahmidi](https://www.linkedin.com/in/anas-lahmidi/)

## 📞 Contact

For questions, suggestions, or collaboration opportunities:

📧 tahirisara911@gmail.com  
📧 anaslahmidi03@gmail.com

## 📄 License

This project is developed for educational purposes.
