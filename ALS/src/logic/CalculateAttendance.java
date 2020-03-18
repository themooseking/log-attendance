package logic;

import java.sql.Date;
import java.time.LocalDate;

public class CalculateAttendance {
	private Date startDate = new Date(2020-03-01);
	private Date endDate = new Date(2020-03-18);
	private DB_Controller controller;
//	private ArrayList<Absence> absenceList = controller.getAbsenceList();
//	private ArrayList<Student> studentList = controller.getStudentList();
//	private ArrayList<Timetable> timetableList = controller.getTimetableList();
	
	public void calcAttendancePerc() {
		for (Date i = startDate; i.after(endDate); nextDate(i)) {
			System.out.println(nextDate(i));
		}
		
	}
	
	private Date nextDate(Date date) {
		LocalDate tempDate = date.toLocalDate();
		
		tempDate.plusDays(1);
		
		date = Date.valueOf(tempDate);
		
		return date;
	}
}
