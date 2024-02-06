# 🌃 City Management Application 💻

This application provides endpoints for managing cities and user authentication.

## Prerequisites 📝 

- JDK 17
- Gradle
- PostgeSQL
- Docker
- Docker Compose

## ⛏Running the Application

### ✘ Step 1: Clone the Repository

#### git clone https://github.com/dmytrovb02/country-and-city
#### cd country-and-city

### ✘ Step 2: Build the Application

./gradlew build

### ✘ Step 3: Run Docker Compose

docker-compose up --build

This command will start the application along with the required dependencies (e.g., database).

### ✘ Step 4: Access the Application

Once the application is running, you can access the endpoints using the following base URL:

#### http://localhost:8081/swagger-ui/index.html

## 🌟Endpoints

### City Management

#### Get all cities

GET /api/cities

Get a list of all available cities.

#### Get all unique city names

GET /api/cities/name

Get a list of all available unique city names.

#### Update a city by ID

PUT /api/cities/{id}

Update a city name and logo by ID. Requires EDITOR role.

#### Search cities

GET /api/cities/search

Search cities by name and name of country.

### 🎯Authentication

#### User Registration

POST /api/auth/register

Register a new user.

#### User Authentication

POST /api/auth/login

Authenticate a user.
