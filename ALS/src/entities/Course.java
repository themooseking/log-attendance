package entities;

public class Course {
	
	private int educatorId;
	private int semesterNo;
	private String courseName;
	
	public Course(int educatorId, int semesterNo, String courseName) {
		this.educatorId = educatorId;
		this.semesterNo = semesterNo;
		this.courseName = courseName;
	}
}
