package logic;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CalculateAttendance {
	private LocalDate startDate = LocalDate.parse("2020-03-01");
	private LocalDate endDate = LocalDate.parse("2020-03-18");
	private long daySpan = ChronoUnit.DAYS.between(startDate, endDate);
	private DB_Controller controller;
	private ArrayList<Absence> absenceList = controller.getAbsenceList();
//	private ArrayList<Student> studentList = controller.getStudentList();
//	private ArrayList<Timetable> timetableList = controller.getTimetableList();
	private int[] arr = new int[(int) daySpan];
	
	public void calcAttendancePerc() {
		for (LocalDate i = startDate; i.isBefore(endDate); i = i.plusDays(1)) {
			for (int j = 0; j < absenceList.length; j++) {
				if (i.equals(absenceList[j])) { 
					
				}
			}
		}
	}

}
