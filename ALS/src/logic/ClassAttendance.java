package logic;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import entities.Absence;
import entities.Course;
import entities.Student;

public class ClassAttendance {
	private DB_Controller controller = new DB_Controller();
	private CalculateAttendance studentAttendance = new CalculateAttendance();

	public float classAbsenceInCourse(LocalDate date, Course course) {
		ArrayList<Student> classList = controller.getStudentsByCourse(course);

		int studentsAbsent = 0;
		
		for (int i = 0; i < classList.size(); i++) {
			if(studentAttendance.studentAbsentInCourse(date, course, classList.get(i)) == 100) {
				studentsAbsent++;
			}
		}
		
		return (float) studentsAbsent / classList.size() * 100;
	}

	public float classAttendance(LocalDate date, Course course) {
		return 100 - classAbsenceInCourse(date, course);
	}
}
