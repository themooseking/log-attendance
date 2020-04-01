package logic;

import java.sql.SQLException;
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

	public void createAbsence(ArrayList<Absence> absenceList) {
		for (Absence ab : absenceList) {
			absenceDB.createAbsence(ab);
		}
	}

	/***********************************
	 * READ
	 ***********************************/

	public ArrayList<Absence> getAbsenceList() {
		ArrayList<Student> studentList = getStudentList();
		ArrayList<Course> courseList = getCourseList();

		return absenceDB.getAllAbsence(studentList, courseList);
	}

	public ArrayList<Absence> getAbsenceByCourse(Course course) {
		ArrayList<Student> studentList = getStudentList();

		return absenceDB.getAbsenceByCourse(studentList, course);
	}

	public ArrayList<Course> getCourseList() {
		return courseDB.getCourses();
	}
	
	public ArrayList<Course> getCoursesByEduId(int id) {
		return courseDB.getCoursesByEducatorId(id);
	}

	public ArrayList<Student> getStudentList() {
		return studentDB.getAllStudents();
	}

	public ArrayList<Student> getStudentsByCourse(Course course) {
		return studentDB.getStudentsByCourse(course);
	}

	public ArrayList<Timetable> getTimeTableList() {
		ArrayList<Course> courseList = getCourseList();

		return timeTableDB.getTimeTable(courseList);
	}
}
