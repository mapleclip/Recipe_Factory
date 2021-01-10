package recproboard.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import qnaboard.model.dao.QnaBoardDAO;
import recproboard.model.vo.RecproBoard;
import static common.JDBCTemplate.close;
public class RecproBoardDAO {
	private Properties prop = new Properties();

	public RecproBoardDAO() {
		String fileName = QnaBoardDAO.class.getResource("/sql/recproboard/recproboard-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<RecproBoard> selectList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<RecproBoard> list = new ArrayList<RecproBoard>();
		System.out.println("연산처리가 되나");
		String query = prop.getProperty("selectList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				RecproBoard rb = new RecproBoard(rset.getInt("recpro_no"),
													rset.getString("recpro_title"),
													rset.getString("recpro_content"),
													rset.getString("recpro_writer"),
													rset.getDate("recpro_date"),
													rset.getString("status"));
				
				list.add(rb);
													
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
			
		}
		
		System.out.println(list);
		return list;
	}

	public RecproBoard selectRboard(Connection conn, int rpb) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		RecproBoard rb = null;
		
		String query = prop.getProperty("selectRboard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rpb);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				rb = new RecproBoard(rset.getInt("recpro_no"),
										rset.getString("recpro_title"),
										rset.getString("recpro_content"),
										rset.getString("recpro_writer"),
										rset.getDate("recpro_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return rb;
	}

	public int updateRboard(Connection conn, RecproBoard rb) {
		PreparedStatement pstmt =null;
		int result = 0;
		
		String query = prop.getProperty("updateRboard");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, rb.getRecpro_title());
			pstmt.setString(2, rb.getRecpro_content());
			pstmt.setInt(3, rb.getRecpro_no());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteRboard(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteRboard");
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

	public int insertRecPro(Connection conn, RecproBoard rb) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertRecPro");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rb.getRecpro_title());
			pstmt.setString(2, rb.getRecpro_content());
			pstmt.setString(3, rb.getRecpro_writer());
			
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
