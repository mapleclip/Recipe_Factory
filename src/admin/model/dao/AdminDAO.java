package admin.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import admin.model.vo.BAttachment;
import admin.model.vo.Banner;
import admin.model.vo.PageInfo;
import cart.model.vo.Buy;
import member.model.vo.Member;

public class AdminDAO {

	private Properties prop = new Properties();
	
	public AdminDAO() {
		String fileName = AdminDAO.class.getResource("/sql/admin/admin-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 총 게시물 갯수
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
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return result;
	}

	public ArrayList<Buy> selectList(Connection conn, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Buy> list = new ArrayList<Buy>();
		
		String query = prop.getProperty("selectList");
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Buy b = new Buy(rset.getInt("ORDERNO"),
						rset.getString("MEMBERID"),
						rset.getString("P_ID"),
						rset.getString("P_NAME"),
						rset.getInt("AMOUNT"),
						rset.getString("ORDERNAME"),
						rset.getString("ORDERADDRESS"),
						rset.getString("ORDERPHONE"),
						rset.getString("ORDERREQUIRE"),
						rset.getInt("TOTALPRICE"),
						rset.getDate("ORDERDATE"),
						rset.getString("BUY_STATUS"),
						rset.getString("DELI_STATUS"));
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int getMemberListCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("getMemberListCount");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return result;
	}

	public ArrayList<Member> selectMemberList(Connection conn, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		
		String query = prop.getProperty("selectMemberList");
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = new Member(rset.getString("memberid"),
									rset.getString("password"),
									rset.getString("membername"),
									rset.getString("phone"),
									rset.getString("email"),
									rset.getString("postal"),
									rset.getString("address1"),
									rset.getString("address2"),
									rset.getString("gender"),
									rset.getDate("enroll_date"),
									rset.getDate("delete_date"),
									rset.getString("status"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertMainBanner(Connection conn, Banner b) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertMainBanner");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getBannerTitle());
			pstmt.setString(2, b.getBannerContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertAttachment(Connection conn, ArrayList<BAttachment> fileList) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertAttachment");
		
		try {
			for(int i = 0; i < fileList.size(); i++) {
				BAttachment at = fileList.get(i);
				
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setString(3, at.getFilePath());
				pstmt.setInt(4, at.getFileLevel());
				
				result += pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public ArrayList selectBlist(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Banner> list = null;
		
		String query = prop.getProperty("selectBlist");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<Banner>();
			while(rset.next()) {
				list.add(new Banner(rset.getInt("banner_id"),
								   rset.getString("banner_title"),
								   rset.getString("banner_content"),
								   rset.getDate("create_date")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}

	public ArrayList selectFlist(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<BAttachment> list = null;
		
		String query = prop.getProperty("selectFlist");
		
		try {
			stmt = conn.createStatement(); 
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<BAttachment>();
			while(rset.next()) {
				list.add(new BAttachment(rset.getInt("file_id"),
										rset.getInt("banner_id"),
										rset.getString("change_name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return list;		
	}

	public int updateBanner(Connection conn, Banner b) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateBanner");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getBannerTitle());
			pstmt.setString(2, b.getBannerContent());
			pstmt.setInt(3, b.getBannerId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateAttachment(Connection conn, ArrayList<BAttachment> fileList) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateAttachment");
		
		try {
			for(int i = 0; i < fileList.size(); i++) {
				BAttachment at = fileList.get(i);
				
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setInt(3, at.getBannerId());
				pstmt.setInt(4, at.getFileId());
				
				System.out.println(at);
				result += pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;	
	}

	public int UpdateDeil(Connection conn, String chk) {
		
		Statement stmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateDeli");
		query += "(" + chk + ")";		
		
		try {
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return result;
	}



}
