package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import util.DBConnection;

public class MainDAO {
	public ArrayList<MainBean> getCountryData() {
		
		Connection con = null;
		ArrayList<MainBean> result = new ArrayList<MainBean>();
		
		try
		{
			con = DBConnection.createConnection();
			String query = "select * from country";
			
			Statement stmt = con.createStatement(); 
			
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				MainBean mainBean = new MainBean();
				mainBean.setISO3166(rs.getString(1));
				mainBean.setCountryName(rs.getString(2));
				result.add(mainBean);
			}
			rs.close();
			stmt.close();
			con.close();
			
			return result;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		 
			return null;  // On failure, send a message from here.
		
		
	}
}
