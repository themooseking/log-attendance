package logic;

import java.sql.Date;

public class CalculateAttendance {
	private Date startDate;
	private Date endDate;
	private DB_Controller controller;
	private ArrayList<Absence> absenceList = controller.getAbsenceList();
	private ArrayList<Student> studentList = controller.getStudentList();
	private ArrayList<Timetable> timetableList = controller.getTimetableList();
	
	private void calcAttendancePerc() {
		for (Date i = startDate; i.before(endDate); i) {
			
		}
		
	}
}
