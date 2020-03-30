package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainMenu {
	private Stage primaryStage;
	private VBoxWithStyle vbox;
	private int Educator;
	private String Lesson1;
	private String Lesson2;  
	
	public MainMenu(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
		
	public void mainMenuUI() {
		vbox = new VBoxWithStyle(title(), tButton(), bButton());
		vbox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vbox, 1800, 980);
		sceneSetup(scene);
	}
	
	//////////////////////////////
	// Hentning af laerer
	//////////////////////////////
	
	public void educatorLogin() {
		Educator = 1;
//		Educator = 2;
	
		if(Educator == 1) {
			Lesson1 = "Protek2";
			Lesson2 = "Protek4";
		}
		
		else if(Educator == 2) {
			Lesson1 = "SYS2";
			Lesson2 = "SYS4";
		}
	}

	//////////////////////////////
	// Top Buttons
	//////////////////////////////
	
	private HBox tButton() {
		HBox hbox = new HBox(firstLesson(Lesson1), attendanceButton());
		hbox.setPadding(new Insets(100, 100, 100, 100));
		hbox.setAlignment(Pos.CENTER);
		 
		return hbox;
	}
	
	private GridPane firstLesson(String Lesson1) {
		GridPaneCenter grid = new GridPaneCenter();
		grid.setPadding(new Insets(10, 50, 10, 10));

		ButtonWithStyle firstLesson = new ButtonWithStyle(Lesson1, grid, 0, 0);
		firstLesson.setMinSize(300, 150);
		
		firstLesson.setOnAction(e -> {
		//logattendance	
		});

		return grid;
	}
	
	private GridPane attendanceButton() {
		GridPaneCenter grid = new GridPaneCenter();
		grid.setPadding(new Insets(10, 10, 10, 50));
		
		ButtonWithStyle atdButton = new ButtonWithStyle("Attendance", grid, 1, 0);
		atdButton.setMinSize(300, 150);
		
		atdButton.setOnAction(e ->
			new StatisticFilter(primaryStage).filterUI()
		);
		
		return grid;
	}
	
	//////////////////////////////
	// Bot Buttons
	//////////////////////////////
	
	private HBox bButton() {
		HBox hbox = new HBox(secondLesson(Lesson2), logoutButton());
		hbox.setPadding(new Insets(25, 100, 100, 100));
		hbox.setAlignment(Pos.CENTER);
		 
		return hbox;
	}
	
	private GridPane secondLesson(String Lesson2) {
		GridPaneCenter grid = new GridPaneCenter();
		grid.setPadding(new Insets(10, 50, 10, 10));
		
		ButtonWithStyle secondLesson = new ButtonWithStyle(Lesson2, grid, 0, 0);
		secondLesson.setMinSize(300, 150);
		
		secondLesson.setOnAction(e -> {
		//logattendance	
		});

		return grid;
	}
	
	private GridPane logoutButton() {
		GridPaneCenter grid = new GridPaneCenter();
		grid.setPadding(new Insets(10, 10, 10, 50));
		
		ButtonWithStyle loButton = new ButtonWithStyle("Logout", grid, 1, 0);
		loButton.setMinSize(300, 150);
		
		loButton.setOnAction(e -> {
		//Logout	
		});
		
		return grid;
	}
	
	//////////////////////////////
	// Label Title
	//////////////////////////////

	private Label title() {
		Label label = new Label("MainMenu");
		label.setFont(Font.font("Calibri", FontWeight.BOLD, 60));
		label.setTextFill(Color.web("#F9F9F9"));
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
