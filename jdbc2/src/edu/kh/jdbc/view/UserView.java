package edu.kh.jdbc.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.model.dto.User;
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

	private void multiInsertUser() throws Exception {
		// TODO Auto-generated method stub
System.out.println("\n===8. 여러 User 등록===\n");
		
		System.out.print("등록할 User 수 : ");
		int input = sc.nextInt();
		sc.nextLine(); // 버퍼 개행문자 제거
		
		// 입력받은 회원 정보를 저장할 List 객체 생성
		List<User> userList = new ArrayList<User>();
		
		for(int i = 0; i < input; i++) {
			
			String userId = null; // 입력된 아이디를 저장할 변수
			
			while(true) {
				
				System.out.print((i+1) + "번째 ID : ");
				userId = sc.next();
				
				// 입력받은 userId가 중복인지 검사하는
				// 서비스(SELECT) 호출 후 결과(int, 중복 == 1, 아니면 == 0) 반환 받기
				int count = service.idCheck(userId);
				
				if(count == 0) { // 중복이 아닌 경우 
					System.out.println("사용 가능한 아이디입니다");
					break;
				}
				
				System.out.println("이미 사용중인 아이디 입니다. 다시 입력하세요.");
			
			}
			
			// pw, name 입력 받기
			System.out.print((i+1) + "번째 PW : ");
			String userPw = sc.next();
			
			System.out.print((i+1) + "번째 Name : ");
			String userName = sc.next();
			
			// 입력받은 값 3개를 한번에 묶어서 전달할 수 있도록
			// User DTO 객체를 생성한 후 필드에 값 세팅 
			User user = new User();
			
			user.setUserId(userId);
			user.setUserPw(userPw);
			user.setUserName(userName);
			
			// userList에 user 추가 
			userList.add(user);
			
		} // for문 종료
		
		// 입력받은 모든 사용자를 insert 하는 서비스 호출
		// -> 결과로 삽입된 행의 개수 반환
		int result = service.multiInsertUser(userList);
		
		if(result == userList.size()) {
			System.out.println("전체 삽입 성공!!!");
			
		} else {
			System.out.println("삽입 실패");
			
		}
	}

	private void insertUser2() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("\n=====7. User 등록(아이디 중복 검사) =====\n");
		String userId = null;
		while (true) {
				System.out.print("ID : ");
				userId = sc.next();
				int idCheck = service.idCheck(userId);
				if (idCheck>0) {
					System.out.println("\n이미 존재하는 아이디입니다. 다시 입력해주세요.\n");
					idCheck = 0;
				} else {
					System.out.println("\n사용 가능한 아이디입니다.\n");
					break;
				}
			}
			System.out.print("PW : ");
			String userPw = sc.next();

			System.out.print("NAME : ");
			String userName = sc.next();

			User user = new User();

			user.setUserId(userId);
			user.setUserPw(userPw);
			user.setUserName(userName);

			int result = service.insertUser(user);

			if (result > 0) {
				System.out.println(user.getUserId() + " 사용자가 등록되었습니다.");
			} else {
				System.out.println("사용자 등록 실패");
			}
	}

	private void updateName() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("\n=====6. User 이름 수정 =====\n");
		System.out.print("ID 입력 : ");
		String userId = sc.next();
		System.out.print("PW 입력 : ");
		String userPw = sc.next();
		System.out.print("수정할 이름 입력 : ");
		String userName = sc.next();
		
        User user = new User();
        user.setUserId(userId);
        user.setUserPw(userPw);
        user.setUserName(userName);
        boolean result = service.updateName(user);
        if (!result) {
			System.out.println("\n아이디 또는 비밀번호가 일치하는 User가 존재하지 않음\n");
			return;
		} else {
			System.out.println("이름 수정 성공");
        }
	}

	private void deleteUser() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("\n=====5. User No 으로 삭제 =====\n");
		System.out.print("User No 입력 : ");
		int userNo = sc.nextInt();
		sc.nextLine();
		boolean result = service.deleteUser(userNo);
		  
		if (!result) {
			System.out.println("\n사용자 번호가 일치하는 User가 존재하지 않음\n");
			return;
		}
		else {
			System.out.println("삭제 성공");
		}
	}

	private void selectUser() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("\n=====4. User No 검색 조회 =====\n");
		System.out.print("User No 입력 : ");
		int userNo = sc.nextInt();
		sc.nextLine();
		
		User user = service.selectUser(userNo);
		
		if (user == null) {
			System.out.println("\n검색 결과 없음\n");
			return;
		}
		System.out.println(user.getUserNo() + " / " + user.getUserId() + " / " + user.getUserPw() + " / "
				+ user.getUserName() + " / " + user.getUserEnrollDate());
	}

	private void selectName() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("\n=====3. User 이름 검색 조회 =====\n");
		System.out.print("이름 입력 : ");
		String userName = sc.next();
		
		ArrayList<User> userList = service.selectName(userName);
		
		if (userList.isEmpty()) {
			System.out.println("\n검색 결과 없음\n");
			return;
		}
		for (int i=0; i<userList.size(); i++) {
			System.out.println(userList.get(i).getUserNo() + " / " + userList.get(i).getUserId() + " / "
					+ userList.get(i).getUserPw() + " / " + userList.get(i).getUserName() + " / " + userList.get(i).getUserEnrollDate());
		}
	}

	private void selectAll() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("\n=====2. User 전체 조회 =====\n");

		ArrayList<User> userList = service.selectAll();
		
		if (userList.isEmpty()) {
			System.out.println("\n******조회 결과가 없습니다.*****\n");
			return;
		}
		for (int i=0; i<userList.size(); i++) {
			System.out.println(userList.get(i).getUserNo() + " / " + userList.get(i).getUserId() + " / "
					+ userList.get(i).getUserPw() + " / " + userList.get(i).getUserName() + " / " + userList.get(i).getUserEnrollDate());
		}
	}

	private void insertUser() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("\n=====1. User 등록 =====\n");
		
		System.out.println("ID : ");
		String userId = sc.next();
		
		System.out.println("PW : ");
		String userPw = sc.next();
		
		System.out.println("NAME : ");
		String userName = sc.next();
		
		User user = new User();
		
		user.setUserId(userId);
		user.setUserPw(userPw);
		user.setUserName(userName);
		
		int result = service.insertUser(user);
		
		if (result > 0) {
			System.out.println(user.getUserId() + " 사용자가 등록되었습니다.");
		}
		else {
			System.out.println("사용자 등록 실패");
		}
    } 
}
