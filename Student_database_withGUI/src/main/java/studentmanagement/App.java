package studentmanagement;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {

    private TextField idField;
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField emailField;
    private TextField courseField;
    private TextField gradeField;
    private TextField searchField;

    private TableView<Student> studentTable;
    private StudentDAO studentDAO;

    @Override
    public void start(Stage stage) {
        Database.createTable();
        studentDAO = new StudentDAO();

        Label titleLabel = new Label("Student Management System");
        titleLabel.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        GridPane formGrid = createForm();
        HBox buttonBox = createButtons();
        studentTable = createTable();

        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.getChildren().addAll(titleLabel, formGrid, buttonBox, studentTable);

        Scene scene = new Scene(mainLayout, 900, 650);

        stage.setTitle("Student Management System");
        stage.setScene(scene);
        stage.show();

        loadStudents();
    }

    private GridPane createForm() {
        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(12);
        grid.setPadding(new Insets(15));
        grid.setAlignment(Pos.CENTER);

        idField = new TextField();
        idField.setPromptText("Auto-filled when selected");
        idField.setEditable(false);

        firstNameField = new TextField();
        firstNameField.setPromptText("First Name");

        lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");

        emailField = new TextField();
        emailField.setPromptText("Email");

        courseField = new TextField();
        courseField.setPromptText("Course");

        gradeField = new TextField();
        gradeField.setPromptText("Grade");

        searchField = new TextField();
        searchField.setPromptText("Search by name, email, course, or grade");

        grid.add(new Label("Student ID:"), 0, 0);
        grid.add(idField, 1, 0);

        grid.add(new Label("First Name:"), 0, 1);
        grid.add(firstNameField, 1, 1);

        grid.add(new Label("Last Name:"), 0, 2);
        grid.add(lastNameField, 1, 2);

        grid.add(new Label("Email:"), 2, 0);
        grid.add(emailField, 3, 0);

        grid.add(new Label("Course:"), 2, 1);
        grid.add(courseField, 3, 1);

        grid.add(new Label("Grade:"), 2, 2);
        grid.add(gradeField, 3, 2);

        grid.add(new Label("Search:"), 0, 3);
        grid.add(searchField, 1, 3, 3, 1);

        return grid;
    }

    private HBox createButtons() {
        Button addButton = new Button("Add Student");
        Button updateButton = new Button("Update Student");
        Button deleteButton = new Button("Delete Student");
        Button searchButton = new Button("Search");
        Button viewAllButton = new Button("View All");
        Button clearButton = new Button("Clear");

        addButton.setOnAction(e -> addStudent());
        updateButton.setOnAction(e -> updateStudent());
        deleteButton.setOnAction(e -> deleteStudent());
        searchButton.setOnAction(e -> searchStudents());
        viewAllButton.setOnAction(e -> loadStudents());
        clearButton.setOnAction(e -> clearFields());

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(
                addButton,
                updateButton,
                deleteButton,
                searchButton,
                viewAllButton,
                clearButton
        );

        return buttonBox;
    }

    private TableView<Student> createTable() {
        TableView<Student> table = new TableView<>();

        TableColumn<Student, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setPrefWidth(70);

        TableColumn<Student, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameColumn.setPrefWidth(130);

        TableColumn<Student, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameColumn.setPrefWidth(130);

        TableColumn<Student, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setPrefWidth(220);

        TableColumn<Student, String> courseColumn = new TableColumn<>("Course");
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
        courseColumn.setPrefWidth(150);

        TableColumn<Student, String> gradeColumn = new TableColumn<>("Grade");
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
        gradeColumn.setPrefWidth(100);

        table.getColumns().addAll(
                idColumn,
                firstNameColumn,
                lastNameColumn,
                emailColumn,
                courseColumn,
                gradeColumn
        );

        table.setPrefHeight(300);

        table.setOnMouseClicked(e -> {
            Student selectedStudent = table.getSelectionModel().getSelectedItem();

            if (selectedStudent != null) {
                idField.setText(String.valueOf(selectedStudent.getId()));
                firstNameField.setText(selectedStudent.getFirstName());
                lastNameField.setText(selectedStudent.getLastName());
                emailField.setText(selectedStudent.getEmail());
                courseField.setText(selectedStudent.getCourse());
                gradeField.setText(selectedStudent.getGrade());
            }
        });

        return table;
    }

    private void addStudent() {
        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty()) {
            showAlert("Missing Information", "First name and last name are required.");
            return;
        }

        Student student = new Student(
                firstNameField.getText(),
                lastNameField.getText(),
                emailField.getText(),
                courseField.getText(),
                gradeField.getText()
        );

        studentDAO.addStudent(student);

        loadStudents();
        clearFields();

        showAlert("Success", "Student added successfully.");
    }

    private void updateStudent() {
        if (idField.getText().isEmpty()) {
            showAlert("No Student Selected", "Please select a student from the table first.");
            return;
        }

        int id = Integer.parseInt(idField.getText());

        Student student = new Student(
                id,
                firstNameField.getText(),
                lastNameField.getText(),
                emailField.getText(),
                courseField.getText(),
                gradeField.getText()
        );

        studentDAO.updateStudent(student);

        loadStudents();
        clearFields();

        showAlert("Success", "Student updated successfully.");
    }

    private void deleteStudent() {
        if (idField.getText().isEmpty()) {
            showAlert("No Student Selected", "Please select a student from the table first.");
            return;
        }

        int id = Integer.parseInt(idField.getText());

        studentDAO.deleteStudent(id);

        loadStudents();
        clearFields();

        showAlert("Success", "Student deleted successfully.");
    }

    private void searchStudents() {
        String keyword = searchField.getText();

        if (keyword.isEmpty()) {
            showAlert("Search Empty", "Please type something to search.");
            return;
        }

        ObservableList<Student> students = FXCollections.observableArrayList(
                studentDAO.searchStudents(keyword)
        );

        studentTable.setItems(students);
    }

    private void loadStudents() {
        ObservableList<Student> students = FXCollections.observableArrayList(
                studentDAO.getAllStudents()
        );

        studentTable.setItems(students);
    }

    private void clearFields() {
        idField.clear();
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        courseField.clear();
        gradeField.clear();
        searchField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}