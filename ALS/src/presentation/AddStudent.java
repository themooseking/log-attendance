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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.DB_Controller;

public class AddStudent {
	private Stage primaryStage;
	private VBoxWithStyle vbox;
	private DB_Controller controller = new DB_Controller();
	private TextFieldWithStyle tfFirstName;
	private TextFieldWithStyle tfLastName;
	private TextArea textArea;
	private EventHandler<ActionEvent> semesterSelectEvent;
	private ComboBox<Integer> slcSemesterCreate;
	private ComboBox<Integer> slcSemesterDelete;
	private ComboBox<Student> slcStudent;
	private ArrayList<Integer> semesterNo = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));

	public AddStudent(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void editStudentUI() {
		VBox createStudentVBox = new VBox(createTitle(), firstName(), lastName(), selectSemesterNoCreate(),
				studentCreateButton());
		createStudentVBox.setAlignment(Pos.TOP_CENTER);
		VBox deleteStudentVBox = new VBox(deleteTitle(), selectSemesterNoDelete(), selectStudent(),
				studentDeleteButton());
		deleteStudentVBox.setAlignment(Pos.TOP_CENTER);
		VBox historyVBox = new VBox(textAreaTitle(), textArea = new TextArea());

		HBox studentBoxesAndScrollPane = new HBox(createStudentVBox, deleteStudentVBox, historyVBox);
		studentBoxesAndScrollPane.setAlignment(Pos.CENTER);

		vbox = new VBoxWithStyle(title(), studentBoxesAndScrollPane, backButton());
		vbox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vbox, 1800, 980);
		sceneSetup(scene);
	}

	private GridPane firstName() {
		GridPaneCenter grid = new GridPaneCenter();

		tfFirstName = new TextFieldWithStyle("First Name", grid, 0, 0);

		return grid;
	}

	private GridPane lastName() {
		GridPaneCenter grid = new GridPaneCenter();

		tfLastName = new TextFieldWithStyle("Last Name", grid, 0, 0);

		return grid;
	}

	private ComboBox<Integer> selectSemesterNoCreate() {
		slcSemesterCreate = new ComboBox<Integer>(FXCollections.observableArrayList(semesterNo));

		slcSemesterCreate.setPromptText("Select Semester No");

		slcSemesterCreate.setMinSize(150, 50);

		return slcSemesterCreate;
	}

	private ComboBox<Integer> selectSemesterNoDelete() {
		slcSemesterDelete = new ComboBox<Integer>(FXCollections.observableArrayList(semesterNo));

		slcSemesterDelete.setPromptText("Select Semester No");

		semesterSelectEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				slcStudent.setItems(FXCollections
						.observableArrayList(controller.getStudentsBySemesterNo(slcSemesterDelete.getValue())));
			}
		};

		slcSemesterDelete.setOnAction(semesterSelectEvent);

		slcSemesterDelete.setMinSize(150, 50);

		return slcSemesterDelete;
	}

	private ComboBox<Student> selectStudent() {
		slcStudent = new ComboBox<Student>(FXCollections.observableArrayList());

		slcStudent.setMinSize(150, 50);

		return slcStudent;
	}

	//////////////////////////////
	// Buttons
	//////////////////////////////

	private GridPane studentCreateButton() {
		GridPaneCenter grid = new GridPaneCenter();

		ButtonWithStyle createStudentButton = new ButtonWithStyle("Create", grid, 0, 0);
		createStudentButton.setOnAction(e -> {
			Student newStudent = new Student(tfFirstName.getText(), tfLastName.getText(), slcSemesterCreate.getValue());

			controller.createStudent(newStudent);
			textArea.setText(textArea.getText() + newStudent + " has been added.\n");
		});

		return grid;
	}

	private GridPane studentDeleteButton() {
		GridPaneCenter grid = new GridPaneCenter();

		ButtonWithStyle deleteStudentButton = new ButtonWithStyle("Delete", grid, 0, 0);
		deleteStudentButton.setOnAction(e -> {
			Student selectedStudent = slcStudent.getValue();

			controller.deleteStudent(selectedStudent);
			textArea.setText(textArea.getText() + selectedStudent + " has been deleted.\n");

			slcStudent.setItems(FXCollections
					.observableArrayList(controller.getStudentsBySemesterNo(slcSemesterDelete.getValue())));
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
		label.setFont(Font.font("Calibri", FontWeight.BOLD, 60));
		label.setTextFill(Color.web("#F9F9F9"));
		return label;
	}

	private Label deleteTitle() {
		Label label = new Label("Delete Students");
		label.setFont(Font.font("Calibri", FontWeight.BOLD, 60));
		label.setTextFill(Color.web("#F9F9F9"));
		return label;
	}

	private Label textAreaTitle() {
		Label label = new Label("History of Actions");
		label.setFont(Font.font("Calibri", FontWeight.BOLD, 60));
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
