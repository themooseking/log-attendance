package presentation;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ComboBoxWithStyle extends ComboBox<Object> {

	public ComboBoxWithStyle(ObservableList<Object> observableList, GridPaneCenter grid, int row, int col) {
		super(observableList);
		super.getStylesheets().add("/presentation/ComboBox.css");

		super.setPrefSize(400, 80);

		defaultEffect(this);

		super.onMouseEnteredProperty().set(e -> enterEffect(this));
		super.onMouseExitedProperty().set(e -> defaultEffect(this));
		
		GridPane.setConstraints(grid, row, col);
		grid.getChildren().add(this); 
	}

	
	private void enterEffect(ComboBox<Object> obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#118ABB"), new CornerRadii(0), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setCursor(Cursor.HAND);
	}
	
	private void defaultEffect(ComboBox<Object> obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#F9F9F9"), new CornerRadii(0), Insets.EMPTY);
		Background background = new Background(background_fill);
		
		obj.setBackground(background);
		obj.setCursor(Cursor.DEFAULT);
	}
}
