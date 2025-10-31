package edu.kh.jdbc.homework.model.dto;

public class Student {

	private int student_num;
	private String student_name;
	private int student_age;
	private String student_major;
	private String ent_date;
	
	public Student() {
		
	}

	public Student(int student_num, String student_name, int student_age, String student_major, String ent_date) {
		super();
		this.student_num = student_num;
		this.student_name = student_name;
		this.student_age = student_age;
		this.student_major = student_major;
		this.ent_date = ent_date;
	}

	public int getStudent_num() {
		return student_num;
	}

	public void setStudent_num(int student_num) {
		this.student_num = student_num;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public int getStudent_age() {
		return student_age;
	}

	public void setStudent_age(int student_age) {
		this.student_age = student_age;
	}

	public String getStudent_major() {
		return student_major;
	}

	public void setStudent_major(String student_major) {
		this.student_major = student_major;
	}

	public String getEnt_date() {
		return ent_date;
	}

	public void setEnt_date(String ent_date) {
		this.ent_date = ent_date;
	}
	
}
