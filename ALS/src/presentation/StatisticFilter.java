package presentation;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.DB_Controller;

public class StatisticFilter {

	private Stage primaryStage;
	private VBox vbox;
	private DB_Controller controller = new DB_Controller();

	public StatisticFilter(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void filterUI() {
		vbox = new VBox(title(), courseSetup());
		vbox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vbox, 1800, 980);
		sceneSetup(scene);
	}

	private GridPane courseSetup() {
		GridPaneCenter grid = new GridPaneCenter();
		
		ToggleGroup tg = new ToggleGroup();

		for (int i = 0; i < controller.getCourseList().size(); i++) {
			RadioButtonWithStyle rb = new RadioButtonWithStyle(controller.getCourseList().get(i).getCourseName(), tg, grid, 0, i);
		}

		return grid;
	}

	//////////////////////////////
	// Label Title
	//////////////////////////////

	private Label title() {
		Label label = new Label("Filter");
		label.setFont(Font.font("Calibri", FontWeight.BOLD, 60));
		return label;
	}

	//////////////////////////////
	// Scene stuff
	//////////////////////////////

	private void sceneSetup(Scene scene) {
		primaryStage.setTitle("ALS");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
