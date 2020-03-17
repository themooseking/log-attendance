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
	
	public void enterEffect(Button obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#707070"), new CornerRadii(10), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 5; -fx-border-color: #707070; -fx-border-width: 3;");
		obj.setCursor(Cursor.HAND);

		obj.setTextFill(Color.web("#FFFFFF"));
	}

	public void defaultEffect(Button obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#FFFFFF"), new CornerRadii(10), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 5; -fx-border-color: #707070; -fx-border-width: 3;");
		obj.setCursor(Cursor.DEFAULT);

		obj.setTextFill(Color.web("#000000"));
	}

}
