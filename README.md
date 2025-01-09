# PollMaster

PollMaster is a simple application that allows users to create and participate in polls. This README provides an overview of the project, how to set it up, and how to contribute.

## Features

- User authentication and authorization
- Create new polls with multiple options
- Vote on polls
- Admin panel to view poll results

## Technologies Used

- Java
- Spring Boot
- Thymeleaf
- Hibernate (JPA)
- MySQL
- Bootstrap
- Font Awesome

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- MySQL

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/VotingApp.git
    ```
2. Navigate to the project directory:
    ```sh
    cd VotingApp
    ```

### Configure Database

1. Create a MySQL database named votingapp.

2. Update the database configuration in application.properties:
   ```sh
    spring.datasource.url=jdbc:mysql://localhost:3306/pollingdb
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
    ```

### Running the application

    mvn clean install
    mvn spring-boot:run

### Initial Setup

1. When running the application for the first time, create a user.
2. Modify the role of that user to 'admin' to access the admin API.

## Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository.
2. Create a new branch:
    ```sh
    git checkout -b feature-branch
    ```
3. Make your changes and commit them:
    ```sh
    git commit -m "Description of your changes"
    ```
4. Push to the branch:
    ```sh
    git push origin feature-branch
    ```
5. Create a pull request.

## Acknowledgements
- Spring Boot
- Thymeleaf
- Bootstrap
- Font Awesome

## Contact

- For any questions or feedback, please contact us at aarondsouza04020@gmail.com.

## Images
![pollspage](https://github.com/user-attachments/assets/75b4f366-a151-4dc0-bb9c-bf3257efc2dd)

![votepage](https://github.com/user-attachments/assets/e491838e-1d6d-4d0b-b8f9-8d4abc870b4b)

![resultspage](https://github.com/user-attachments/assets/3551ff6e-7de1-423d-a841-14acca4b8f59)



