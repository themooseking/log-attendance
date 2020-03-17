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
	
	public void enterEffect(TextField obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#707070"), new CornerRadii(10), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 5; -fx-border-color: #707070; -fx-border-width: 3;");
		obj.setCursor(Cursor.HAND);
	}
	
	public void defaultEffect(TextField obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#FFFFFF"), new CornerRadii(10), Insets.EMPTY);
		Background background = new Background(background_fill);
		
		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 5; -fx-border-color: #707070; -fx-border-width: 3;");
		obj.setCursor(Cursor.DEFAULT);
	}
}
