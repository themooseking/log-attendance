package presentation;

import java.util.ArrayList;
import java.util.Collections;

import entities.Course;
import entities.Student;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.DB_Controller;
import logic.RegisterAbsence;

public class LogAttendance {

	private Stage primaryStage;
	private VBoxWithStyle vbox;
	private HBox hbox;
	private DB_Controller controller = new DB_Controller();
	private Course selectedCourse;
	private ArrayList<Student> studentList;
	private ArrayList<Student> selectedStudentList = new ArrayList<Student>();

	public LogAttendance(Stage primaryStage, Course selectedCourse) {
		this.primaryStage = primaryStage;
		this.selectedCourse = selectedCourse;
		studentList = controller.getStudentsByCourse(selectedCourse);
		sortStudentList();
	}

	public void logAttendanceUI() {

		hbox = new HBox(studentSetup());
		hbox.setAlignment(Pos.CENTER);

		vbox = new VBoxWithStyle(title(), hbox, buttons());
		vbox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vbox, 1800, 980);
		sceneSetup(scene);
	}

	private GridPane studentSetup() {
		GridPaneCenter grid = new GridPaneCenter();
		int x = 0;
		int y = 0;

		for (int i = 0; i < studentList.size(); i++) {
			String studentName = studentList.get(i).getFirstName() + " " + studentList.get(i).getLastName();
			RadioButtonWithStyle rb = new RadioButtonWithStyle(studentName, grid, x, y);
			rb.setPrefWidth(500);
			rb.setOnAction(e -> {
				for (int j = 0; j < studentList.size(); j++) {
					boolean checker = rb.getText()
							.equals(studentList.get(j).getFirstName() + " " + studentList.get(j).getLastName());

					if (checker && rb.isSelected()) {
						selectedStudentList.add(studentList.get(j));
					} else if (checker && !rb.isSelected()) {
						selectedStudentList.remove(studentList.get(j));
					}
				}
			});

			y++;
			if (y == 12) {
				y = 0;
				x++;
			}
		}

		return grid;
	}

	//////////////////////////////
	// FETCH AND BACK
	//////////////////////////////

	private HBox buttons() {
		HBox hbox = new HBox(LogButton(), backButton());
		hbox.setAlignment(Pos.CENTER);

		return hbox;
	}

	private GridPane LogButton() {
		GridPaneCenter grid = new GridPaneCenter();
		grid.setPadding(new Insets(10, 10, 10, 10));

		ButtonWithStyle btnLog = new ButtonWithStyle("Log", grid, 0, 0);
		btnLog.setOnAction(e -> {
			new RegisterAbsence(selectedStudentList, selectedCourse).logAbsence();
			new MainMenu(primaryStage).mainMenuUI();
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
	// Label Title
	//////////////////////////////

	private Label title() {
		Label label = new Label("Log Absence for " + selectedCourse.getCourseName());
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

	//////////////////////////////
	// Sort List
	//////////////////////////////

	private void sortStudentList() {
		Collections.sort(studentList, Student.StuNameComparator);
	}
}
