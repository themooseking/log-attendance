package entities;

public class Course {
	private int courseId;
	private String courseName;
	private int semesterNo;
	private int educatorId;
	
	public Course(int courseId, String courseName, int semesterNo, int educatorId) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.semesterNo = semesterNo;
		this.educatorId = educatorId;
	}

	public int getCourseId() {
		return courseId;
	}
}
