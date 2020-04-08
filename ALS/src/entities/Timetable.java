package entities;

public class Timetable {
	private String plannedDay;
	private Course course;
	
	public Timetable(String plannedDay, Course course){
		this.plannedDay = plannedDay;
		this.course = course;
	}

	public String getPlannedDay() {
		return plannedDay;
	}

	public Course getCourse() {
		return course;
	}
}
