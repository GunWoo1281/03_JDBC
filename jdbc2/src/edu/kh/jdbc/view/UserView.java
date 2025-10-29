package edu.kh.jdbc.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.kh.jdbc.model.service.UserService;

public class UserView {
	
	private UserService service = new UserService();
	private Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		int input = 0;
		do {
			try {
				System.out.println("\n===== User 관리 프로그램 =====\n");
				System.out.println("1. User 등록(INSERT)");
				System.out.println("2. User 전체 조회(SELECT)");
				System.out.println("3. User 중 이름에 검색어가 포함된 회원 조회 (SELECT)");
				System.out.println("4. USER_NO를 입력 받아 일치하는 User 조회(SELECT)");
				System.out.println("5. USER_NO를 입력 받아 일치하는 User 삭제(DELETE)");
				System.out.println("6. ID, PW가 일치하는 회원이 있을 경우 이름 수정(UPDATE)");
				System.out.println("7. User 등록(아이디 중복 검사)");
				System.out.println("8. 여러 User 등록하기");
				System.out.println("0. 프로그램 종료");
				System.out.print("메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine(); // 버퍼에 남은 개행문자 제거
				switch (input) {
					case 1: insertUser(); break;
					case 2: selectAll(); break;
					case 3: selectName(); break;
					case 4: selectUser(); break;
					case 5: deleteUser(); break;
					case 6: updateName(); break;
					case 7: insertUser2(); break;
					case 8: multiInsertUser(); break;
					case 0: System.out.println("\n[프로그램 종료]\n"); break;
					default: System.out.println("\n[메뉴 번호만 입력하세요]\n");
				}
				System.out.println("\n-------------------------------------\n");
			} catch (InputMismatchException e) {
				e.printStackTrace();
				input = -1;
				sc.nextLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (input!= 0);
		
	}

	private void multiInsertUser() {
		// TODO Auto-generated method stub
		
	}

	private void insertUser2() {
		// TODO Auto-generated method stub
		
	}

	private void updateName() {
		// TODO Auto-generated method stub
		
	}

	private void deleteUser() {
		// TODO Auto-generated method stub
		
	}

	private void selectUser() {
		// TODO Auto-generated method stub
		
	}

	private void selectName() {
		// TODO Auto-generated method stub
		
	}

	private void selectAll() {
		// TODO Auto-generated method stub
		
	}

	private void insertUser() {
		// TODO Auto-generated method stub
		
	}
}
