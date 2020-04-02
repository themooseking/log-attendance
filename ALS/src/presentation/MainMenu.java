package presentation;

import java.util.ArrayList;

import entities.Course;
import entities.Educator;
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

public class MainMenu {
	private Stage primaryStage;
	private VBoxWithStyle vbox;
	private Educator educator;
	private DB_Controller controller = new DB_Controller();
	private ArrayList<Course> courseList;
//	private int Educator;
//	private String Lesson1;
//	private String Lesson2;  

	public MainMenu(Stage primaryStage, Educator educator) {
		this.primaryStage = primaryStage;
		this.educator = educator;
		courseList = controller.getCoursesByEduId(educator.getEducatorId());
	}

	public void mainMenuUI() {
		vbox = new VBoxWithStyle(title(), gridSetup());
		vbox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vbox, 1800, 980); 
		sceneSetup(scene);
	}

	//////////////////////////////
	// Buttons
	//////////////////////////////

	private HBox gridSetup() {
		VBox vboxLeft = new VBox(courseButtons());
		VBox vboxRight = new VBox(attendanceButton(), logoutButton());

		HBox hbox = new HBox(vboxLeft, vboxRight);
		hbox.setAlignment(Pos.CENTER);

		return hbox;
	}

	private GridPane courseButtons() {
		GridPaneCenter grid = new GridPaneCenter();

		for (int i = 0; i < courseList.size(); i++) {
			Course course = courseList.get(i);
			String courseName = course.getCourseName();
			ButtonWithStyle btn = new ButtonWithStyle(courseName, grid, 0, i);

			btn.setOnAction(e -> {
				LogAttendance logAttendance = new LogAttendance(course);
				logAttendance.logAttendanceUI();
			});
		}

		return grid;
	}

	private GridPane attendanceButton() {
		GridPaneCenter grid = new GridPaneCenter();
		grid.setPadding(new Insets(10, 10, 10, 50));

		ButtonWithStyle atdButton = new ButtonWithStyle("Attendance", grid, 1, 0);
		atdButton.setMinSize(300, 150);

		atdButton.setOnAction(e -> {
			new StatisticFilter(primaryStage, educator).filterUI();
		});

		return grid;
	}

	private GridPane logoutButton() {
		GridPaneCenter grid = new GridPaneCenter();
		grid.setPadding(new Insets(10, 10, 10, 50));

		ButtonWithStyle loButton = new ButtonWithStyle("Logout", grid, 1, 0);
		loButton.setMinSize(300, 150);

		loButton.setOnAction(e -> {
			new LoginScreen(primaryStage).loginUI();
		});

		return grid;
	}

	//////////////////////////////
	// Label Title
	//////////////////////////////

	private Label title() {
		Label label = new Label("MainMenu");
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
