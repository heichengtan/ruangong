package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBConnection;

public class FavoriteCountryDAO {
	public String DeleteFavoriteCountry(FavoriteCountryBean deleteCountry) {
		String user = deleteCountry.getUser();
		String ISO3166 = deleteCountry.getISO3166();
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try
		{
			con = DBConnection.createConnection();
			String query = "DELETE FROM favorite_country WHERE ISO3166=? AND member_num=?"; //Insert user details into the table 'USERS'
			preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
			preparedStatement.setString(1, ISO3166);
			preparedStatement.setString(2, user);
			
			
			int i= preparedStatement.executeUpdate();
			con.close();
			preparedStatement.close();
			
			if (i!=0)  
				return "SUCCESS"; 
			}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		 
		return "刪除失敗"; // On failure, send a message from here.
	}
	
	public String createFavoriteCountry(NewFavoriteCountryBean newFavoriteCountryBean) {
		String user = newFavoriteCountryBean.getUser();
		String ISO3166 = newFavoriteCountryBean.getISO3166();
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try
		{
			con = DBConnection.createConnection();
			String query = "INSERT INTO `favorite_country`(`ISO3166`, `member_num`) VALUES (?,?)"; //Insert user details into the table 'USERS'
			preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
			preparedStatement.setString(1, ISO3166);
			preparedStatement.setString(2, user);
			
			
			int i= preparedStatement.executeUpdate();
			con.close();
			preparedStatement.close();
			
			if (i!=0)  //Just to ensure data has been inserted into the database
				return "SUCCESS"; 
			}
		catch(SQLException e)
		{
			
		}
		 
		return "此國家已存在我的最愛列表"; // On failure, send a message from here.
	}
	
	public ArrayList<FavoriteCountryBean> getFavoriteCountry(String userID) {
		ArrayList<FavoriteCountryBean> favoriteCountry = new ArrayList<FavoriteCountryBean>();
		Connection con = null;
		

		try {
			con = DBConnection.createConnection(); // establishing connection
			String query = "SELECT favorite_country.ISO3166,country.country_name FROM favorite_country "
					+ "INNER JOIN country ON favorite_country.ISO3166=country.ISO3166 "
					+ "WHERE member_num = ?";
					
			PreparedStatement stmt = con.prepareStatement(query); 
			stmt.setString(1, userID);
			
			ResultSet rs = stmt.executeQuery();
			
			
			while (rs.next()) // Until next row is present otherwise it return false
			{
				FavoriteCountryBean favoriteCountryBean = new FavoriteCountryBean();
				favoriteCountryBean.setISO3166(rs.getString(1));
				favoriteCountryBean.setCountry(rs.getString(2));
				favoriteCountry.add(favoriteCountryBean);
			}
			
			
			rs.close();
			stmt.close();
			con.close();
			
			return favoriteCountry;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
/*SELECT favorite_country.ISO3166,country.country_name FROM `favorite_country` INNER JOIN country ON favorite_country.ISO3166=country.ISO3166 WHERE member_num = "1"*/