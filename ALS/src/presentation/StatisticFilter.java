package presentation;

import java.time.LocalDate;

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
import logic.DB_Controller;

public class StatisticFilter {

	private Stage primaryStage;
	private VBoxWithStyle vbox;
	private HBox hbox;
	private DB_Controller controller = new DB_Controller();
	private LocalDate startDate = LocalDate.now().minusDays(7);
	private LocalDate endDate = LocalDate.now();
	private String selectedCourse;

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
		ToggleGroup tg = new ToggleGroup();

		for (int i = 0; i < controller.getCourseList().size(); i++) {
			String courseName = controller.getCourseList().get(i).getCourseName();

			RadioButtonWithStyle rb = new RadioButtonWithStyle(courseName, tg, grid, 0, i);
			if (i == 0) {
				rb.setSelected(true);
			}
			rb.setOnAction(e -> {
				selectedCourse = rb.getText();
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
			startDate = startDP.getValue();
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
			} else {
				endDate = endDP.getValue();
			}
		});

		return grid;
	}

	//////////////////////////////
	// FETCH AND BACK
	//////////////////////////////

	private GridPane buttons() {
		GridPaneCenter grid = new GridPaneCenter();

		ButtonWithStyle btnFetch = new ButtonWithStyle("Fetch", grid, 0, 0);
		btnFetch.setOnAction(e -> {
			new CourseStatistics(primaryStage, startDate, endDate, selectedCourse).courseStatisticsUI();
		});

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
