package presentation;

import javafx.scene.Cursor;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class RadioButtonWithStyle extends RadioButton {

	public RadioButtonWithStyle(String radioButtonText, GridPane grid, int col, int row) {
		super.setText(radioButtonText);
		super.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
		super.getStylesheets().add("/presentation/RadioButton.css");
		super.setPrefSize(200, 120);

		defaultEffect(this);

		super.onMouseEnteredProperty().set(e -> enterEffect(this));
		super.onMouseExitedProperty().set(e -> defaultEffect(this));
		
		GridPane.setConstraints(this, col, row);
		grid.getChildren().add(this);
	}
	
	public void enterEffect(RadioButton obj) {
		obj.setCursor(Cursor.HAND);
		
		obj.setTextFill(Color.web("#118ABB"));
	}

	public void defaultEffect(RadioButton obj) {
		obj.setCursor(Cursor.DEFAULT);

		obj.setTextFill(Color.web("#F9F9F9"));
	}

}
