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
	private float[] absenceArr;

	public CalculateAttendance(LocalDate sDate, LocalDate eDate, ArrayList<Course> cList, ArrayList<Student> sList) {
		this.startDate = sDate;
		this.endDate = eDate;
		this.daySpan = ChronoUnit.DAYS.between(startDate, endDate);
		this.absenceArr = new float[(int) daySpan + 1];
		this.courseList = cList;
		this.studentList = sList;
	}

	public float[] calculateCourseAttendance(int i) {
		float totalStudents = (float) studentList.size();
		int index = 0;

		for (LocalDate ii = startDate; ii.isBefore(endDate.plusDays(1)); ii = ii.plusDays(1)) {
			absenceArr[index++] = 100 - ((float) absentStudents(ii, courseList.get(i)) / totalStudents * 100);
		}
		return absenceArr;
	}

	public float[] calculateCourseAbsence(int i) {
		float totalStudents = (float) studentList.size();
		int index = 0;

		for (LocalDate ii = startDate; ii.isBefore(endDate.plusDays(1)); ii = ii.plusDays(1)) {
			absenceArr[index++] = (float) absentStudents(ii, courseList.get(i)) / totalStudents * 100;
		}
		return absenceArr;
	}

	public float[] averageAbsence() {
		float totalStudents = (float) studentList.size();
		int index = 0;

		for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
			float totalCourses = (float) courseList.size();
			absenceArr[index] = 0;
			
			for (int i = 0; i < totalCourses; i++) {
				absenceArr[index] += (float) absentStudents(date, courseList.get(i)) / totalStudents * 100
						/ totalCourses;
			}
		}

		return absenceArr;
	}

//	public float[] studentAttendance() {
//		float[] attendanceArr = new float[(int) daySpan + 1];
//
//		for (int i = 0; i < absenceArr.length; i++) {
//			attendanceArr[i] = 100 - absenceArr[i];
//		}
//		return attendanceArr;
//	}

//	public float[] calculateAbsence() {
//		float totalStudents = (float) studentList.size();
//		int index = 0;
//
//		for (LocalDate i = startDate; i.isBefore(endDate.plusDays(1)); i = i.plusDays(1)) {
//			float totalCourses = (float) totalCourseByDate(i);
//			if (totalCourses > 0) {
//				absenceArr[index++] = (float) totalAbsentByDate(i) / totalStudents * 100 / totalCourses;
//			} else {
//				absenceArr[index++] = 0;
//			}
//		}
//
//		return absenceArr;
//	}

//	private int totalAbsentByDate(LocalDate date) {
//		int absent = 0;
//		for (int i = 0; i < courseList.size(); i++) {
//			absent += absentStudents(date, courseList.get(i));
//		}
//		return absent;
//	}

//	private int totalStudents() {
//		int numberOfStudents = 0;
//		for (int i = 0; i < courseList.size(); i++) {
//			ArrayList<Student> studentCourseList = controller.getStudentsByCourse(courseList.get(i));
//			for (int j = 0; j < studentCourseList.size(); j++) {
//				numberOfStudents++;
//			}
//		}
//		return numberOfStudents / courseList.size();
//	}

//	private int totalCourseByDate(LocalDate date) {
//		int totalCourses = 0;
//		String dayOfWeek = date.getDayOfWeek().name();
//		ArrayList<Timetable> timetableList = controller.getTimeTableList();
//
//		for (int i = 0; i < timetableList.size(); i++) {
//			String timeTableDay = timetableList.get(i).getPlannedDay().toUpperCase();
//			if (dayOfWeek.equals(timeTableDay)) {
//				for (int j = 0; j < courseList.size(); j++) {
//					if (courseList.get(j).getCourseId() == timetableList.get(i).getCourse().getCourseId()) {
//						totalCourses++;
//					}
//				}
//			}
//		}
//		return totalCourses;
//	}

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
