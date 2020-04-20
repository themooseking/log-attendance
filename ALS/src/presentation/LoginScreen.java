package presentation;

import java.util.ArrayList;

import entities.Educator;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.DB_Controller;
import logic.LoggedInST;

public class LoginScreen {
	
	private Stage primaryStage;
	private ComboBoxWithStyle selectedEducator;

	public LoginScreen(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void loginUI() {
		VBoxWithStyle vbox = new VBoxWithStyle(title(), selectEducator(), loginSetup());
		vbox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vbox, 1800, 980);
		sceneSetup(scene);
	}

	private GridPane selectEducator() {
		GridPaneCenter grid = new GridPaneCenter();
		grid.setPadding(new Insets(30));
		
		ArrayList<Educator> educatorList = new DB_Controller().getEducatorList();

		selectedEducator = new ComboBoxWithStyle(FXCollections.observableArrayList(educatorList), grid, 0, 0);
		if (LoggedInST.getUser() == null) {
			selectedEducator.setValue(educatorList.get(0));
		} else {
			selectedEducator.setValue(LoggedInST.getUser());
		}

		selectedEducator.setMinSize(150, 50);

		return grid;
	}

	private GridPane loginSetup() {
		GridPaneCenter grid = new GridPaneCenter();

		ButtonWithStyle btnLogin = new ButtonWithStyle("Login", grid, 0, 1);
		btnLogin.setOnAction(e -> {
			Educator educator = (Educator) selectedEducator.getValue();
			LoggedInST.setUser(educator);
			new MainMenu(primaryStage).mainMenuUI();
		});

		return grid;
	}

	//////////////////////////////
	// Label Title
	//////////////////////////////

	private Label title() {
		Label label = new Label("Welcome to my system, human.");
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
