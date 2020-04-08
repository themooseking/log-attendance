package presentation;

import entities.Student;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.DB_Controller;

public class AddCourse {
	
	private Stage primaryStage;
	private VBoxWithStyle vbox;
	private DB_Controller controller = new DB_Controller();
	private TextField tfFirstName;
	private TextField tfLastName;
	
	private ComboBox<String> slcSemester;
	private ComboBox<String> selectCourse;

	public AddCourse(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void createStudentUI() {
		vbox = new VBoxWithStyle(title(), firstName(), LastName(), selectSemesterNo(), studentInfoButtons());
		vbox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vbox, 1800, 980);
		sceneSetup(scene);
	}

	private TextField firstName() {
		TextField tfFirstName = new TextField();

		tfFirstName.setPrefSize(50, 50);
		// tfFirstName.setMinSize(50, 50);

		return tfFirstName;
	}

	private TextField LastName() {
		TextField tfLastName = new TextField();

		tfLastName.setMinSize(50, 50);

		return tfLastName;
	}

	private ComboBox<String> selectSemesterNo() {
		String semesterNo[] = { "1", "2", "3", "4", "5" };
		
		slcSemester = new ComboBox<String>(FXCollections.observableArrayList(semesterNo));

		slcSemester.setMinSize(150, 50);

		return slcSemester;
	}

//	private ComboBox<String> selectCourse() {
//		ArrayList<Course> courseList = controller.getCourseList();
//		ArrayList<String> courseNames = new ArrayList<String>();
//		
//		for (int i = 0; i < courseList.size(); i++) {
//			courseNames.add(courseList.get(i).getCourseName());
//		}
//		
//		selectCourse = new ComboBox<String>(FXCollections.observableArrayList(courseNames));
//
//		selectCourse.setMinSize(150, 50);
//
//		return selectCourse;
//	}

	private GridPane studentInfoButtons() {
		GridPaneCenter grid = new GridPaneCenter();

		ButtonWithStyle createStudentButton = new ButtonWithStyle("Create", grid, 0, 1);
		createStudentButton.setOnAction(e -> {
			controller.createStudent(new Student(tfFirstName.getText(), tfLastName.getText(), Integer.parseInt((String) slcSemester.getValue())));
			//System.out.println(Integer.parseInt((String) slcSemester.getValue()));
			System.out.println("create knap trykket");
			// bekræftelse
			new MainMenu(primaryStage).mainMenuUI();
		});

//		ButtonWithStyle backButton = new ButtonWithStyle("Back", grid, 1, 1);
//		createStudentButton.setOnAction(e -> {
//			//new MainMenu(primaryStage).mainMenuUI();
//		});

		return grid;
	}

	//////////////////////////////
	// Label Title
	//////////////////////////////

	private Label title() {
		Label label = new Label("New Students");
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
