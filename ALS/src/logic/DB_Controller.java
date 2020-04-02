package logic;

import java.util.ArrayList;
import data.*;
import entities.*;

public class DB_Controller {
	private DataLayer dataLayer = new DataLayer();
	private DB_Absence absenceDB = new DB_Absence(dataLayer.getConnection());
	private DB_Course courseDB = new DB_Course(dataLayer.getConnection());
	private DB_Educator educatorDB = new DB_Educator(dataLayer.getConnection());
	private DB_Student studentDB = new DB_Student(dataLayer.getConnection());
	private DB_TimeTable timeTableDB = new DB_TimeTable(dataLayer.getConnection());

	/***********************************
	 * Create
	 ***********************************/

	public void createAbsence(ArrayList<Absence> absenceList) {
		for (Absence ab : absenceList) {
			absenceDB.createAbsence(ab);
		}
	}

	public void createCourse(Course course) {
		courseDB.createCourse(course);
	}

	public void createStudent(Student student) {
		studentDB.createStudent(student);
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
		ArrayList<Educator> educatorList = getEducatorList();

		return courseDB.getCourses(educatorList);
	}

	public ArrayList<Course> getCoursesByEduId(int id) {
		ArrayList<Educator> educatorList = getEducatorList();

		return courseDB.getCoursesByEducatorId(educatorList, id);
	}

	public ArrayList<Educator> getEducatorList() {
		return educatorDB.getAllEducators();
	}

	public ArrayList<Student> getStudentList() {
		return studentDB.getAllStudents();
	}

	public ArrayList<Student> getStudentsByCourse(Course course) {
		return studentDB.getStudentsByCourse(course);
	}
	
	public ArrayList<Student> getStudentsByCourseList(ArrayList<Course> courseList) {
		ArrayList<Student> totalStudentList = new ArrayList<Student>();
		
		for(Course course : courseList) {
			ArrayList<Student> studentList = studentDB.getStudentsByCourse(course);
			totalStudentList.addAll(studentList);
		}
		
		return totalStudentList;
	}

	public ArrayList<Timetable> getTimeTableList() {
		ArrayList<Course> courseList = getCourseList();

		return timeTableDB.getTimeTable(courseList);
	}
	
	/***********************************
	 * DELETE
	 ***********************************/
	
	public void deleteCourse(Course course) {
		courseDB.deleteCourse(course);
	}
	
	public void deleteStudent(Student student) {
		studentDB.deleteStudent(student);
	}
}
