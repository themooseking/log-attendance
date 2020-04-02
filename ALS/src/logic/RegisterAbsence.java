package logic;

import java.time.LocalDate;
import java.util.ArrayList;

import entities.Absence;
import entities.Course;
import entities.Student;

public class RegisterAbsence {
	private ArrayList<Student> selectedStudentList;
	private Course selectedCourse;
	private LocalDate today = LocalDate.now();
	private DB_Controller controller = new DB_Controller();	
	
	public RegisterAbsence(ArrayList<Student> selectedStudentList, Course selectedCourse) {
		this.selectedStudentList = selectedStudentList;
		this.selectedCourse = selectedCourse;
	}


	private ArrayList<Absence> studentAbsenceList() {
		ArrayList<Absence> studentAbsenceList = new ArrayList<Absence>();
		
		for (int i = 0; i < selectedStudentList.size(); i++) {
			studentAbsenceList.add(new Absence(today, selectedStudentList.get(i), selectedCourse));
		}
		
		return studentAbsenceList;
	}
	
	public void logAbsence() {
		controller.createAbsence(studentAbsenceList());
	}

}
