package logic;

import java.util.ArrayList;
import data.*;
import entities.*;

public class DB_Controller {
	private DataLayer dataLayer = new DataLayer();
	private DB_Absence absenceDB = new DB_Absence(dataLayer.getConnection());
	private DB_Course courseDB = new DB_Course(dataLayer.getConnection());
	private DB_Student studentDB = new DB_Student(dataLayer.getConnection());
	private DB_TimeTable timeTableDB = new DB_TimeTable(dataLayer.getConnection());
	
	/***********************************
	 * READ
	 ***********************************/
	
	public ArrayList<Absence> getAbsenceList(){
		ArrayList<Student> studentList = getStudentList();
		ArrayList<Course> courseList = getCourseList();
		
		return absenceDB.getAbsence(studentList, courseList);
	}
	
	public ArrayList<Course> getCourseList(){
		return courseDB.getCourses();
	}
	
	public ArrayList<Student> getStudentList(){
		return studentDB.getStudents();
	}
	
	public ArrayList<Timetable> getTimeTableList(){
		ArrayList<Course> courseList = getCourseList();
		
		return timeTableDB.getTimeTable(courseList);
	}
}
