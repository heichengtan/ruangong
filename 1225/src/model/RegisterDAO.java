package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		Statement statement = null;
		ResultSet resultSet = null;
		
		try
		{
			boolean exist = false;
			con = DBConnection.createConnection();
			String query = "insert into member(member_num,account,password,nickname) values (NULL,?,?,?)"; //Insert user details into the table 'USERS'
			preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
			preparedStatement.setString(1, userAccount);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, fullName);
			
			String selectQuery = "SELECT account FROM member";
			statement  = con.createStatement();
			resultSet =	statement.executeQuery(selectQuery);
			while(resultSet.next()) {
				if(resultSet.getString(0).equals(userAccount)) {
					exist = true;
					break;
				}
					
			}
			if(exist) {
				return "jdifjdifj";
			}
			else {
				int i = preparedStatement.executeUpdate();
				if (i!=0)  //Just to ensure data has been inserted into the database
					return "SUCCESS"; 
				}
			
			
			con.close();
			preparedStatement.close();
			statement.close();
			resultSet.close();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		 
			return "Oops.. Something went wrong there..!";  // On failure, send a message from here.
		}
}
