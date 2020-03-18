package entities;

public class Timetable {
	private String plannedDay;
	private Course course;
	
	public Timetable(String plannedDay, Course course){
		this.plannedDay = plannedDay;
		this.course = course;
	}
}
