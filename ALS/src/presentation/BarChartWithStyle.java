package presentation;

import java.time.LocalDate;
import java.util.ArrayList;

import entities.Course;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;
import logic.CalculateAttendance;

public class BarChartWithStyle<X, Y> extends BarChart<X, Y> {

	public BarChartWithStyle(GridPane grid, int col, int row, Axis x, Axis y) {
		super(x, y);
		super.setLegendVisible(true);
		super.setAnimated(false);
		super.getStylesheets().add("/presentation/BarChart.css");
		super.setPrefSize(1500, 600);

		GridPane.setConstraints(this, col, row);
		grid.getChildren().add(this);
	}
	
	public ArrayList<Series<String, Number>> prepareData(CalculateAttendance calcAtt, boolean diagramSwitch, ArrayList<Course> selectedCourseList) {
		
		ArrayList<XYChart.Series<String, Number>> dataSeriesArr = new ArrayList<XYChart.Series<String,Number>>();
		
		float[] arr;


		for (int i = 0; i < selectedCourseList.size(); i++) {
			int index = 0;

			if (!diagramSwitch) {
				arr = calcAtt.calculateCourseAbsence(i);
			} else {
				arr = calcAtt.calculateCourseAttendance(i);
			}

			XYChart.Series<String, Number> dataSeries = new XYChart.Series<String, Number>();
			dataSeries.setName(selectedCourseList.get(i).getCourseName());

			for (LocalDate ii = calcAtt.getStartDate(); ii
					.isBefore(calcAtt.getEndDate().plusDays(1)); ii = ii.plusDays(1)) {
				dataSeries.getData().add(new XYChart.Data<String, Number>(ii.toString(), arr[index++]));
			}
			dataSeriesArr.add(dataSeries);
		}		
		return dataSeriesArr;
	}
	
}
