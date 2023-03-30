# Java Swing Quiz Application
This Java Swing Quiz Application is designed to provide an interactive quiz-taking experience for users. The application fetches data from a MongoDB database and allows users to take quizzes on various topics.

## Features
 - User authentication system to ensure secure access to the application
 - User interface for taking quizzes with multiple choice questions
 - Admin interface for managing users and questions
 - CRUD functionality for adding, modifying, and deleting users and questions
## Installation
 - Clone the repository to your local machine
 - Ensure you have the latest version of Java installed
 - Make your own database
 - Set up the MongoDB database and collections
 - Update the MongoDB connection information in the DatabaseManager class
 - I built it in intelliJ, feel free to use whatever you'd like
## Usage

### Login Window
![image](https://user-images.githubusercontent.com/63313585/227182020-ee15792d-fa77-466d-80ba-583f8eb17704.png)
The user is asked to enter their username and password, after which they are authenticated and logged in.

### User Interface
![image](https://user-images.githubusercontent.com/63313585/227182167-a68b621c-9433-443c-8d17-8cc6388cb44b.png)
![image](https://user-images.githubusercontent.com/63313585/227182236-1c646dbf-1ead-4a2c-b487-121481ecf988.png)
![image](https://user-images.githubusercontent.com/63313585/227182325-a08e0d61-1ee4-4577-9ef5-f80d9a8758ba.png)
When the user logs into the application, they will be presented with the quiz selection screen. The user can customize the quiz before they start taking the quiz. The quiz consists of multiple-choice questions and the user can select their answer by clicking on the corresponding button. After the user has completed the quiz, their score will be displayed.

### Admin Interface
![image](https://user-images.githubusercontent.com/63313585/227182484-e5a946c4-b510-4e3d-ba5c-66cfa123787c.png)
The admin interface allows for the management of users and questions. To access the admin interface, log in as an admin user. The admin interface consists of two tabs: Users and Questions.

### Users Tab
The Users tab displays a list of all registered users. Admin users can add new users, modify existing users, or delete users and check their quiz reports.

### Questions Tab
The Questions tab displays a list of all available questions. Admin users can add new questions, modify existing questions, or delete questions.

## Contributions
If you find a bug or have a feature request, please open an issue in the repository. Pull requests are also welcome.

## License
This application is licensed under the MIT License.
