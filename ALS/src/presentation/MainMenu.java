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
import logic.LoggedInST;

public class MainMenu {
	private Stage primaryStage;
	private VBoxWithStyle vbox;
	private DB_Controller controller = new DB_Controller();
	private ArrayList<Course> courseList;

	public MainMenu(Stage primaryStage) {
		this.primaryStage = primaryStage;
		courseList = controller.getCoursesByEduId(LoggedInST.getUser().getEducatorId());
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
		vboxLeft.setAlignment(Pos.CENTER);
		VBox vboxMiddle = new VBox(attendanceButton(), logoutButton());
		vboxMiddle.setAlignment(Pos.CENTER);
		VBox vboxRight = new VBox(studentButton(), courseButton());
		vboxRight.setAlignment(Pos.CENTER);

		HBox hbox = new HBox(vboxLeft, vboxMiddle, vboxRight);
		hbox.setAlignment(Pos.CENTER);

		return hbox;
	}

	private GridPane courseButtons() {
		GridPaneCenter grid = new GridPaneCenter();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(20);

		for (int i = 0; i < courseList.size(); i++) {
			Course course = courseList.get(i);
			String courseName = course.getCourseName();
			ButtonWithStyle btn = new ButtonWithStyle(courseName, grid, 0, i);
			btn.setMinSize(300, 150);

			btn.setOnAction(e -> {
				LogAttendance logAttendance = new LogAttendance(primaryStage, course);
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
			new StatisticFilter(primaryStage).filterUI();
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
	
	private GridPane studentButton() {
		GridPaneCenter grid = new GridPaneCenter();
		grid.setPadding(new Insets(10, 10, 10, 50));
		
		ButtonWithStyle stButton = new ButtonWithStyle("Edit Students", grid, 1, 0);
		stButton.setMinSize(300, 150);
		
		stButton.setOnAction(e -> {
			//Student
		});
		
		return grid;
	}
	
	private GridPane courseButton() {
		GridPaneCenter grid = new GridPaneCenter();
		grid.setPadding(new Insets(10, 10, 10, 50));
		
		ButtonWithStyle coButton = new ButtonWithStyle("Edit Courses", grid, 1, 0);
		coButton.setMinSize(300, 150);
		
		coButton.setOnAction(e -> {
			//Course
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
