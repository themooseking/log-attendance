package logic;

import java.sql.Date;

public class CalculateAttendance {
	private Date startDate;
	private Date endDate;
	private DB_Controller controller;
	
	private void calcAttendancePerc() {
		ArrayList<Absence> absenceList = controller.getAbsenceList();
		
	}
}
