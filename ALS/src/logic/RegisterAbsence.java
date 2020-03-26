package logic;

import java.util.ArrayList;

import entities.Course;
import entities.Student;

public class RegisterAbsence {
	private ArrayList<Student> selectedStudentList;
	private Course selectedCourse;
	
	public RegisterAbsence(ArrayList<Student> selectedStudentList, Course selectedCourse) {
		this.selectedStudentList = selectedStudentList;
		this.selectedCourse = selectedCourse;
	}



}
