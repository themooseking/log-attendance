package logic;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import entities.Absence;
import entities.Student;

public class CalculateAttendance {
	private LocalDate startDate = LocalDate.parse("2020-03-01");
	private LocalDate endDate = LocalDate.parse("2020-03-18");
	private long daySpan = ChronoUnit.DAYS.between(startDate, endDate);
	private DB_Controller controller;
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
	
	public int calcAttendancePercDay(LocalDate date) {
		int absent = 0;
		
		for (int j = 0; j < absenceList.size(); j++) {
			if (date.equals(absenceList.get(j).getDate())) { 
				absent++;
			}
		}
		
		return absent / studentList.size() * 100;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

}
