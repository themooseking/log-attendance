package presentation;

import java.time.LocalDate;

import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;
import logic.CalculateAttendance;

public class BarChartWithStyle<X, Y> extends BarChart<X, Y> {

	public BarChartWithStyle(GridPane grid, int col, int row, Axis x, Axis y) {
		super(x, y);
		super.getStylesheets().add("/presentation/BarChart.css");
		super.setPrefSize(1500, 600);

		GridPane.setConstraints(this, col, row);
		grid.getChildren().add(this);
	}
	
	public Series<String, Number> prepareData(CalculateAttendance calcAtt, boolean diagramSwitch) {
		XYChart.Series<String, Number> dataSeries = new XYChart.Series<String, Number>();
//		dataSeries.setName(calcAtt.getCourseList().get(0).getCourseName());
		dataSeries.setName("Average");
		
		float[] arr;

		if (!diagramSwitch) {
			arr = calcAtt.calculateAbsence();
		} else {
			arr = calcAtt.studentAttendance();
		}
		
		int index = 0;
		for (LocalDate i = calcAtt.getStartDate(); i.isBefore(calcAtt.getEndDate().plusDays(1)); i = i.plusDays(1)) {
			dataSeries.getData().add(new XYChart.Data<String, Number>(i.toString(), arr[index++]));
		}
		
		return dataSeries;
	}
	
}
