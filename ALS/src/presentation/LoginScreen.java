package presentation;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoginScreen {

	private Stage primaryStage;
	private VBox vbox;

	public LoginScreen(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void loginUI() {
		vbox = new VBox(title(), loginSetup());
		vbox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vbox, 1800, 980);
		sceneSetup(scene);
	}
	
	private GridPane loginSetup() {
		GridPaneCenter grid = new GridPaneCenter();
		
		TextFieldWithStyle tfUsername = new TextFieldWithStyle("Username", grid, 0, 0);
		
		ButtonWithStyle btnLogin = new ButtonWithStyle("Login", grid, 0, 1);
		btnLogin.setOnAction(e -> {
			
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
		primaryStage.setTitle("Exchange Rate");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}