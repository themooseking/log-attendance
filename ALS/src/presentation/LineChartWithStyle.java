package presentation;

import java.time.LocalDate;

import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;
import logic.CalculateAttendance;

public class LineChartWithStyle<X, Y> extends LineChart<X, Y> {

	public LineChartWithStyle(GridPane grid, int col, int row, Axis x, Axis y) {
		super(x, y);
		super.setLegendVisible(true);
		super.setAnimated(false);
		super.setCreateSymbols(true);
		super.setAlternativeRowFillVisible(false);
		super.setAlternativeColumnFillVisible(false);
		super.setHorizontalGridLinesVisible(false);
		super.setVerticalGridLinesVisible(false);
		super.getXAxis().setVisible(false);
		super.getYAxis().setVisible(false);
		super.getStylesheets().add("/presentation/LineChart.css");
		super.setPrefSize(1500, 600);

		GridPane.setConstraints(this, col, row);
		grid.getChildren().add(this);
	}
	
	public Series<String, Number> prepareAverageData(CalculateAttendance calcAtt, boolean diagramSwitch) {
		XYChart.Series<String, Number> dataSeries = new XYChart.Series<String, Number>();
//		dataSeries.setName(calcAtt.getCourseList().get(0).getCourseName());
		dataSeries.setName("Average");
		
		float[] arr = null;

		if (!diagramSwitch) {
			arr = calcAtt.averageAbsence();
		} else {
			arr = calcAtt.averageAbsence();
		}
		
		int index = 0;
		for (LocalDate i = calcAtt.getStartDate(); i.isBefore(calcAtt.getEndDate().plusDays(1)); i = i.plusDays(1)) {
			dataSeries.getData().add(new XYChart.Data<String, Number>(i.toString(), arr[index++]));
		}
		
		return dataSeries;
	}
	
}
