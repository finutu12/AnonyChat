# AnonyChat

![Logo](Assets/Logo.png)

## Initial idea

Anonymous chat where people can enjoy eachother's company for a limited amount of time.
Basically a meet-up app. You get to find people and possible friends.
Though be careful!

**Disclaimer**

I am not held up for guilty if anyone sends personal data over the chat.\
``Never Do That``


AnonyChat is a chat application that allows users to connect and chat anonymously. It is built using C# for the frontend and Java (Spring Boot) for the backend.

## Table of Contents
- [Features](#features)
- [Frontend](#frontend)
  - [Classes](#classes)
- [Backend](#backend)
  - [Controllers](#controllers)
  - [Models](#models)
  - [Repositories](#repositories)
  - [Services](#services)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)

## Features
- User registration and login.
- Anonymous chat sessions.
- Message exchange in real-time.
- WebSocket support for real-time updates.

## Frontend
The frontend is built using WPF in C#.

### Classes

#### `Window1.xaml.cs`
Handles user registration.

- **Methods:**
  - `AddUser`: Registers a new user.
  - `SendPostRequestAsync`: Sends a POST request to register a new user.

#### `MainWindow.xaml.cs`
Handles the main chat functionalities.

- **Methods:**
  - `FindChatButton`: Starts a new chat session.
  - `LeaveChatButton`: Leaves the current chat session.
  - `addMessageToChat`: Adds a message to the chat window.
  - `getFreeChatSession`: Fetches a free chat session.
  - `UpdateChat`: Updates the chat with new messages.
  - `SendButton`: Sends a new message.
  - `SendMessage`: Sends a message to the backend.

#### Models

- **User**
  - Represents a user in the application.

- **Message**
  - Represents a message in a chat session.

- **ChatSession**
  - Represents a chat session.

## Backend
The backend is built using Spring Boot in Java.

### Controllers

#### `ChatController`
Handles chat session-related operations.

- **Endpoints:**
  - `POST /chat/freeSession`: Fetches or creates a free chat session.
  - `POST /chat/greet`: Returns a greeting message.
  - `GET /chat/getGreet`: Returns a greeting message using a GET request.
  - `GET /chat/less2`: Fetches users with ID less than a given number.
  - `GET /chat/findByID`: Fetches users by chat session ID.

#### `MessageController`
Handles message-related operations.

- **Endpoints:**
  - `POST /message/sendMessage`: Sends a message.
  - `POST /message/getMessages`: Fetches messages for a chat session.

#### `UserController`
Handles user-related operations.

- **Endpoints:**
  - `POST /user/getUser`: Registers a new user.

### Models

- **User**
  - Represents a user in the system.

- **Message**
  - Represents a message in the system.

- **ChatSession**
  - Represents a chat session in the system.

- **MessageDTO**
  - Data Transfer Object for messages.

### Repositories

- **ChatSessionRepository**
  - Custom queries for chat sessions.

- **MessageRepository**
  - Custom queries for messages.

- **UserRepository**
  - Custom queries for users.

### Services

- **ChatSessionService**
  - Business logic for chat sessions.

- **MessageService**
  - Business logic for messages.

- **StompMessageService**
  - Service for sending messages using STOMP protocol.

- **UserService**
  - Business logic for users.

## Getting Started

### Prerequisites
- [.NET](https://dotnet.microsoft.com/download)
- [Java 11+](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven](https://maven.apache.org/install.html)

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/your-repo/anonychat.git
    ```

2. Navigate to the project directory:
    ```sh
    cd anonychat
    ```

### Running the Application

#### Backend

1. Navigate to the backend directory:
    ```sh
    cd backend
    ```

2. Build and run the backend application:
    ```sh
    mvn spring-boot:run
    ```

#### Frontend

1. Open the frontend solution in Visual Studio.
2. Build and run the frontend application.

## API Endpoints

### ChatController

- `POST /chat/freeSession`
  - **Description:** Fetches or creates a free chat session.
  - **Request Body:** `Integer id`
  - **Response:** `ChatSession`

- `POST /chat/greet`
  - **Description:** Returns a greeting message.
  - **Request Body:** `Integer id`
  - **Response:** `String`

- `GET /chat/getGreet`
  - **Description:** Returns a greeting message using a GET request.
  - **Request Params:** `String name`
  - **Response:** `String`

- `GET /chat/less2`
  - **Description:** Fetches users with ID less than a given number.
  - **Request Params:** `Integer nr`
  - **Response:** `List<User>`

- `GET /chat/findByID`
  - **Description:** Fetches users by chat session ID.
  - **Request Params:** `Integer id`
  - **Response:** `List<User>`

### MessageController

- `POST /message/sendMessage`
  - **Description:** Sends a message.
  - **Request Body:** `Message`
  - **Response:** `Message`

- `POST /message/getMessages`
  - **Description:** Fetches messages for a chat session.
  - **Request Body:** `Integer chatSessionId`
  - **Response:** `List<MessageDTO>`

### UserController

- `POST /user/getUser`
  - **Description:** Registers a new user.
  - **Request Body:** `User`
  - **Response:** `User`

