package presentation;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TextAreaWithStyle extends TextArea{
	public TextAreaWithStyle(GridPaneCenter grid, int row, int col) {
		super.getStylesheets().add("/presentation/TextArea.css");

		super.setFont(Font.font("Calibri", 16));
		super.setPrefSize(400, 1200);
		super.setWrapText(true);
		super.setEditable(false);

		defaultEffect(this);

		super.onMouseEnteredProperty().set(e -> enterEffect(this));
		super.onMouseExitedProperty().set(e -> defaultEffect(this));
		
		GridPane.setConstraints(grid, row, col);
		grid.getChildren().add(this);
	}
	
	private void enterEffect(TextArea obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#118ABB"), new CornerRadii(0), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setCursor(Cursor.HAND);
	}
	
	private void defaultEffect(TextArea obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#F9F9F9"), new CornerRadii(0), Insets.EMPTY);
		Background background = new Background(background_fill);
		
		obj.setBackground(background);
		obj.setCursor(Cursor.DEFAULT);
	}
}
