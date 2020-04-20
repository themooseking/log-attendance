package presentation;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

public class ScrollPaneWithStyle extends ScrollPane {

	public ScrollPaneWithStyle(GridPane grid, int col, int row) {
		super.getStylesheets().add("/presentation/ScrollPane.css");
		super.setPrefSize(400, 600);

		defaultEffect(this);

		super.onMouseEnteredProperty().set(e -> enterEffect(this));
		super.onMouseExitedProperty().set(e -> defaultEffect(this));

		GridPane.setConstraints(this, col, row);
		grid.getChildren().add(this);
	}

	private void enterEffect(ScrollPane obj) {
		obj.setPannable(true);
	}

	private void defaultEffect(ScrollPane obj) {
		obj.setPannable(false);

	}
}
