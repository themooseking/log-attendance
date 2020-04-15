package entities;

public class Course {
	private int courseId;
	private String courseName;
	private int semesterNo;
	private Educator educator;

	public Course(int courseId, String courseName, int semesterNo, Educator educator) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.semesterNo = semesterNo;
		this.educator = educator;
	}

	public Course(String courseName, Integer semesterNo, Educator educator) {
		this.courseName = courseName;
		this.semesterNo = semesterNo;
		this.educator = educator;
	}

	public int getCourseId() {
		return courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public int getSemesterNo() {
		return semesterNo;
	}

	public Educator getEducator() {
		return educator;
	}
	
	@Override
	public String toString() {
		return courseName + ", " + courseId;		
	}
}
