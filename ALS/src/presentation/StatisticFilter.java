package presentation;

import java.time.LocalDate;
import java.util.ArrayList;

import entities.Course;
import entities.Student;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.CalculateAttendance;
import logic.DB_Controller;

public class StatisticFilter {

	private Stage primaryStage;
	private VBoxWithStyle vbox;
	private HBox hbox;
	private DB_Controller controller = new DB_Controller();
	private LocalDate startDate = LocalDate.now().minusDays(7);
	private LocalDate endDate = LocalDate.now();
	private ArrayList<Course> courseList = controller.getCourseList();
	private ArrayList<Course> selectedCourseList = new ArrayList<Course>();

	public StatisticFilter(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void filterUI() {
		VBox dpBox = new VBox(startDatePicker(), endDatePicker());
		dpBox.setAlignment(Pos.CENTER);

		hbox = new HBox(courseSetup(), dpBox);
		hbox.setAlignment(Pos.CENTER);

		vbox = new VBoxWithStyle(title(), hbox, buttons());
		vbox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vbox, 1800, 980);
		sceneSetup(scene);
	}

	private GridPane courseSetup() {
		GridPaneCenter grid = new GridPaneCenter();

		for (int i = 0; i < courseList.size(); i++) {
			String courseName = courseList.get(i).getCourseName();

			RadioButtonWithStyle rb = new RadioButtonWithStyle(courseName, grid, 0, i);
			rb.setOnAction(e -> {
				for (int j = 0; j < courseList.size(); j++) {
					boolean checker = rb.getText().equals(courseList.get(j).getCourseName());
					
					if (checker && rb.isSelected()) {
						selectedCourseList.add(courseList.get(j));
					} else if (checker && !rb.isSelected()) {
						selectedCourseList.remove(courseList.get(j));
					}
				}
			});
		}
		return grid;
	}

	//////////////////////////////
	// DATEPICKERS
	//////////////////////////////

	private GridPane startDatePicker() {
		GridPaneCenter grid = new GridPaneCenter();
		grid.setPadding(new Insets(10, 10, 10, 10));

		DatePickerWithStyle startDP = new DatePickerWithStyle(grid, 0, 0);
		startDP.setValue(startDate);
		startDP.setOnAction(e -> {
			if (startDP.getValue().isAfter(endDate)) {
				startDP.setValue(startDate);
			} else {
				startDate = startDP.getValue();
			}
		});

		return grid;
	}

	private GridPane endDatePicker() {
		GridPaneCenter grid = new GridPaneCenter();

		DatePickerWithStyle endDP = new DatePickerWithStyle(grid, 0, 0);
		endDP.setValue(endDate);
		endDP.setOnAction(e -> {
			if (endDP.getValue().isAfter(LocalDate.now())) {
				endDP.setValue(endDate);
			} else if (endDP.getValue().isBefore(startDate)) {
				endDP.setValue(endDate);
			} else {
				endDate = endDP.getValue();
			}
		});

		return grid;
	}

	//////////////////////////////
	// FETCH AND BACK
	//////////////////////////////

	private HBox buttons() {
		HBox hbox = new HBox(fetchButton(), backButton());
		hbox.setAlignment(Pos.CENTER);

		return hbox;
	}

	private GridPane fetchButton() {
		GridPaneCenter grid = new GridPaneCenter();
		grid.setPadding(new Insets(10, 10, 10, 10));

		ButtonWithStyle btnFetch = new ButtonWithStyle("Fetch", grid, 0, 0);
		btnFetch.setOnAction(e -> {
			CalculateAttendance selectedData = new CalculateAttendance(startDate, endDate, selectedCourseList);
			new CourseStatistics(primaryStage, selectedData).courseStatisticsUI();
		});

		return grid;
	}

	private GridPane backButton() {
		GridPaneCenter grid = new GridPaneCenter();

		ButtonWithStyle btnBack = new ButtonWithStyle("Back", grid, 1, 0);
		btnBack.setOnAction(e -> {
			// Previous screen
		});

		return grid;
	}

	//////////////////////////////
	// Label Title
	//////////////////////////////

	private Label title() {
		Label label = new Label("Filter");
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
