package presentation;

import java.util.ArrayList;
import java.util.Arrays;

import entities.Course;
import entities.Educator;
import javafx.collections.FXCollections;
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

public class EditCourse {
	private Stage primaryStage;
	private DB_Controller controller = new DB_Controller();
	private TextFieldWithStyle tfcourseName;
	private TextAreaWithStyle textArea;
	private ComboBoxWithStyle slcSemesterCreate;
	private ComboBoxWithStyle slcEducator;
	private ComboBoxWithStyle slcCourse;

	public EditCourse(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void editCourseUI() {
		VBox createCourseVBox = new VBox(createTitle(), courseName(), selectSemesterNoCreate(), selectEducator(),
				courseCreateButton());
		vboxSetters(createCourseVBox);

		VBox deleteCourseVBox = new VBox(deleteTitle(), selectCourse(), courseDeleteButton());
		vboxSetters(deleteCourseVBox);

		VBox historyVBox = new VBox(textAreaTitle(), historyArea());
		vboxSetters(historyVBox);

		HBox courseBoxesAndTextArea = new HBox(createCourseVBox, deleteCourseVBox, historyVBox);
		courseBoxesAndTextArea.setAlignment(Pos.CENTER);
		courseBoxesAndTextArea.setPadding(new Insets(10, 10, 10, 10));

		VBoxWithStyle vbox = new VBoxWithStyle(title(), courseBoxesAndTextArea, backButton());
		vbox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vbox, 1800, 980);
		sceneSetup(scene);
	}
	
	private void vboxSetters(VBox vbox) {
		vbox.setAlignment(Pos.TOP_CENTER);
		vbox.setPadding(new Insets(10, 10, 10, 10));
	}

	//////////////////////////////
	// TextFields
	//////////////////////////////

	private GridPane courseName() {
		GridPaneCenter grid = new GridPaneCenter();

		tfcourseName = new TextFieldWithStyle("Course Name", grid, 0, 0);

		return grid;
	}

	//////////////////////////////
	// TextAreas
	//////////////////////////////

	private GridPane historyArea() {
		GridPaneCenter grid = new GridPaneCenter();

		textArea = new TextAreaWithStyle(grid, 0, 0);

		return grid;
	}

	//////////////////////////////
	// ComboBoxes
	//////////////////////////////

	private GridPane selectSemesterNoCreate() {
		GridPaneCenter grid = new GridPaneCenter();
		
		ArrayList<Integer> semesterNo = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));

		slcSemesterCreate = new ComboBoxWithStyle(FXCollections.observableArrayList(semesterNo), grid, 0, 0);
		slcSemesterCreate.setPromptText("Select Semester No");

		return grid;
	}

	private GridPane selectEducator() {
		GridPaneCenter grid = new GridPaneCenter();

		ArrayList<Educator> EducatorsList = controller.getEducatorList();
		slcEducator = new ComboBoxWithStyle(FXCollections.observableArrayList(EducatorsList), grid, 0, 0);
		slcEducator.setPromptText("Select Educator");

		return grid;
	}

	private GridPane selectCourse() {
		GridPaneCenter grid = new GridPaneCenter();

		ArrayList<Course> coursesList = controller.getCourseList();
		slcCourse = new ComboBoxWithStyle(FXCollections.observableArrayList(coursesList), grid, 0, 0);
		slcCourse.setPromptText("Select Course");

		return grid;
	}

	//////////////////////////////
	// Buttons
	//////////////////////////////

	private GridPane courseCreateButton() {
		GridPaneCenter grid = new GridPaneCenter();

		ButtonWithStyle createCourseButton = new ButtonWithStyle("Create", grid, 0, 0);
		createCourseButton.setOnAction(e -> {
			Course newCourse = new Course(tfcourseName.getText(), (int) slcSemesterCreate.getValue(),
					(Educator) slcEducator.getValue());

			controller.createCourse(newCourse);
			textArea.setText(textArea.getText() + "The course " + newCourse + " has been added.\n");

			slcCourse.setItems(FXCollections.observableArrayList(controller.getCourseList()));
		});

		return grid;
	}

	private GridPane courseDeleteButton() {
		GridPaneCenter grid = new GridPaneCenter();

		ButtonWithStyle courseDeleteButton = new ButtonWithStyle("Delete", grid, 0, 0);
		courseDeleteButton.setOnAction(e -> {
			Course selectedCourse = (Course) slcCourse.getValue();

			controller.deleteCourse(selectedCourse);
			textArea.setText(textArea.getText() + "The course " + selectedCourse + " has been deleted.\n");

			slcCourse.setItems(FXCollections.observableArrayList(controller.getCourseList()));
		});

		return grid;
	}

	private GridPane backButton() {
		GridPaneCenter grid = new GridPaneCenter();

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
		Label label = new Label("Edit Courses");
		label.setFont(Font.font("Calibri", FontWeight.BOLD, 60));
		label.setTextFill(Color.web("#F9F9F9"));
		return label;
	}

	private Label createTitle() {
		Label label = new Label("Create Course");
		label.setFont(Font.font("Calibri", FontWeight.BOLD, 50));
		label.setTextFill(Color.web("#F9F9F9"));
		return label;
	}

	private Label deleteTitle() {
		Label label = new Label("Delete Course");
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
