package entities;

import java.time.LocalDate;

public class Absence {
	private LocalDate date;
	private Student student;
	private Course course;
	
	public Absence(LocalDate date, Student student, Course course) {
		this.date = date;
		this.student = student;
		this.course = course;
	}
}
