package faqboard.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import static common.JDBCTemplate.close;
import faqboard.model.vo.Faqboard;

public class FaqDAO {
	private Properties prop = new Properties();
	
	public FaqDAO() {
		String fileName = FaqDAO.class.getResource("/sql/faq/faq-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Faqboard> selectList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Faqboard> list = new ArrayList<Faqboard>();
		String query = prop.getProperty("selectList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Faqboard fb = new Faqboard(rset.getInt("fboard_no"),
											rset.getString("fboard_writer"),
											rset.getString("fboard_title"),
											rset.getString("fboard_content"),
											rset.getString("status"));
				list.add(fb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return list;
	}

	public int insertFaq(Connection conn, Faqboard fb) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertFaq");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fb.getFboard_writer());
			pstmt.setString(2, fb.getFboard_title());
			pstmt.setString(3, fb.getFboard_content());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int deleteFboard(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteFboard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
}
