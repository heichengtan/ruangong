package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBConnection;

public class CommentDAO {
	public ArrayList<CommentBean> getComment(String ISO3166) {

		ArrayList<CommentBean> commentResult = new ArrayList<CommentBean>();
		Connection con = null;
		

		try {
			con = DBConnection.createConnection(); // establishing connection
			String query = "SELECT content,comment_time,member_num,comment_seq FROM comment "
					+ "WHERE ISO3166 = ?";
			PreparedStatement stmt = con.prepareStatement(query); 
			stmt.setString(1, ISO3166);
			ResultSet rs = stmt.executeQuery();
			
			
			while (rs.next()) // Until next row is present otherwise it return false
			{
				CommentBean commentBean = new CommentBean();
				commentBean.setContent(rs.getString(1));
				commentBean.setComment_time(rs.getDate(2));
				commentBean.setMemberNum(rs.getString(3));
				commentBean.setCommentNum(rs.getString(4));
				commentResult.add(commentBean);
			}
			System.out.println(commentResult.size());
			
			rs.close();
			stmt.close();
			con.close();
			
			return commentResult;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String deleteComment(String commentNum) {

		Connection con = null;
		

		try {
			con = DBConnection.createConnection(); // establishing connection
			String query = "DELETE FROM comment WHERE comment_seq = ?";
			PreparedStatement stmt = con.prepareStatement(query); 
			stmt.setString(1, commentNum);
			int i = stmt.executeUpdate();
			
			
			stmt.close();
			con.close();
			
			if (i!=0)  
				return "SUCCESS"; 
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "刪除失敗";
	}
	
	public String updateComment(String commentSeq,String content) {

		Connection con = null;
		

		try {
			con = DBConnection.createConnection(); // establishing connection
			String query = "UPDATE comment SET content=? WHERE comment_seq=?";
			PreparedStatement stmt = con.prepareStatement(query); 
			stmt.setString(1, content);
			stmt.setString(2, commentSeq);
			int i = stmt.executeUpdate();
			
		
			stmt.close();
			con.close();
			
			if (i!=0)  
				return "SUCCESS"; 
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "刪除失敗";
	}
	
	public String insertComment(String user,String ISO3166,String content) {

		Connection con = null;
		

		try {
			con = DBConnection.createConnection(); // establishing connection
			String query = "INSERT INTO `comment`(`member_num`, `ISO3166`, `content`) VALUES (?,?,?)";
			PreparedStatement stmt = con.prepareStatement(query); 
			stmt.setString(1, user);
			stmt.setString(2, ISO3166);
			stmt.setString(3, content);
			int i = stmt.executeUpdate();
			
		
			stmt.close();
			con.close();
			
			if (i!=0)  
				return "SUCCESS"; 
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "新增失敗";
	}
}

/*SELECT comment.content,comment.comment_time,memcoun_comment.member_num FROM memcoun_comment INNER JOIN comment ON memcoun_comment.comment_num=comment.comment_num WHERE memcoun_comment.ISO3166="US" ORDER BY comment.comment_time DESCSELECT comment.content,comment.comment_time,memcoun_comment.member_num FROM memcoun_comment INNER JOIN comment ON memcoun_comment.comment_num=comment.comment_num WHERE memcoun_comment.ISO3166="US" ORDER BY comment.comment_time DESC*/
