package logic;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import entities.Absence;
import entities.Course;
import entities.Student;

public class CalculateAttendance {
	//rename/refactor class name til StudentAttendance
	
	private LocalDate startDate = LocalDate.parse("2020-03-01");
	private LocalDate endDate = LocalDate.parse("2020-03-18");
	private long daySpan = ChronoUnit.DAYS.between(startDate, endDate);
	private DB_Controller controller = new DB_Controller();
	private ArrayList<Absence> absenceList = controller.getAbsenceList();
	private ArrayList<Student> studentList = controller.getStudentList();
	private int[] arr = new int[(int) daySpan];

	public int[] calcAttendancePerc() {
		int index = 0;
		for (LocalDate i = startDate; i.isBefore(endDate); i = i.plusDays(1)) {
			for (int j = 0; j < absenceList.size(); j++) {
				if (i.equals(absenceList.get(j).getDate())) {
					arr[index]++;
				}
				index++;
			}
		}
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i] / studentList.size() * 100;
		}
		return arr;
	}

	public float calcAttendancePercDay(LocalDate date) {
		int absent = 0;

		for (int j = 0; j < absenceList.size(); j++) {
			if (date.equals(absenceList.get(j).getDate())) {
				absent++;
			}
		}
		// System.out.println(((float) absent / studentList.size() * 100));
		return (float) absent / studentList.size() * 100;
	}

	public float studentAbsentInCourse(LocalDate date, Course course, Student student) {
		ArrayList<Absence> studentAbsenceCourseList = controller.getAbsenceByCourse(course);

		int absent = 100;
		int notAbsent = 0;

		for (int i = 0; i < studentAbsenceCourseList.size(); i++) {
			if (studentAbsenceCourseList.get(i).getStudent() == student) {
				for (int j = 0; j < studentAbsenceCourseList.size(); j++) {
					if (date.equals(studentAbsenceCourseList.get(j).getDate())) {
						return absent;
					}
				}
			}
		}

		return notAbsent;
	}
	
	public float studentAttendanceInCourseForDiagram(LocalDate date, Course course, Student student) {
		return 100 - studentAbsentInCourse(date, course, student);
	}

	public float studentAbsenceInCourse(LocalDate startD, LocalDate endD, Course course, Student student) {
		ArrayList<Absence> studentAbsenceCourseList = controller.getAbsenceByCourse(course);

		// startdate og enddate skal nok ikke være i denne klasse, men beholder det for
		// nu. Det vil sige de her skal slettes når det rettes.
		startD = startDate;
		endD = endDate;
		
		long daySpan = ChronoUnit.DAYS.between(startD, endD);

		int daysAbsent = 0;

		for (LocalDate i = startD; i.isBefore(endD); i = i.plusDays(1)) {
			for (int j = 0; j < studentAbsenceCourseList.size(); j++) {
				if (studentAbsenceCourseList.get(j).getStudent() == student) {
					for (int k = 0; k < studentAbsenceCourseList.size(); k++) {
						if (i.equals(studentAbsenceCourseList.get(k).getDate())) {
							daysAbsent++;
						}
					}
				}
			}
		}
		return (float) daysAbsent / daySpan * 100;
	}

	public float studentAttendanceInCourse(LocalDate startD, LocalDate endD, Course course, Student student) {
		return 100 - studentAbsenceInCourse(startD, endD, course, student);
	}

	public float studentAbsent(LocalDate date, Student student) {
		//Hvordan vil vi gerne håndtere fravær ifht om der er to fag eller et på en dag? Eller flere?
		ArrayList<Absence> studentAbsenceList = controller.getAbsenceList();
		
		int absentInCourses = 0;
		int absent = 0;

		for (int i = 0; i < studentAbsenceList.size(); i++) {
			if (studentAbsenceList.get(i).getStudent() == student) {
				for (int j = 0; j < studentAbsenceList.size(); j++) {
					if (date.equals(studentAbsenceList.get(j).getDate())) {
						absentInCourses++;
					}
				}
			}
		}

		if(absentInCourses == 1) {
			absent = 50;
		}
		if(absentInCourses == 2) {
			absent = 100;
		}
		return absent;
	}
	
	public float studentAttendanceForDiagram(LocalDate date, Student student) {
		return 100 - studentAbsent(date, student);
	}
	
	public float studentAbsence(LocalDate startD, LocalDate endD, Student student) {
		//tager ikke højde for flere fag på samme dag?! --- så ikke done
		
		ArrayList<Absence> studentAbsenceList = controller.getAbsenceList();

		// startdate og enddate skal nok ikke være i denne klasse, men beholder det for
		// nu. Det vil sige de her skal slettes når det rettes.
		startD = startDate;
		endD = endDate;
		
		long daySpan = ChronoUnit.DAYS.between(startD, endD);

		int daysAbsent = 0;

		for (LocalDate i = startD; i.isBefore(endD); i = i.plusDays(1)) {
			for (int j = 0; j < studentAbsenceList.size(); j++) {
				if (studentAbsenceList.get(j).getStudent() == student) {
					for (int k = 0; k < studentAbsenceList.size(); k++) {
						if (i.equals(studentAbsenceList.get(k).getDate())) {
							daysAbsent++;
						}
					}
				}
			}
		}
		return (float) daysAbsent / daySpan * 100;
	}
	
	public float studentAttendance(LocalDate startD, LocalDate endD, Student student) {
		return 100 - studentAbsence(startD, endD, student);
	}
	
	
	// de her kan slettes når startDate og endDate fjernes
	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

}
