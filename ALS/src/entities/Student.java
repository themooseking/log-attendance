package entities;

import java.util.Comparator;

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

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getSemesterNo() {
		return semesterNo;
	}

	public static Comparator<Student> StuNameComparator = new Comparator<Student>() {
		public int compare(Student s1, Student s2) {
			String StudentName1 = s1.getFirstName();
			String StudentName2 = s2.getFirstName();

			return StudentName1.compareTo(StudentName2);
		}
	};
}
