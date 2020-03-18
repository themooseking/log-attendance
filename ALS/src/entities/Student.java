package entities;

public class Student {
	
	private int studentId;
	private int semesterNo;
	private String firstName;
	private String lastName;
	
	public Student(int studentId, int semesterNo, String firstName, String lastName) {
		this.studentId = studentId;
		this.semesterNo = semesterNo;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
}
