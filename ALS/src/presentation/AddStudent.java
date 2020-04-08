package presentation;

import java.util.ArrayList;
import java.util.Arrays;

import entities.Course;
import entities.Student;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.DB_Controller;

public class AddStudent {
	private Stage primaryStage;
	private VBoxWithStyle vbox;
	private DB_Controller controller = new DB_Controller();
	private TextFieldWithStyle tfFirstName;
	private TextFieldWithStyle tfLastName;
	private ScrollPaneWithStyle scrollPaneActionsMade;
	private ComboBox<Integer> slcSemesterCreate;
	private ComboBox<Integer> slcSemesterDelete;
	private ArrayList<Student> studentListBySemesterNo;

	private ComboBox<Student> slcStudent;
	private ArrayList<Integer> semesterNo = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));

	public AddStudent(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void editStudentUI() {
//		slcSemesterDelete = new ComboBox<String>(FXCollections.observableArrayList(semesterNo));
//		
		VBox createStudentVBox = new VBox(createTitle(), firstName(), lastName(), selectSemesterNoCreate(), studentCreateButton());
		createStudentVBox.setAlignment(Pos.TOP_CENTER);
		VBox deleteStudentVBox = new VBox(deleteTitle(), selectSemesterNoDelete(), /*selectStudent(),*/ studentDeleteButton());
		deleteStudentVBox.setAlignment(Pos.TOP_CENTER);
		VBox scrollPaneHistoryVBox = new VBox(/* scrollpane */);

		HBox studentBoxesAndScrollPane = new HBox(createStudentVBox, deleteStudentVBox, scrollPaneHistoryVBox);
		studentBoxesAndScrollPane.setAlignment(Pos.CENTER);

		vbox = new VBoxWithStyle(title(), studentBoxesAndScrollPane);
		vbox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vbox, 1800, 980);
		sceneSetup(scene);
	}

	private GridPane firstName() {
		GridPaneCenter grid = new GridPaneCenter();

		tfFirstName = new TextFieldWithStyle("First Name", grid, 0, 0);

		return grid;
	}

	private GridPane lastName() {
		GridPaneCenter grid = new GridPaneCenter();

		tfLastName = new TextFieldWithStyle("Last Name", grid, 0, 0);

		return grid;
	}

	private ComboBox<Integer> selectSemesterNoCreate() {
		slcSemesterCreate = new ComboBox<Integer>(FXCollections.observableArrayList(semesterNo));

		slcSemesterCreate.setMinSize(150, 50);

		return slcSemesterCreate;
	}

	private ComboBox<Integer> selectSemesterNoDelete() {
		slcSemesterDelete = new ComboBox<Integer>(FXCollections.observableArrayList(semesterNo));

//		{
//			studentListBySemesterNo = controller.getStudentsBySemesterNo(slcSemesterDelete.getValue());
//		}

		slcSemesterDelete.setMinSize(150, 50);

		return slcSemesterDelete;
	}

	private ComboBox<Student> selectStudent() {
		// slcStudent = new
		// ComboBox<String>(FXCollections.observableArrayList(studentListBySemesterNo));

		slcStudent.setMinSize(150, 50);

		return slcStudent;
	}
	
	private GridPane scrollPaneHistory() {
		GridPaneCenter grid = new GridPaneCenter();
		
		scrollPaneActionsMade = new ScrollPaneWithStyle(grid, 0, 0);
		//sp.setPrefWidth(550);
		
		return grid;
	}
	
	private void setScrollPaneContet(String actionMade) {
		
		scrollPaneActionsMade.setContent(new Label(actionMade));
	}

	private GridPane studentCreateButton() {
		GridPaneCenter grid = new GridPaneCenter();

		ButtonWithStyle createStudentButton = new ButtonWithStyle("Create", grid, 0, 0);
		createStudentButton.setOnAction(e -> {
			Student newStudent = new Student(tfFirstName.getText(), tfLastName.getText(), slcSemesterCreate.getValue());

			controller.createStudent(newStudent);
			setScrollPaneContet(newStudent + " has been added.");

			System.out.println("create knap trykket");
			// bekræftelse
			// new MainMenu(primaryStage).mainMenuUI();
		});

		return grid;
	}

	private GridPane studentDeleteButton() {
		GridPaneCenter grid = new GridPaneCenter();

		ButtonWithStyle deleteStudentButton = new ButtonWithStyle("Delete", grid, 0, 0);
		deleteStudentButton.setOnAction(e -> {
			Student selectedStudent = slcStudent.getValue();

			controller.deleteStudent(selectedStudent);

			System.out.println("delete knap trykket");
		});

		return grid;
	}
	
	//////////////////////////////
	// Label Titles
	//////////////////////////////

	private Label title() {
		Label label = new Label("Edit Students");
		label.setFont(Font.font("Calibri", FontWeight.BOLD, 60));
		return label;
	}
	
	private Label createTitle() {
		Label label = new Label("Create Students");
		label.setFont(Font.font("Calibri", FontWeight.BOLD, 60));
		return label;
	}
	
	private Label deleteTitle() {
		Label label = new Label("Delete Students");
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
