package edu.kh.jdbc.homework.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import edu.kh.jdbc.homework.model.dto.Student;
import edu.kh.jdbc.homework.model.service.StudentService;


public class StudentView {
	private StudentService service = new StudentService();
	private Scanner sc = new Scanner(System.in);
	
	public void displayMenu() {
		
		int input = 0;
		
		while (true) {
			try {
				System.out.println("\n========= 학생 관리 프로그램 =========");
				System.out.println("1. 전체 학생 조회");
				System.out.println("2. 전공별 학생 조회 (특정 전공 학생만 필터링 조회) ");
				System.out.println("3. 학번으로 학생 조회");
				System.out.println("4. 학생 정보 추가");
				System.out.println("5. 학생 정보 수정");
				System.out.println("6. 학생 정보 삭제 (학번을 기준)");
				System.out.println("0. 프로그램 종료");
				System.out.print("메뉴 선택: ");
				input = sc.nextInt();
				sc.nextLine();
				System.out.println();
				
				switch(input) {
					case 1:
						selectAllStudents();
						break;
					case 2:
						selectStudentsByMajor();
						break;
					case 3:
						selectStudentByNum();
						break;
					case 4:
						addStudent();
						break;
					case 5:
						updateStudent();
						break;
					case 6:
						deleteStudent();
						break;
					case 0:
						System.out.println("========= 프로그램을 종료합니다.========= ");
						return;
					default:
						System.out.println("메뉴에 존재하는 번호만 입력해주세요.");
						break;
				}
			} catch (InputMismatchException e) {
				System.out.println("입력 형식이 올바르지 않습니다. 다시 입력하세요.");
				sc.nextLine();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void selectAllStudents() throws Exception {
		System.out.println("========= 전체 학생 조회 ========");
		
		ArrayList<Student> student_list = service.selectAllStudents();
		
		if (student_list.isEmpty()) {
			System.out.println("조회된 학생 정보가 없습니다.");
			return;
		}
		System.out.println("학번 | 이름 | 나이 | 전공 | 생성일 ");
		for(int i=0; i<student_list.size(); i++) {
			System.out.println("  "+student_list.get(i).getStudent_num() + "  | " + student_list.get(i).getStudent_name() + " | "
					+ student_list.get(i).getStudent_age() + " | " + student_list.get(i).getStudent_major() + " | "
					+ student_list.get(i).getEnt_date());
		}
	}

	private void selectStudentsByMajor() throws Exception {
		System.out.println("\n========= 특정 전공 학생만 필터링 조회 ========");
		System.out.print("조회할 학생의 전공 입력: ");
		String major = sc.nextLine();
		ArrayList<Student> student_list  = service.selectStudentsByMajor(major);
		if (student_list.isEmpty()) {
			System.out.println("조회된 학생 정보가 없습니다.");
			return;
		}
		System.out.println("======= "+ major + " 전공의 학생 정보 리스트 =======");
		System.out.println("학번 | 이름 | 나이 | 전공 | 생성일 ");
		for(int i=0; i<student_list.size(); i++) {
			System.out.println("  "+student_list.get(i).getStudent_num() + "  | " + student_list.get(i).getStudent_name() + " | "
					+ student_list.get(i).getStudent_age() + " | " + student_list.get(i).getStudent_major() + " | "
					+ student_list.get(i).getEnt_date());
		}
	}

	private void selectStudentByNum() throws Exception {
		System.out.println("\n========= 학번으로 학생 조회 ========");
		System.out.print("조회할 학생의 학번 입력: ");
		int studentNum = sc.nextInt(); sc.nextLine();
		
		Student student = service.selectStudentByNum(studentNum);
		if (student == null) {
			System.out.println("조회된 학생 정보가 없습니다.");
			return;
		}
		
		System.out.println("======= "+ studentNum + " 학번의 학생 정보 리스트 =======");
		System.out.println("학번 | 이름 | 나이 | 전공 | 생성일 ");
		System.out.println("  " + student.getStudent_num() + "  | " + student.getStudent_name() + " | "
				+ student.getStudent_age() + " | " + student.getStudent_major() + " | " + student.getEnt_date());
	}
	

	private void addStudent() throws Exception {
		System.out.println("\n========= 학생 정보 추가 ========");
		System.out.print("추가할 학생의 이름 입력: ");
		String name = sc.nextLine();
		System.out.print("추가할 학생의 나이 입력: ");
        int age = sc.nextInt(); sc.nextLine(); 
        System.out.print("추가할 학생의 전공 입력: ");
        String major = sc.nextLine();
        
        Student student = new Student();
        student.setStudent_name(name);
        student.setStudent_age(age);
        student.setStudent_major(major);
        
    	int result = service.addStudent(student);
    	if(result > 0) {
    		System.out.println("학생 정보가 성공적으로 추가되었습니다.");
    	} else {
    		System.out.println("학생 정보 추가에 실패했습니다.");
    	}
	}

	private void updateStudent() throws Exception {
		System.out.println("\n========= 학생 정보 수정 ========");
		System.out.print("수정할 학생의 학번 입력: ");
		int studentNum = sc.nextInt(); sc.nextLine();
		
		Student student = service.selectStudentByNum(studentNum);
		if (student == null) {
			System.out.println("수정할 학생 정보가 없습니다.");
			return;
		}
		else {
			System.out.print("수정할 학생의 이름 입력 : ");
			String name = sc.nextLine();
			System.out.print("수정할 학생의 나이 입력 : ");
			int age = sc.nextInt(); sc.nextLine();
			System.out.print("수정할 학생의 전공 입력 : ");
			String major = sc.nextLine();
			
			student.setStudent_name(name);
			student.setStudent_age(age);
			student.setStudent_major(major);
			
			int result = service.updateStudent(student);
			if (result > 0) {
				System.out.println("학생 정보가 성공적으로 수정되었습니다.");
			} else {
				System.out.println("학생 정보 수정에 실패했습니다.");
			}
		}
	}

	private void deleteStudent() throws Exception {
		System.out.println("\n========= 학생 정보 삭제 ========");
		System.out.print("삭제할 학생의 학번 입력: ");
		int studentNum = sc.nextInt(); sc.nextLine();
		
		Student student = service.selectStudentByNum(studentNum);
		
		if (student == null) {
			System.out.println("삭제할 학생 정보가 없습니다.");
			return;
		}
		else {
			int result = service.deleteStudent(studentNum);
			if (result > 0) {
				System.out.println("학생 정보가 성공적으로 삭제되었습니다.");
			} else {
				System.out.println("학생 정보 삭제에 실패했습니다.");
			}
		}
	}
}
