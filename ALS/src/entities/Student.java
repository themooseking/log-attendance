package entities;

public class Student {
	private int studentId;	
	private String firstName;
	private String lastName;
	private int semesterNo;
	
	public Student(int studentId, String firstName, String lastName, int semesterNo) {
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.semesterNo = semesterNo;
	}

	public int getStudentId() {
		return studentId;
	}
}
