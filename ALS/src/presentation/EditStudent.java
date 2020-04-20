package presentation;

import java.util.ArrayList;
import java.util.Arrays;

import entities.Student;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.DB_Controller;

public class EditStudent {
	private Stage primaryStage;
	private VBoxWithStyle vbox;
	private DB_Controller controller = new DB_Controller();
	private TextFieldWithStyle tfFirstName;
	private TextFieldWithStyle tfLastName;
	private TextAreaWithStyle textArea;
	private EventHandler<ActionEvent> studentUpdateEvent;
	private ComboBoxWithStyle slcSemesterCreate;
	private ComboBoxWithStyle slcSemesterDelete;
	private ComboBoxWithStyle slcStudent;
	private ArrayList<Integer> semesterNo = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));

	public EditStudent(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void editStudentUI() {
		VBox createStudentVBox = new VBox(createTitle(), firstName(), lastName(), selectSemesterNoCreate(),
				studentCreateButton());
		createStudentVBox.setAlignment(Pos.TOP_CENTER);
		createStudentVBox.setPadding(new Insets(10, 10, 10, 10));

		VBox deleteStudentVBox = new VBox(deleteTitle(), selectSemesterNoDelete(), selectStudent(),
				studentDeleteButton());
		deleteStudentVBox.setAlignment(Pos.TOP_CENTER);
		deleteStudentVBox.setPadding(new Insets(10, 10, 10, 10));

		VBox historyVBox = new VBox(textAreaTitle(), historyArea());
		historyVBox.setAlignment(Pos.TOP_CENTER);
		historyVBox.setPadding(new Insets(10, 10, 10, 10));

		HBox studentBoxesAndTextArea = new HBox(createStudentVBox, deleteStudentVBox, historyVBox);
		studentBoxesAndTextArea.setAlignment(Pos.CENTER);
		studentBoxesAndTextArea.setPadding(new Insets(10, 10, 10, 10));

		vbox = new VBoxWithStyle(title(), studentBoxesAndTextArea, backButton());
		vbox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vbox, 1800, 980);
		sceneSetup(scene);
	}

	//////////////////////////////
	// TextFields
	//////////////////////////////

	private GridPane firstName() {
		GridPaneCenter grid = new GridPaneCenter();
		grid.setPadding(new Insets(10, 10, 10, 10));

		tfFirstName = new TextFieldWithStyle("First Name", grid, 0, 0);

		return grid;
	}

	private GridPane lastName() {
		GridPaneCenter grid = new GridPaneCenter();
		grid.setPadding(new Insets(10, 10, 10, 10));

		tfLastName = new TextFieldWithStyle("Last Name", grid, 0, 0);

		return grid;
	}
	
	//////////////////////////////
	// TextAreas
	//////////////////////////////
	
	private GridPane historyArea() {
		GridPaneCenter grid = new GridPaneCenter();
		grid.setPadding(new Insets(10, 10, 10, 10));

		textArea = new TextAreaWithStyle(grid, 0, 0);

		return grid;
	}
	
	//////////////////////////////
	// ComboBoxes
	//////////////////////////////

	private GridPane selectSemesterNoCreate() {
		GridPaneCenter grid = new GridPaneCenter();
		grid.setPadding(new Insets(10, 10, 10, 10));

		slcSemesterCreate = new ComboBoxWithStyle(FXCollections.observableArrayList(semesterNo), grid, 0, 0);

		slcSemesterCreate.setPromptText("Select Semester No");

		slcSemesterCreate.setMinSize(150, 50);

		return grid;
	}

	private GridPane selectSemesterNoDelete() {
		GridPaneCenter grid = new GridPaneCenter();
		grid.setPadding(new Insets(10, 10, 10, 10));
		
		slcSemesterDelete = new ComboBoxWithStyle(FXCollections.observableArrayList(semesterNo), grid, 0, 0);

		slcSemesterDelete.setPromptText("Select Semester No");

		slcSemesterDelete.setOnAction(updateStudentsCombobox());

		slcSemesterDelete.setMinSize(150, 50);

		return grid;
	}

	private GridPane selectStudent() {
		GridPaneCenter grid = new GridPaneCenter();
		grid.setPadding(new Insets(10, 10, 10, 10));
		
		slcStudent = new ComboBoxWithStyle(FXCollections.observableArrayList(), grid, 0, 0);

		slcStudent.setPromptText("Select Student");

		slcStudent.setMinSize(150, 50);

		return grid;
	}

	//////////////////////////////
	// Buttons
	//////////////////////////////

	private GridPane studentCreateButton() {
		GridPaneCenter grid = new GridPaneCenter();
		grid.setPadding(new Insets(10, 10, 10, 10));

		ButtonWithStyle createStudentButton = new ButtonWithStyle("Create", grid, 0, 0);
		createStudentButton.setOnAction(e -> {
			Student newStudent = new Student(tfFirstName.getText(), tfLastName.getText(),
					(int) slcSemesterCreate.getValue());

			controller.createStudent(newStudent);
			textArea.setText(textArea.getText() + newStudent + " has been added.\n");

			if ((int) slcSemesterCreate.getValue() == (int) slcSemesterDelete.getValue()) {
				slcStudent.setItems(FXCollections
						.observableArrayList(controller.getStudentsBySemesterNo((int) slcSemesterCreate.getValue())));
			}
		});

		return grid;
	}

	private GridPane studentDeleteButton() {
		GridPaneCenter grid = new GridPaneCenter();
		grid.setPadding(new Insets(10, 10, 10, 10));

		ButtonWithStyle deleteStudentButton = new ButtonWithStyle("Delete", grid, 0, 0);
		deleteStudentButton.setOnAction(e -> {
			Student selectedStudent = (Student) slcStudent.getValue();

			controller.deleteStudent(selectedStudent);
			textArea.setText(textArea.getText() + selectedStudent + " has been deleted.\n");

			slcStudent.setItems(FXCollections
					.observableArrayList(controller.getStudentsBySemesterNo((int) slcSemesterDelete.getValue())));
		});

		return grid;
	}

	private GridPane backButton() {
		GridPaneCenter grid = new GridPaneCenter();
		grid.setPadding(new Insets(10, 10, 10, 10));

		ButtonWithStyle btnBack = new ButtonWithStyle("Back", grid, 1, 0);
		btnBack.setOnAction(e -> {
			new MainMenu(primaryStage).mainMenuUI();
		});

		return grid;
	}

	//////////////////////////////
	// Event Handler
	//////////////////////////////

	private EventHandler<ActionEvent> updateStudentsCombobox() {
		studentUpdateEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				ArrayList<Student> studentsList = controller.getStudentsBySemesterNo((int) slcSemesterDelete.getValue());
				slcStudent.setItems(FXCollections.observableArrayList(studentsList));
			}
		};
		return studentUpdateEvent;
	}

	//////////////////////////////
	// Label Titles
	//////////////////////////////

	private Label title() {
		Label label = new Label("Edit Students");
		label.setFont(Font.font("Calibri", FontWeight.BOLD, 60));
		label.setTextFill(Color.web("#F9F9F9"));
		return label;
	}

	private Label createTitle() {
		Label label = new Label("Create Students");
		label.setFont(Font.font("Calibri", FontWeight.BOLD, 50));
		label.setTextFill(Color.web("#F9F9F9"));
		return label;
	}

	private Label deleteTitle() {
		Label label = new Label("Delete Students");
		label.setFont(Font.font("Calibri", FontWeight.BOLD, 50));
		label.setTextFill(Color.web("#F9F9F9"));
		return label;
	}

	private Label textAreaTitle() {
		Label label = new Label("History of Actions");
		label.setFont(Font.font("Calibri", FontWeight.BOLD, 50));
		label.setTextFill(Color.web("#F9F9F9"));
		return label;
	}

	//////////////////////////////
	// Scene stuff
	//////////////////////////////

	private void sceneSetup(Scene scene) {
		primaryStage.setTitle("ALS");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
