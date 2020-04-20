package logic;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import entities.*;

public class CalculateAttendance {
	private DB_Controller controller = new DB_Controller();
	private LocalDate startDate;
	private LocalDate endDate;
	private long daySpan;
	private ArrayList<Course> courseList;
	private ArrayList<Student> studentList;

	public CalculateAttendance(LocalDate sDate, LocalDate eDate, ArrayList<Course> cList, ArrayList<Student> sList) {
		this.startDate = sDate;
		this.endDate = eDate;
		this.daySpan = ChronoUnit.DAYS.between(startDate, endDate);
		this.courseList = cList;
		this.studentList = sList;
	}

	public float[] calculateCourseAttendance(int i) {
		float totalStudents = (float) studentList.size();
		float[] absenceArr = new float[(int) daySpan + 1];
		int index = 0;

		for (LocalDate ii = startDate; ii.isBefore(endDate.plusDays(1)); ii = ii.plusDays(1)) {
			absenceArr[index++] = 100 - ((float) absentStudents(ii, courseList.get(i)) / totalStudents * 100);
		}
		return absenceArr;
	}

	public float[] calculateCourseAbsence(int i) {
		float totalStudents = (float) studentList.size();
		float[] absenceArr = new float[(int) daySpan + 1];
		int index = 0;

		for (LocalDate ii = startDate; ii.isBefore(endDate.plusDays(1)); ii = ii.plusDays(1)) {
			absenceArr[index++] = (float) absentStudents(ii, courseList.get(i)) / totalStudents * 100;
		}
		return absenceArr;
	}

	public float[] averageAbsence() {
		float totalStudents = (float) studentList.size();
		float totalCourses = (float) courseList.size();
		float[] avgArr = new float[(int) daySpan + 1];
		int index = 0;

		for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1), index++) {
			for (int i = 0; i < totalCourses; i++) {
				avgArr[index] += (float) absentStudents(date, courseList.get(i)) / totalStudents * 100
						/ totalCourses;
			}
		}

		return avgArr;
	}
	
	public float[] averageAttendance() {
		float totalStudents = (float) studentList.size();
		float totalCourses = (float) courseList.size();
		float[] avgArr = new float[(int) daySpan + 1];
		int index = 0;

		for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1), index++) {
			avgArr[index] = 100;
			
			for (int i = 0; i < totalCourses; i++) {
				avgArr[index] -= (float) absentStudents(date, courseList.get(i)) / totalStudents * 100
						/ totalCourses;
			}
		}

		return avgArr;
	}

	private int absentStudents(LocalDate date, Course course) {
		ArrayList<Absence> studentAbsenceCourseList = controller.getAbsenceByCourse(course);

		int studentsAbsent = 0;

		for (int i = 0; i < studentAbsenceCourseList.size(); i++) {
			for (int j = 0; j < studentList.size(); j++) {
				if (date.equals(studentAbsenceCourseList.get(i).getDate()) && studentList.get(j)
						.getStudentId() == studentAbsenceCourseList.get(i).getStudent().getStudentId()) {
					studentsAbsent++;
				}
			}
		}
		return studentsAbsent;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public ArrayList<Course> getCourseList() {
		return courseList;
	}

}
