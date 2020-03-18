package entities;

import java.sql.Date;

public class Absence {

	private Date date;
	private Student student;
	private Course course;
	
	public Absence(Date date, Student student, Course course) {
		this.date = date;
		this.student = student;
		this.course = course;
	}
}
