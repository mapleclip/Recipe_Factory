package board.model.dao;

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

import board.model.vo.MRAttachment;
import board.model.vo.ManagerRecipe;
import board.model.vo.PageInfo;

public class ManagerRecipeDAO {
	private Properties prop = new Properties();
	
	public ManagerRecipeDAO() {
		String fileName = ManagerRecipeDAO.class.getResource("/sql/managerRecipe/managerRecipe-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getListCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("getListCount");
		
		//ManagerRecipe-query에 getListCount작성
		
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

	public ArrayList<ManagerRecipe> selectList(Connection conn, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ManagerRecipe> list = new ArrayList<ManagerRecipe>();
		
		String query = prop.getProperty("selectManagerRecipeList");
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit()+1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		//ManagerRecipe-query에 selectManagerRecipeList작성
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ManagerRecipe m = new ManagerRecipe(rset.getInt("m_r_no"),
													rset.getString("mem_writer"),
													rset.getString("p_id"),
													rset.getString("m_r_title"),
													rset.getString("m_r_thumbnail"),
													rset.getString("m_r_explanation"),
													rset.getInt("m_r_capacity"),
													rset.getInt("m_r_price"),
													rset.getString("m_r_origin"),
													rset.getInt("board_count"),
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

	//레펙 레시피 5.
	public ArrayList selectMBList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<ManagerRecipe> list = null;
		
		String query = prop.getProperty("selectMBList");
		
		//ManagerRecipe-query에 selectMBList작성
		
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<ManagerRecipe>();
						
			while(rset.next()) {
				list.add(new ManagerRecipe(rset.getInt("m_r_no"),
											rset.getString("mem_writer"),
											rset.getString("p_id"),
											rset.getString("m_r_title"),
											rset.getString("m_r_thumbnail"),
											rset.getString("m_r_explanation"),
											rset.getInt("m_r_capacity"),
											rset.getInt("m_r_price"),
											rset.getString("m_r_origin"),
											rset.getInt("board_count"),
											rset.getString("status")));
				
				
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		
		return list;
	}

	//레펙 레시피 6.
	public ArrayList selectMFList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<MRAttachment> list = null;
		
		String query = prop.getProperty("selectMFList");
		
		//ManagerRecipe-query에 selectMFList작성 //여기 다시 확인
		
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<MRAttachment>();
			
			while(rset.next()) {
				list.add(new MRAttachment(rset.getInt("m_r_no"),
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

	public int insertManageRecipeBoard(Connection conn, ManagerRecipe b) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertManageRecipeBoard");
		
		//ManagerRecipe-query.properties에 insertManageRecipeBoard작성
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, b.getMemberId());
			pstmt.setString(2, b.getP_Id());
			pstmt.setString(3, b.getM_R_Title());
			pstmt.setString(4, b.getM_R_Thumbnail());
			pstmt.setString(5, b.getM_R_Explanation());
			pstmt.setInt(6, b.getM_R_Capacity());
			pstmt.setInt(7, b.getM_R_price());
			pstmt.setString(8, b.getM_R_Origin());
		
			
			result += pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertMRAttachement(Connection conn, ArrayList<MRAttachment> fileList) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertMRAttachment");
		
		//board-query에 insertAttachment작성
		
		try {
			for(int i = 0; i < fileList.size(); i++) {
				MRAttachment at = fileList.get(i);
				
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

	public int updateCount(Connection conn, int b) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			//managerRecipe-query에 updateCount작성
			pstmt.setInt(1, b);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ManagerRecipe selectManagerRecipe(Connection conn, int b) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ManagerRecipe mr = null;
		
		String query = prop.getProperty("selectManagerRecipe");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			//managerRecipe-query에 selectManagerRecipe작성
			
			pstmt.setInt(1, b);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				mr = new ManagerRecipe(rset.getInt("m_r_no"),
							rset.getString("mem_writer"),
							rset.getString("p_id"),
							rset.getString("m_r_title"),
							rset.getString("m_r_thumbnail"),
							rset.getString("m_r_explanation"),
							rset.getInt("m_r_capacity"),
							rset.getInt("m_r_price"),
							rset.getString("m_r_origin"),
							rset.getInt("board_count"),
							rset.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return mr;
	}

	//레펙 레시피12.
	public ArrayList<MRAttachment> selectThumbnail(Connection conn, int bId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MRAttachment> list = null;
		
		String query = prop.getProperty("selectThumbnail");
		
		//managerRecipe-query에 selectThumbnail작성
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, bId);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<MRAttachment>();
			
			while(rset.next()) {
				MRAttachment at = new MRAttachment();
				at.setFileId(rset.getInt("file_id"));
				at.setOriginName(rset.getString("origin_name"));
				at.setChangeName(rset.getString("change_name"));
				at.setFilePath(rset.getString("file_path"));
				at.setUploadDate(rset.getDate("upload_date"));
				
				list.add(at);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}

	public int updateManageRecipeBoard(Connection conn, ManagerRecipe mr) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateManageRecipeBoard");
		
		//ManagerRecipe-query.properties에 updateManageRecipeBoard작성
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, mr.getM_R_Title());
			pstmt.setString(2, mr.getM_R_Thumbnail());
			pstmt.setString(3, mr.getM_R_Explanation());
			pstmt.setInt(4, mr.getM_R_Capacity());
			pstmt.setInt(5, mr.getM_R_price());
			pstmt.setInt(6, mr.getM_R_NO());
		
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateMRAttachement(Connection conn, ArrayList<MRAttachment> fileList) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("updateMRAttachment");
		
		//board-query에 updateAttachment작성
		
		try {
			for(int i = 0; i < fileList.size(); i++) {
				MRAttachment at = fileList.get(i);
				
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setString(3, at.getFilePath());
				pstmt.setInt(4, at.getM_R_NO());

				result = pstmt.executeUpdate();
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
			
		return result;
	}

	public int updateBoard(Connection conn, ManagerRecipe managerRecipe) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateManagerRecipe");

		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, managerRecipe.getM_R_Title());
			pstmt.setString(2, managerRecipe.getM_R_Thumbnail());
			pstmt.setString(3, managerRecipe.getM_R_Explanation());
			pstmt.setInt(4, managerRecipe.getM_R_Capacity());
			pstmt.setInt(5, managerRecipe.getM_R_price());
			pstmt.setString(6, managerRecipe.getM_R_Origin());
			pstmt.setInt(7, managerRecipe.getM_R_NO());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateAttachment(Connection conn, ArrayList<MRAttachment> fileList) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateAttachment");
		
		try {
			for(int i = 0; i < fileList.size(); i++) {
				MRAttachment at = fileList.get(i);
				
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setInt(3, at.getM_R_NO());
				pstmt.setInt(4, at.getFileId());
				
				result = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteManagerRecipe(Connection conn, int bId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteManagerRecipe");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList selectTMBList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<ManagerRecipe> list = null;
		
		String query = prop.getProperty("selectTMBList");
		
		//ManagerRecipe-query에 selectMBList작성
		
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<ManagerRecipe>();
						
			while(rset.next()) {
				list.add(new ManagerRecipe(rset.getInt("m_r_no"),
											rset.getString("mem_writer"),
											rset.getString("p_id"),
											rset.getString("m_r_title"),
											rset.getString("m_r_thumbnail"),
											rset.getString("m_r_explanation"),
											rset.getInt("m_r_capacity"),
											rset.getInt("m_r_price"),
											rset.getString("m_r_origin"),
											rset.getInt("board_count"),
											rset.getString("status")));
				
				
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		
		return list;
	}

	public ArrayList selectTMFList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<MRAttachment> list = null;
		
		String query = prop.getProperty("selectTMFList");
		
		//ManagerRecipe-query에 selectMFList작성 //여기 다시 확인
		
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<MRAttachment>();
			
			while(rset.next()) {
				list.add(new MRAttachment(rset.getInt("m_r_no"),
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
}
