package presentation;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ButtonWithStyle extends Button {

	public ButtonWithStyle(String buttonText, GridPane grid, int col, int row) {
		super.setText(buttonText);
		super.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
		super.setPrefSize(200, 120);

		defaultEffect(this);

		super.onMouseEnteredProperty().set(e -> enterEffect(this));
		super.onMouseExitedProperty().set(e -> defaultEffect(this));
		
		GridPane.setConstraints(this, col, row);
		grid.getChildren().add(this);
	}
	
	private void enterEffect(Button obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#118ABB"), new CornerRadii(0), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setCursor(Cursor.HAND);
		obj.setTextFill(Color.web("#F9F9F9"));
	}

	private void defaultEffect(Button obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#3D3D3D"), new CornerRadii(0), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setCursor(Cursor.DEFAULT);
		obj.setTextFill(Color.web("#F9F9F9"));
	}

}
