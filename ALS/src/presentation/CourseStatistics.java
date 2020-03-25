package presentation;

import java.time.LocalDate;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.CalculateAttendance;

public class CourseStatistics {

	private Stage primaryStage;
	private VBoxWithStyle vbox;
	private CalculateAttendance calcAtt;

	public CourseStatistics(Stage primaryStage, CalculateAttendance selectedData) {
		this.primaryStage = primaryStage;
		this.calcAtt = selectedData;
	}

	public void courseStatisticsUI() {
		vbox = new VBoxWithStyle(title(), barDiagram(), attendanceSelector(), backButton());
		vbox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vbox, 1800, 980);
		sceneSetup(scene);
	}

	private GridPane barDiagram() {
		GridPaneCenter grid = new GridPaneCenter();

		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Days");

		NumberAxis yAxis = new NumberAxis(0, 100, 5);
		yAxis.setLabel("Absence");

		BarChart barChart = new BarChart(xAxis, yAxis);
		barChart.setPrefSize(1500, 600);
		barChart.getStylesheets().add("/presentation/BarChart.css");

		XYChart.Series sys2DataSeries = new XYChart.Series();
		sys2DataSeries.setName("Systemudvikling2");

		float[] arr = calcAtt.calculateAbsence();
		
		int index = 0;
		for (LocalDate i = calcAtt.getStartDate(); i.isBefore(calcAtt.getEndDate().plusDays(1)); i = i.plusDays(1)) {
			sys2DataSeries.getData().add(new XYChart.Data(i.toString(), arr[index++]));
		}

//        sys2DataSeries.getData().add(new XYChart.Data("Monday", 10));
//        sys2DataSeries.getData().add(new XYChart.Data("Tuesday"  , 5));
//        sys2DataSeries.getData().add(new XYChart.Data("Wednesday"  , 100));
//        sys2DataSeries.getData().add(new XYChart.Data("Thursday", 5));
//        sys2DataSeries.getData().add(new XYChart.Data("Friday"  , 30));

		barChart.getData().add(sys2DataSeries);

		grid.getChildren().add(barChart);

		return grid;
	}
	
	//////////////////////////////
	// Selector
	//////////////////////////////

	private GridPane attendanceSelector() {
		GridPaneCenter grid = new GridPaneCenter();
		
		RadioButtonWithStyle selector = new RadioButtonWithStyle("Fremmøde", grid, 1, 0);
		boolean isSelected = selector.isSelected();
		if (isSelected) {
			calcAtt.studentAttendance();
		}
		return grid;
	}

	//////////////////////////////
	// Back
	//////////////////////////////
	
	private GridPane backButton() {
		GridPaneCenter grid = new GridPaneCenter();

		ButtonWithStyle btnBack = new ButtonWithStyle("Back", grid, 1, 0);
		btnBack.setOnAction(e ->
			new StatisticFilter(primaryStage).filterUI()
		);

		return grid;
	}
	
	//////////////////////////////
	// Label Title
	//////////////////////////////

	private Label title() {
		Label label = new Label("2 Semester stats :)");
		label.setFont(Font.font("Calibri", FontWeight.BOLD, 60));
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
