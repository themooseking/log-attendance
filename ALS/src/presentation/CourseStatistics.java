package presentation;

import java.time.LocalDate;
import java.util.ArrayList;

import entities.Course;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.CalculateAttendance;

public class CourseStatistics {

	private Stage primaryStage;
	private VBoxWithStyle vbox;
	private CalculateAttendance calcAtt;
	private boolean diagramSwitch;
	private ArrayList<Course> selectedCourseList;

	public CourseStatistics(Stage primaryStage, CalculateAttendance selectedData,
			ArrayList<Course> selectedCourseList) {
		this.primaryStage = primaryStage;
		this.calcAtt = selectedData;
		this.selectedCourseList = selectedCourseList;
	}

	public void courseStatisticsUI() {
		vbox = new VBoxWithStyle(title(), barDiagram(), attendanceSwitch(), bButton());
		vbox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vbox, 1800, 980);
		sceneSetup(scene);
	}

	//////////////////////////////
	// Axis Data
	//////////////////////////////

	private CategoryAxis averageXAxisData() {
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Days");

		return xAxis;
	}

	private NumberAxis averageYAxisData() {
		NumberAxis yAxis = new NumberAxis(0, 100, 5);

		if (diagramSwitch) {
			yAxis.setLabel("Attendance");
		} else {
			yAxis.setLabel("Absence");
		}

		return yAxis;
	}

	//////////////////////////////
	// Charts and Diagrams
	//////////////////////////////

	private GridPane barDiagram() {
		GridPaneCenter grid = new GridPaneCenter();

		BarChartWithStyle<String, Number> barChart = new BarChartWithStyle<String, Number>(grid, 0, 0,
				averageXAxisData(), averageYAxisData());

		LineChartWithStyle<String, Number> lineChart = new LineChartWithStyle<String, Number>(grid, 0, 0,
				averageXAxisData(), averageYAxisData());

		ArrayList<Series<String, Number>> dataSeriesArr = barChart.prepareData(calcAtt, diagramSwitch,
				selectedCourseList);
		barChart.getData().addAll(dataSeriesArr);
		lineChart.getData().add(lineChart.prepareAverageData(calcAtt, diagramSwitch));

		return grid;
	}

	//////////////////////////////
	// Inverter
	//////////////////////////////

	private GridPane attendanceSwitch() {
		GridPaneCenter grid = new GridPaneCenter();
//		grid.getChildren().remove(0);

		if (!diagramSwitch) {
			RadioButtonWithStyle switchButton = new RadioButtonWithStyle("Absence", grid, 1, 0);
			switchButton.setOnAction(e -> {
				courseStatisticsUI();
			});
			diagramSwitch = true;
		} else {
			RadioButtonWithStyle switchButton = new RadioButtonWithStyle("Attendance", grid, 1, 0);
			switchButton.setOnAction(e -> {
				courseStatisticsUI();
			});
			diagramSwitch = false;
		}

		return grid;
	}

	//////////////////////////////
	// Back
	//////////////////////////////

	private HBox bButton() {
		HBox hbox = new HBox(backButton());
		hbox.setPadding(new Insets(10, 40, 10, 40));
		hbox.setAlignment(Pos.CENTER_RIGHT);

		return hbox;
	}

	private GridPane backButton() {
		GridPaneCenter grid = new GridPaneCenter();

		ButtonWithStyle btnBack = new ButtonWithStyle("Back", grid, 1, 0);
		btnBack.setOnAction(e -> {
			new StatisticFilter(primaryStage).filterUI();
		});

		return grid;
	}

	//////////////////////////////
	// Label Title
	//////////////////////////////

	private Label title() {
		Label label = new Label("Data");
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
