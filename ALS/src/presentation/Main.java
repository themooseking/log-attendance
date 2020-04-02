package presentation;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		LogAttendance la = new LogAttendance(primaryStage); 
//		la.logAttendanceUI();
//	}

//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		StatisticFilter sf = new StatisticFilter(primaryStage);
//		sf.filterUI();
//	}

//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		CourseStatistics stats = new CourseStatistics(primaryStage);
//		stats.courseStatisticsUI();
//	} 

	@Override
	public void start(Stage primaryStage) throws Exception {
		LoginScreen login = new LoginScreen(primaryStage); 
		login.loginUI();
	}
}
