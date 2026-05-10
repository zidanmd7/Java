# Student Management System with Database

A simple JavaFX application for managing student records. This project allows users to add, update, delete, search, and view student information using a graphical user interface. Student data is stored in a SQLite database, so the records stay saved even after the program closes.


## Features
- Add new student records
- View all students in a table
- Search for students by name, email, course, or grade
- Update existing student information
- Delete student records
- Store data using SQLite
- Simple JavaFX graphical interface

## Technologies Used
- Java
- JavaFX
- SQLite
- JDBC
- Maven

## Project Structure
```
Student access database/
├── pom.xml
└── src/
    └── main/
        └── java/
            └── studentmanagement/
                ├── App.java
                ├── Database.java
                ├── Student.java
                └── StudentDAO.java
```

## Requirements
Before running the project, make sure you have:
- Java installed
- Maven installed
- IntelliJ IDEA or another Java IDE

You can check if Java is installed by running:
```bash
java -version
```

You can check if Maven is installed by running:
```bash
mvn -version
```

## How to Run the Project
Open a terminal and go to the main project folder where the `pom.xml` file is located.

Example:

```bash
cd ~/Desktop/"Student access database"
```

Then run:

```bash
mvn clean javafx:run
```

The JavaFX window should open after Maven finishes building the project.
## How to Use the Application

1. Open the application.
2. Enter the student's first name, last name, email, course, and grade.
3. Click **Add Student** to save the record.
4. Select a student from the table if you want to update or delete them.
5. Edit the fields and click **Update Student** to save changes.
6. Click **Delete Student** to remove a selected student.
7. Use the search box to search by name, email, course, or grade.
8. Click **View All** to reload all student records.
9. Click **Clear** to empty the input fields.

## Database Information
The program uses SQLite for storing student records.
When the program runs, it creates a database file called:

```
students.db
```

The database table is created automatically if it does not already exist.

The table stores:
- Student ID
- First name
- Last name
- Email
- Course
- Grade

## Purpose of the Project
This project was created to practice GUI development and database operations in Java. It shows basic CRUD operations, JavaFX interface design, JDBC database connection, and SQLite database storage.

CRUD stands for:
- Create
- Read
- Update
- Delete

## Notes
Make sure you run Maven from the main project folder, not from inside the `src` folder.

Correct location:

```
Student access database/
```

Wrong location:

```
Student access database/src/main/java/
```

