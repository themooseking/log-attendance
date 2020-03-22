package presentation;

import javafx.scene.Cursor;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;

public class DatePickerWithStyle extends DatePicker {

	public DatePickerWithStyle(GridPane grid, int col, int row) {
		super.getStylesheets().add("/presentation/DatePicker.css");
		super.setPrefSize(200, 120);

		defaultEffect(this);

		super.onMouseEnteredProperty().set(e -> enterEffect(this));
		super.onMouseExitedProperty().set(e -> defaultEffect(this));
		
		GridPane.setConstraints(this, col, row);
		grid.getChildren().add(this);
	}
	
	public void enterEffect(DatePicker obj) {
		obj.setCursor(Cursor.HAND);

	}

	public void defaultEffect(DatePicker obj) {
		obj.setCursor(Cursor.DEFAULT);

	}

}
