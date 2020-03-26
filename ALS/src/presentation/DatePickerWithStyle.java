package presentation;

import javafx.scene.Cursor;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;

public class DatePickerWithStyle extends DatePicker {

	public DatePickerWithStyle(GridPane grid, int col, int row) {
		super.getStylesheets().add("/presentation/DatePicker.css");
		super.setPrefSize(400, 120);

		defaultEffect(this);

		super.onMouseEnteredProperty().set(e -> enterEffect(this));
		super.onMouseExitedProperty().set(e -> defaultEffect(this));
		
		GridPane.setConstraints(this, col, row);
		grid.getChildren().add(this);
	}
	
	private void enterEffect(DatePicker obj) {
		obj.setCursor(Cursor.HAND);

	}

	private void defaultEffect(DatePicker obj) {
		obj.setCursor(Cursor.DEFAULT);

	}

}
