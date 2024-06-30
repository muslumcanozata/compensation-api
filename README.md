# Compensation API

## Project Overview

Compensation API stores employees salary survey. You can get these datas by using Compensation API. This project is designed to store and monitor salary surveys, handle batch insert operations by using csv files. The project utilizes Docker for containerization and Gradle for build management.

## Installation and Setup

### Prerequisites

- Docker
- Docker Compose
- Java 17
- Gradle 8.5
- 
### Technologies Used

- Docker
- Docker Compose
- Java 17
- Gradle 8.5
- Spring Boot 3.3
- Spring Data JPA
- PostgreSQL 16
- Swagger 3
- JUnit 5
- Mockito 4.2
- RabbitMQ 3

### Steps to Set Up

1. **Clone the repository:**
   ```bash
   git clone git@github.com:muslumcanozata/compensation-api.git
   ```
2. **Navigate to the project directory:**
   ```bash
   cd compensation-api
   ```
3. **Build the project using Gradle:(not necessary)**
   ```bash
   ./gradlew build
   ```
4. **Run the Docker Compose setup:**
   ```bash
   docker-compose up -d
   ```

## Usage Instructions

To run the project locally:

1. Ensure that Docker and Docker Compose are installed and running on your system.
2. Use the Docker Compose file provided (`compose.yml`) to start the services:
   ```bash
   docker-compose up -d
   ```
3. Access the swagger ui page of this application through your local browser at `http://localhost:8080/api/v1/swagger-ui/index.html`.

## Directory Structure

```
Compensation-API/
│
├── Dockerfile            # Configuration for building the Docker image
├── README.md             # Additional help and guidelines
├── build.gradle          # Build configuration file for Gradle
├── settings.gradle       # Settings file for Gradle project
├── compose.yml           # Docker Compose file to set up multi-container environment
├── gradle/               # Gradle wrapper files
├── gradlew               # Unix executable for Gradle wrapper
├── gradlew.bat           # Windows batch file for Gradle wrapper
├── src/                  # Source code of the application
│   ├── main/             # Main application code
│   └── test/             # Test code
└── .gitignore            # Git ignore file
```

## Contribution Guidelines

If you wish to contribute to this project, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your changes and commit them with clear messages.
4. Push your changes to your forked repository.
5. Create a pull request with a detailed description of your changes.

