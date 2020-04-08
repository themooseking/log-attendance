package presentation;

import java.util.ArrayList;

import entities.Educator;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.DB_Controller;
import logic.LoggedInST;


public class LoginScreen {

	private Stage primaryStage;
	private VBoxWithStyle vbox;
	private DB_Controller controller = new DB_Controller();
	private ArrayList<Educator> educatorList;
	private Educator educator = new Educator(1, "Hans Iversen");

	public LoginScreen(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.educatorList = controller.getEducatorList();
	}

	public void loginUI() {
		vbox = new VBoxWithStyle(title(), slcEducator(), loginSetup());
		vbox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vbox, 1800, 980);
		sceneSetup(scene);
	}
	
	private ComboBox<String> slcEducator() {
		String educatorNameArray[] = new String[educatorList.size()];
		
		for (int i = 0; i < educatorList.size(); i++) {
			educatorNameArray[i] = educatorList.get(i).getEducatorName();
		}
		
		ComboBox<String> slcEducator = new ComboBox<String>(FXCollections.observableArrayList(educatorNameArray));
		
		slcEducator.setMinSize(150, 50);
		
		return slcEducator;
	}
	
	private GridPane loginSetup() {
		GridPaneCenter grid = new GridPaneCenter();
		
//		TextFieldWithStyle tfUsername = new TextFieldWithStyle("Username", grid, 0, 0);
		
		ButtonWithStyle btnLogin = new ButtonWithStyle("Login", grid, 0, 1);
		btnLogin.setOnAction(e -> {
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
