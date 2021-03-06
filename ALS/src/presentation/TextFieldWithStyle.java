package presentation;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TextFieldWithStyle extends TextField {

	public TextFieldWithStyle(String prompt, GridPaneCenter grid, int row, int col) {
		super.setPromptText(prompt);
		super.getStylesheets().add("/presentation/TextField.css");

		super.setFont(Font.font("Calibri", 24));
		super.setPrefSize(400, 80);

		defaultEffect(this);

		super.onMouseEnteredProperty().set(e -> enterEffect(this));
		super.onMouseExitedProperty().set(e -> defaultEffect(this));
		
		GridPane.setConstraints(grid, row, col);
		grid.getChildren().add(this);
	}
	
	private void enterEffect(TextField obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#118ABB"), new CornerRadii(0), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setCursor(Cursor.HAND);
	}
	
	private void defaultEffect(TextField obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#F9F9F9"), new CornerRadii(0), Insets.EMPTY);
		Background background = new Background(background_fill);
		
		obj.setBackground(background);
		obj.setCursor(Cursor.DEFAULT);
	}
}
