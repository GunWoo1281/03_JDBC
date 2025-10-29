package edu.kh.jdbc.common;

import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Scanner;

public class CreateXMLFile {

	public static void main(String[] args) {
		
		Scanner sc = null;
		FileOutputStream fos = null;
		
		try {
			sc = new Scanner(System.in);
			System.out.print("파일명 입력 : ");
			String fileName = sc.nextLine();
			
			Properties prop = new Properties();
			fos = new FileOutputStream(fileName + ".xml");
			
			prop.storeToXML(fos, ".xml 파일!!!");
			
			System.out.println(fileName + ".xml 파일 생성 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(fos != null) fos.close();
				if(sc != null) sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
