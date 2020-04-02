package presentation;

import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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

	public CourseStatistics(Stage primaryStage, CalculateAttendance selectedData) {
		this.primaryStage = new Stage();
		this.calcAtt = selectedData;
	}

	public void courseStatisticsUI() {
		vbox = new VBoxWithStyle(title(), barDiagram(), lineChart(), attendanceSwitch(), bButton());
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
			yAxis.setLabel("Absence");
		} else {
			yAxis.setLabel("Attendance");
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
		
		barChart.getData().add(barChart.prepareData(calcAtt, diagramSwitch));

		return grid;
	}

	private GridPane lineChart() {
		GridPaneCenter grid = new GridPaneCenter();

		LineChart barChart = new LineChart(averageXAxisData(), averageYAxisData());
		barChart.setPrefSize(1500, 600);
		barChart.getStylesheets().add("/presentation/BarChart.css");

		XYChart.Series sys2DataSeries = new XYChart.Series();
		sys2DataSeries.setName(calcAtt.getCourseList().get(0).getCourseName());

		float[] arr;

		if (!diagramSwitch) {
			arr = calcAtt.calculateAbsence();
		} else {
			arr = calcAtt.studentAttendance();
		}

		int index = 0;
		for (LocalDate i = calcAtt.getStartDate(); i.isBefore(calcAtt.getEndDate().plusDays(1)); i = i.plusDays(1)) {
			sys2DataSeries.getData().add(new XYChart.Data(i.toString(), arr[index++]));
		}

		barChart.getData().add(sys2DataSeries);

		grid.getChildren().add(barChart);

		return grid;
	}

	//////////////////////////////
	// Inverter
	//////////////////////////////

	private GridPane attendanceSwitch() {
		GridPaneCenter grid = new GridPaneCenter();
//		grid.getChildren().remove(0);

		if (!diagramSwitch) {
			RadioButtonWithStyle switchButton = new RadioButtonWithStyle("Attendance", grid, 1, 0);
			switchButton.setOnAction(e -> {
				courseStatisticsUI();
			});
			diagramSwitch = true;
		} else {
			RadioButtonWithStyle switchButton = new RadioButtonWithStyle("Absence", grid, 1, 0);
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
			primaryStage.close();
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
