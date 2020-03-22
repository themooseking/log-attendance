package presentation;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class RadioButtonWithStyle extends RadioButton {

	public RadioButtonWithStyle(String radioButtonText, ToggleGroup tg, GridPane grid, int col, int row) {
		super.setText(radioButtonText);
		super.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
		super.getStylesheets().add("/presentation/RadioButton.css");
		super.setPrefSize(200, 120);

		defaultEffect(this);

		super.onMouseEnteredProperty().set(e -> enterEffect(this));
		super.onMouseExitedProperty().set(e -> defaultEffect(this));
		
		super.setToggleGroup(tg);
		
		GridPane.setConstraints(this, col, row);
		grid.getChildren().add(this);
	}
	
	public void enterEffect(RadioButton obj) {
		obj.setCursor(Cursor.HAND);
		
		obj.setTextFill(Color.RED);
	}

	public void defaultEffect(RadioButton obj) {
		obj.setCursor(Cursor.DEFAULT);

		obj.setTextFill(Color.web("#000000"));
	}

}
