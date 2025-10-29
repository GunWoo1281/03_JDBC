package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

public class loadXMLFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileInputStream fis = null;
		Connection conn = null;
		Properties prop = new Properties();
		
		try {
			fis = new FileInputStream("driver.xml");
			prop.loadFromXML(fis);

			String user = prop.getProperty("user");
			String password = prop.getProperty("password");

			System.out.println("user : " + user);
			System.out.println("password : " + password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
            try {
                if(fis != null) fis.close();
            } catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
}

