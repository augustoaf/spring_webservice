# Greeting Microservice

A simple Java microservice built with Spring Boot that provides a greeting endpoint.

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Building the Application

To build the application, run:

```bash
mvn clean compile
```

## Running the Application

To run the application, use one of the following commands:

### Using Maven
```bash
mvn spring-boot:run
```

### Using JAR file
First build the JAR:
```bash
mvn clean package
```

Then run the JAR:
```bash
java -jar target/greeting-service-1.0.0.jar
```

## API Endpoints

### GET /greeting

Returns a greeting message with the provided name.

**Parameters:**
- `name` (optional): The name to greet. Defaults to "World" if not provided.

**Examples:**

1. With a name parameter:
   ```
   GET http://localhost:8080/greeting?name=John
   ```
   Response: `Hello, John! Welcome to our microservice!`

2. Without a name parameter:
   ```
   GET http://localhost:8080/greeting
   ```
   Response: `Hello, World! Welcome to our microservice!`

### POST /greetingsFullName

Returns greetings in both English and French with the provided first and last name.

**Request Body (JSON):**
```json
{
  "firstName": "John",
  "lastName": "Doe"
}
```

**Response (JSON):**
```json
{
  "englishGreeting": "Hello, John Doe! Welcome to our microservice!",
  "frenchGreeting": "Bonjour, John Doe! Bienvenue dans notre microservice!"
}
```

## Testing

You can test the endpoints using:

- **cURL:**
  ```bash
  # Test the simple greeting endpoint
  curl "http://localhost:8080/greeting?name=Alice"
  
  # Test the full name greeting endpoint
  curl -X POST "http://localhost:8080/greetingsFullName" \
       -H "Content-Type: application/json" \
       -d '{"firstName": "John", "lastName": "Doe"}'
  ```

- **Browser:** Navigate to `http://localhost:8080/greeting?name=Alice`

- **Postman:** 
  - Send a GET request to `http://localhost:8080/greeting?name=Alice`
  - Send a POST request to `http://localhost:8080/greetingsFullName` with JSON body

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/greetingservice/
│   │       ├── GreetingServiceApplication.java
│   │       ├── controller/
│   │       │   └── GreetingController.java
│   │       └── model/
│   │           ├── Person.java
│   │           └── GreetingResponse.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── com/example/greetingservice/
            ├── GreetingServiceApplicationTests.java
            └── controller/
                └── GreetingControllerTest.java
```

## Configuration

The application runs on port 8080 by default. You can change this by modifying the `server.port` property in `src/main/resources/application.properties`. 