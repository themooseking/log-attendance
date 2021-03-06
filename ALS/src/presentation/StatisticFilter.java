package presentation;

import java.time.LocalDate;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.CalculateAttendance;
import logic.DB_Controller;

public class StatisticFilter {

	private Stage primaryStage;
	private DB_Controller controller = new DB_Controller();
	private LocalDate startDate = LocalDate.now().minusDays(7);
	private LocalDate endDate = LocalDate.now();
	private ArrayList<Course> courseList = controller.getCourseList();
	private ArrayList<Course> selectedCourseList = new ArrayList<Course>();
	private ArrayList<Student> studentList = new ArrayList<Student>();
	private ArrayList<Student> selectedStudentList = new ArrayList<Student>();
	private ScrollPaneWithStyle ssp;
	private ButtonWithStyle btnFetch;

	public StatisticFilter(Stage primaryStage) {
		this.primaryStage = primaryStage;
		sortStudentList();
	}

	public void filterUI() {
		VBox dpBox = new VBox(startDatePicker(), endDatePicker());
		dpBox.setAlignment(Pos.CENTER);

		HBox hbox = new HBox(courseScrollPane(), studentScrollPane(), dpBox);
		hbox.setAlignment(Pos.CENTER);

		VBoxWithStyle vbox = new VBoxWithStyle(title(), hbox, buttons());
		vbox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vbox, 1800, 980);
		sceneSetup(scene);
	}

	//////////////////////////////
	// COURSE
	//////////////////////////////

	private GridPane courseScrollPane() {
		GridPaneCenter grid = new GridPaneCenter();

		ScrollPaneWithStyle sp = new ScrollPaneWithStyle(grid, 0, 0);
		sp.setContent(courseSetup());

		return grid;
	}

	private GridPane courseSetup() {
		GridPaneCenter grid = new GridPaneCenter();

		for (int i = 0; i < courseList.size(); i++) {
			String courseName = courseList.get(i).getCourseName();

			RadioButtonWithStyle rb = new RadioButtonWithStyle(courseName, grid, 0, i);
			rb.setPadding(new Insets(0, 0, 0, 20));
			rb.setOnAction(e -> {
				for (int j = 0; j < courseList.size(); j++) {
					boolean checker = rb.getText().equals(courseList.get(j).getCourseName());

					if (checker && rb.isSelected()) {
						selectedStudentList.clear();
						selectedCourseList.add(courseList.get(j));
						updateStudentScrollPane();
						btnFetch.setDisable(false);
					} else if (checker && !rb.isSelected()) {
						selectedStudentList.clear();
						selectedCourseList.remove(courseList.get(j));
						updateStudentScrollPane();
						if (selectedCourseList.isEmpty()) {
							btnFetch.setDisable(true);
						}
					}
				}
			});
		}
		return grid;
	}

	private void updateStudentScrollPane() {
		ssp.setContent(null);
		studentList.clear();
		studentList.addAll(controller.getStudentsByCourseList(selectedCourseList));
		ssp.setContent(studentSetup());
	}

	//////////////////////////////
	// STUDENT
	//////////////////////////////

	private GridPane studentScrollPane() {
		GridPaneCenter grid = new GridPaneCenter();

		ssp = new ScrollPaneWithStyle(grid, 0, 0);
		ssp.setPrefWidth(550);
		ssp.setContent(studentSetup());

		return grid;
	}

	private GridPane studentSetup() {
		GridPaneCenter grid = new GridPaneCenter();

		selectedStudentList.addAll(studentList);

		for (int i = 0; i < studentList.size(); i++) {
			RadioButtonWithStyle rb = new RadioButtonWithStyle(studentList.get(i).toString(), grid, 0, i);
			rb.setSelected(true);
			rb.setPrefWidth(500);
			rb.setPadding(new Insets(0, 0, 0, 20));
			rb.setOnAction(e -> {
				for (int j = 0; j < studentList.size(); j++) {
					boolean checker = rb.getText().equals(studentList.get(j).toString());

					if (checker && rb.isSelected()) {
						selectedStudentList.add(studentList.get(j));
						btnFetch.setDisable(false);
					} else if (checker && !rb.isSelected()) {
						selectedStudentList.remove(studentList.get(j));
						if (selectedStudentList.isEmpty()) {
							btnFetch.setDisable(true);
						}
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

		btnFetch = new ButtonWithStyle("Fetch", grid, 0, 0);
		btnFetch.setDisable(true);
		;
		btnFetch.setOnAction(e -> {
			CalculateAttendance selectedData = new CalculateAttendance(startDate, endDate, selectedCourseList,
					selectedStudentList);
			new CourseStatistics(primaryStage, selectedData, selectedCourseList).courseStatisticsUI();
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

	//////////////////////////////
	// Sort List
	//////////////////////////////

	private void sortStudentList() {
		Collections.sort(studentList, Student.StuNameComparator);
	}
}
