package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.DBConnection;

public class LoginDAO {
	public String authenticateUser(LoginBean loginBean) {

		String userName = loginBean.getAccount(); // Keeping user entered values in temporary variables.
		String password = loginBean.getPassword();

		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;

		String userNameDB = "";
		String passwordDB = "";

		try {
			con = DBConnection.createConnection(); // establishing connection
			statement = con.createStatement(); // Statement is used to write queries. Read more about it.
			resultSet = statement.executeQuery("select account,password,member_num from member"); // Here table name is users and
																						// userName,password are
																						// columns. fetching all the
																						// records and storing in a
																						// resultSet.

			while (resultSet.next()) // Until next row is present otherwise it return false
			{
				userNameDB = resultSet.getString(1);
				passwordDB = resultSet.getString(2);

				if (userName.equals(userNameDB) && password.equals(passwordDB)) {
					return resultSet.getString(3); //// If the user entered values are already present in database, which means
										//// user has already registered so I will return SUCCESS message.
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // Just returning appropriate message otherwise
	}
}
