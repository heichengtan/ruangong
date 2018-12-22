package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.RegisterBean;
import util.DBConnection;

public class RegisterDAO {
	public String registerUser(RegisterBean registerBean)
	{
		String fullName = registerBean.getFullName();
		String userAccount = registerBean.getUserAccount();
		String password = registerBean.getPassword();
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try
		{
			con = DBConnection.createConnection();
			String query = "insert into member(member_num,account,password,nickname) values (NULL,?,?,?)"; //Insert user details into the table 'USERS'
			preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
			preparedStatement.setString(1, userAccount);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, fullName);
			
			int i= preparedStatement.executeUpdate();
			con.close();
			preparedStatement.close();
			if (i!=0)  //Just to ensure data has been inserted into the database
				return "SUCCESS"; 
			}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		 
			return "Oops.. Something went wrong there..!";  // On failure, send a message from here.
		}
}
