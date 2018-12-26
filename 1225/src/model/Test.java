package model;

import java.sql.Date;


import model.MainDAO;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		java.util.Date now = new java.util.Date();
		Date sqlDate = new Date(now.getTime());
		
		System.out.println(sqlDate);
	}

}
