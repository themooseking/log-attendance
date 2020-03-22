package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.DB_Controller;

public class StatisticFilter {

	private Stage primaryStage;
	private VBoxWithStyle vbox;
	private HBox hbox;
	private DB_Controller controller = new DB_Controller();

	public StatisticFilter(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void filterUI() {
		VBox dpBox = new VBox(startDatePicker(), endDatePicker());
		dpBox.setAlignment(Pos.CENTER);
		
		hbox = new HBox(courseSetup(), dpBox);
		hbox.setAlignment(Pos.CENTER);
		
		vbox = new VBoxWithStyle(title(), hbox);
		vbox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vbox, 1800, 980);
		sceneSetup(scene);
	}

	private GridPane courseSetup() {
		GridPaneCenter grid = new GridPaneCenter();

		ToggleGroup tg = new ToggleGroup();

		for (int i = 0; i < controller.getCourseList().size(); i++) {
			RadioButtonWithStyle rb = new RadioButtonWithStyle(controller.getCourseList().get(i).getCourseName(), tg,
					grid, 0, i);
		}
		return grid;
	}
	
	//////////////////////////////
	// DATEPICKERS
	//////////////////////////////
	
	private GridPane startDatePicker() {
		GridPaneCenter grid = new GridPaneCenter();
		grid.setPadding(new Insets(10, 10, 10, 10));
		
		DatePickerWithStyle startDP = new DatePickerWithStyle(grid, 0, 0);
		
		return grid;
	}
	
	private GridPane endDatePicker() {
		GridPaneCenter grid = new GridPaneCenter();
		
		DatePickerWithStyle endDP = new DatePickerWithStyle(grid, 0, 0);
		
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
