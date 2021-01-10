package qnaboard.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import qnaboard.model.vo.QPageInfo;
import qnaboard.model.vo.QnaBoard;

public class QnaBoardDAO {

	private Properties prop = new Properties();
	
	public QnaBoardDAO() {
		String fileName = QnaBoardDAO.class.getResource("/sql/qnaboard/qnaboard-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<QnaBoard> selectList(Connection conn) {
		
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<QnaBoard> list = new ArrayList<QnaBoard>();
		
		String query = prop.getProperty("selectList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				QnaBoard qb = new QnaBoard(rset.getInt("qboard_no"),
											rset.getString("qboard_title"),
											rset.getString("qboard_content"),
											rset.getString("qboard_writer"),
											rset.getDate("qboard_date"),
											rset.getString("rep_content"),
											rset.getString("rep_writer"));
				list.add(qb);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return list;
		
		
	}

	public int insertQna(Connection conn, QnaBoard qb) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertQna");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, qb.getQboard_title());
			pstmt.setString(2, qb.getQboard_content());
			pstmt.setString(3, qb.getQboard_writer());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public QnaBoard selectQnaboard(Connection conn, int qbo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		QnaBoard qb = null;
		
		String query = prop.getProperty("selectQnaboard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qbo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				qb = new QnaBoard(rset.getInt("qboard_no"),
									rset.getString("qboard_title"),
									rset.getString("qboard_content"),
									rset.getString("qboard_writer"),
									rset.getDate("qboard_date"),
									rset.getString("rep_content"),
									rset.getString("rep_writer"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return qb;
	}

	public int updateQboard(Connection conn, QnaBoard qb) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateQboard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, qb.getQboard_title());
			pstmt.setString(2, qb.getQboard_content());
			pstmt.setInt(3, qb.getQboardNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int deleteQboard(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteQboard");
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

	

	public int insertRepQna(Connection conn, QnaBoard rqb) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertRepQna");
		
		try {
			pstmt = conn.prepareStatement(query);
//			pstmt.setInt(1, rqb.getQboardNo());
			pstmt.setString(1, rqb.getQboard_title());
			pstmt.setString(2, rqb.getQboard_writer());
			pstmt.setString(3, rqb.getRep_content());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int getListCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("getListCount");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return result;
	}

	
	public ArrayList<QnaBoard> selectList(Connection conn, QPageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<QnaBoard> list1 = new ArrayList<QnaBoard>();
		
		String query = prop.getProperty("selectList1");
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				QnaBoard qb = new QnaBoard(rset.getInt("qboard_no"),
											rset.getString("qboard_title"),
											rset.getString("qboard_content"),
											rset.getString("qboard_writer"),
											rset.getDate("qboard_date"),
											rset.getString("status"),
											rset.getString("rep_content"),
											rset.getString("rep_writer"));
				list1.add(qb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return list1;
	}

	

}
