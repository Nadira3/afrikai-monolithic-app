# AfrikAI Monolithic Application - Initial Attempt

## Overview

This is the initial version of a monolithic application that I developed as part of my final project. The application aimed to provide a fully functional system to manage various user-related tasks, including user authentication, task management, and service integrations. It was designed as a single monolithic codebase before transitioning to a more modular microservices-based architecture.

## Features

The application includes the following core features:

- **User Authentication:** Provides secure user authentication using JWT tokens.
- **Task Management:** Allows users to create, assign, and track tasks. Users can also label tasks and update their status.
- **Basic Service Integration:** The system was designed to communicate with other services like a user service for fetching user details.
- **Task Labeling:** Users can label tasks as part of their involvement in task management.

## Technologies Used

- **Spring Boot:** A Java framework for building the backend of the application.
- **JPA / Hibernate:** Used for managing the database and persistence layer.
- **Spring Security:** For authentication and authorization.
- **JWT:** For securing user login and maintaining session integrity.
- **Thymeleaf:** A templating engine for rendering HTML in the front-end (if applicable).
- **H2 Database:** Used as an in-memory database for testing and development purposes.

## Project Structure

The monolithic application is structured as follows:

- **/src/main/java:** Contains all Java code for the application.
- **/src/main/resources:** Contains application properties, static assets, and templates.
- **/src/main/resources/application.properties:** Contains application-level configurations, including database connections, server settings, and security configurations.

## Running the Application

To run the application locally:

1. Clone this repository:
    ```bash
    git clone https://github.com/your-username/monolithic-application.git
    ```
2. Navigate to the project directory:
    ```bash
    cd monolithic-application
    ```
3. Build the application using Maven:
    ```bash
    mvn clean install
    ```
4. Run the application:
    ```bash
    mvn spring-boot:run
    ```
5. Access the application via [http://localhost:8080](http://localhost:8080).

## Key Components

### 1. User Authentication

The user authentication system in this monolithic application is built using Spring Security and JWT tokens. It handles user login, token generation, and token validation. The system ensures that only authenticated users can access restricted endpoints.

### 2. Task Management

This feature enables users to create tasks, assign them, and monitor progress. It includes endpoints for CRUD operations on tasks, with simple status tracking and task assignments.

### 3. Task Labeling

A key feature of the system, users can label tasks, which helps in categorizing them based on different criteria. This feature is integrated within the task management module and provides an easy interface to add labels.

### 4. Service Integration (User Details)

Although this was initially designed as a monolithic application, the user details integration provided insight into how the system would scale in a microservice architecture. The user service fetches additional information about users to enhance task assignments and labeling.

## Future Enhancements

As the application transitioned to a microservices-based architecture, many of the components from this monolithic system were refactored into separate services:

- **API Gateway**
- **Service Registry (Eureka)**
- **Individual Microservices for Task Management and User Management**

The system has been modularized for better scalability, maintainability, and easier deployment.

## Conclusion

This monolithic application was the initial step in a larger architecture overhaul aimed at improving the scalability and maintainability of the system. The transition to microservices has allowed for better separation of concerns, fault tolerance, and independent scalability of the individual components. Despite challenges, this was an important learning experience in software design, and it laid the groundwork for the current microservices-based architecture.

---

Feel free to contribute, report issues, or fork this repository to continue building on this project.
