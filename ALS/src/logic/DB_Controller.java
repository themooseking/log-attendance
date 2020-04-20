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

	public ArrayList<Absence> getAbsenceByCourse(Course course) {
		ArrayList<Student> studentList = getStudentList();

		return absenceDB.getAbsenceByCourse(studentList, course);
	}

	public ArrayList<Course> getCourseList() {
		ArrayList<Educator> educatorList = getEducatorList();

		return courseDB.getCourses(educatorList);
	}

	public ArrayList<Educator> getEducatorList() {
		return educatorDB.getAllEducators();
	}

	public ArrayList<Student> getStudentList() {
		return studentDB.getAllStudents();
	}
	
	public ArrayList<Student> getStudentsBySemesterNo(int semesterNo){
        return studentDB.getStudentsBySemester(semesterNo);
    }

	public ArrayList<Student> getStudentsByCourse(Course course) {
		return studentDB.getStudentsByCourse(course);
	}

	public ArrayList<Student> getStudentsByCourseList(ArrayList<Course> courseList) {
		ArrayList<Student> totalStudentList = new ArrayList<Student>();
		ArrayList<Course> checkedList = new ArrayList<Course>();
		int i = -1;
		
		try {
			for (Course course : courseList) {
				for (; i < checkedList.size(); i++) {
					if (checkedList.size() == 0 || checkedList.get(i).getSemesterNo() != course.getSemesterNo()) {
						ArrayList<Student> studentList = studentDB.getStudentsByCourse(course);
						totalStudentList.addAll(studentList);
						break;
					} else {
						continue;
					}
				}
				i = 0;
				checkedList.add(course);
			}
		} catch (Exception e) {
			System.out.println("Can't create studentList (DB_Controller.getStudentsByCourseList)");
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
