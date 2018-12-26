package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jdk.internal.jshell.tool.resources.l10n;
import util.DBConnection;

public class RegionInformationDAO {
	public ArrayList<RegionInformationBean> getRegionInformation(String ISO3166) {
		Connection con = null;
		
		ArrayList<RegionInformationBean> regionInformationBeans = new ArrayList<RegionInformationBean>();
		
		
		try
		{
			con = DBConnection.createConnection();
			String query = "SELECT country.country_name,disease.disease_name,epidemic.ISO3166,epidemic.effective,epidemic.expires,epidemic.description,epidemic.alert,epidemic.suggestion FROM epidemic "
					+"INNER JOIN country ON epidemic.ISO3166 = country.ISO3166 "
					+"INNER JOIN disease ON epidemic.disease_num=disease.disease_num "
					+"WHERE epidemic.ISO3166 = ?"; 
			
			PreparedStatement stmt = con.prepareStatement(query); 
			stmt.setString(1, ISO3166);
			ResultSet rs = stmt.executeQuery();
			
		
			while (rs.next()) {
				RegionInformationBean regionInformationBean = new RegionInformationBean();
				regionInformationBean.setCountry_name(rs.getString(1));
				regionInformationBean.setDisease_name(rs.getString(2));
				regionInformationBean.setISO3166(rs.getString(3));
				regionInformationBean.setEffective(rs.getDate(4));
				regionInformationBean.setExpires(rs.getDate(5));
				regionInformationBean.setDescription(rs.getString(6));
				regionInformationBean.setAlert(rs.getString(7));
				regionInformationBean.setSuggestion(rs.getString(8));
				regionInformationBeans.add(regionInformationBean);
			}
			rs.close();
			stmt.close();
			con.close();
			

			return regionInformationBeans;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		 
			return null;  // On failure, send a message from here.
	}
}
	/*SELECT country.country_name,disease.disease_name,epidemic.ISO3166,epidemic.effective,epidemic.expires,epidemic.description,epidemic.alert,epidemic.suggestion
	FROM epidemic
	INNER JOIN country ON epidemic.ISO3166 = country.ISO3166
	INNER JOIN disease ON epidemic.disease_num=disease.disease_num
	WHERE epidemic.ISO3166="US" */

