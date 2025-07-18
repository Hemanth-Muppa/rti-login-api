# RTI Portal - Secure Login API

This project is a complete, production-ready, and secure login API built with Java and Spring Boot. It was developed to handle a specific set of business logic requirements, including JWT-based authentication, a sophisticated account lockout mechanism, and a full audit trail for login attempts.

This repository contains the full source code, configuration, and instructions needed to run and test the application.


## ✨ Features


-   **Secure JWT Authentication:** Implements stateless session management using JSON Web Tokens (JWT) for secure communication between the client and server.
-   **Account Lockout Protection:** Automatically blocks a user account for 30 minutes after 4 consecutive failed login attempts to protect against brute-force attacks.
-   **Complete Business Logic:** Handles all seven specified authentication scenarios, from successful logins for active users to handling inactive users, activation key validation, and blocked accounts.
-   **Audit Trail:** Every login attempt (successful or failed) is recorded in a `LoginHistory` table, including the user's IP address, browser, and OS for security monitoring.
-   **Layered Architecture:** Follows professional software design principles with a clear separation of concerns (Controller, Service, Repository, DTOs).
-   **Database Integration:** Uses Spring Data JPA to seamlessly connect to a MySQL database.

## 🛠️ Technology Stack

-   **Backend:** Java 11+
-   **Framework:** Spring Boot
    -   Spring Web (for creating REST APIs)
    -   Spring Data JPA (for database interaction)
    -   Spring Security (for authentication and endpoint protection)
-   **Database:** MySQL
-   **Build Tool:** Apache Maven
-   **JWT Library:** `jjwt`

## 🚀 Getting Started

Follow these instructions to get the project up and running on your local machine for development and testing.

### Prerequisites

Make sure you have the following software installed on your system:
-   Java Development Kit (JDK) - Version 11 or higher
-   Apache Maven
-   MySQL Server
-   Git
-   An API testing tool like [Postman](https://www.postman.com/downloads/)

### Installation & Setup

**1. Clone the repository**
Open your terminal and run the following command to clone the project to your local machine:
```
git clone https://github.com/YourUsername/rti-login-api.git
cd rti-login-api
```
*(Replace `YourUsername` with your actual GitHub username.)*

**2. Set Up the MySQL Database**
You need to create a database and a dedicated user for the application.
-   Open your MySQL client (like MySQL Workbench).
-   Run the following SQL commands. Remember to replace `'your_strong_password'` with a secure password of your choice.

```
-- 1. Create the database
CREATE DATABASE rti_portal;

-- 2. Create a dedicated user for the application
CREATE USER 'rti_user'@'localhost' IDENTIFIED BY 'your_strong_password';

-- 3. Give the new user full permissions on the new database
GRANT ALL PRIVILEGES ON rti_portal.* TO 'rti_user'@'localhost';

-- 4. Apply the changes
FLUSH PRIVILEGES;
```

**3. Configure the Application**
-   Navigate to `src/main/resources/` and open the `application.properties` file.
-   Update the database username and password to match the user you just created.
-   It's also highly recommended to change the `jwt.secret` to a long, random string.

```
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/rti_portal
spring.datasource.username=rti_user
spring.datasource.password=your_strong_password

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl

# JWT Secret Key
jwt.secret=aVeryLongAndRandomSecretKeyThatYouShouldChange
```

### Running the Application

Once the setup is complete, you can start the API server.
-   Open a terminal in the root directory of the project (`rti-login-api`).
-   Run the following Maven command:

```
mvn spring-boot:run
```
The server will start, and you will see log messages indicating that it is running on `port 8080`.

## 🧪 API Usage & Testing

The primary endpoint for this API is the login endpoint.

#### **Endpoint:** `POST /api/login`

### Test Cases

Here’s how you can test the different scenarios using Postman.

**Case 1: Successful Login**
This test verifies that a valid user can log in successfully.

1.  **Add a test user to the database:**
    ```
    INSERT INTO rti_portal.NetUser (userName, mobile, userType, activeIdle, activationKey, activationKeyConf, loginAttempts)
    VALUES ('testuser', '1234567890', 'Admin', 'Y', '12345', '12345', 0);
    ```
2.  **Send a POST request** in Postman with the following JSON body:
    ```
    {
        "username": "testuser",
        "password": "1234567890",
        "ipAddress": "127.0.0.1",
        "browser": "Postman",
        "system": "Desktop"
    }
    ```
3.  **Expected Response (`200 OK`):**
    ```
    {
        "result": "1",
        "resulttype": "Active",
        "sessionuser": 1,
        "sessiontype": "Admin",
        "jwtToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0dXNlciIsInVzZXJDb2RlIjox..."
    }
    ```

**Case 2: Invalid Password**
1.  **Send a POST request** with the correct username but the wrong password:
    ```
    {
        "username": "testuser",
        "password": "wrong-password",
        "ipAddress": "127.0.0.1",
        "browser": "Postman",
        "system": "Desktop"
    }
    ```
2.  **Expected Response (`200 OK`):**
    ```
    {
        "result": "5",
        "resulttype": "InvalidPass",
        "sessionuser": null,
        "sessiontype": null,
        "jwtToken": null
    }
    ```
    *Repeating this request 4 times will trigger the account lockout.*

**Case 3: User Not Found**
1.  **Send a POST request** with a username that does not exist in the database:
    ```
    {
        "username": "unknownuser",
        "password": "any-password",
        "ipAddress": "127.0.0.1",
        "browser": "Postman",
        "system": "Desktop"
    }
    ```
2.  **Expected Response (`200 OK`):**
    ```
    {
        "result": "7",
        "resulttype": "InActive",
        "sessionuser": null,
        "sessiontype": null,
        "jwtToken": null
    }
    ```

---
Happy Coding!
```
