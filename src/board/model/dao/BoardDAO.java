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

import board.model.vo.Attachment;
import board.model.vo.Board;
import board.model.vo.PageInfo;
import board.model.vo.Reply;

public class BoardDAO {
	
	private Properties prop = new Properties();
	
	public BoardDAO() {
		String fileName = BoardDAO.class.getResource("/sql/board/board-query.properties").getPath();
		
		//139-3
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

	
	public int updateCount(Connection conn, int bo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList selectBlist(Connection conn, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = new ArrayList<Board>();
		
		String query = prop.getProperty("selectBlist");
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit()+1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Board(rset.getInt("board_id"),
								   rset.getInt("board_type"),
								   rset.getString("board_title"),
								   rset.getString("board_content"),
								   rset.getString("board_writer"),
								   rset.getInt("board_count"),
								   rset.getDate("create_date"),
								   rset.getDate("modify_date"),
								   rset.getString("status")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList selectFlist(Connection conn, PageInfo pi) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Attachment> list = null;
		
		String query = prop.getProperty("selectFlist");

		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit()+1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		try {
			stmt = conn.createStatement(); 
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<Attachment>();
			while(rset.next()) {
				list.add(new Attachment(rset.getInt("board_id"),
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

	public int insertBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, 2);
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setString(4, b.getBoardWriter());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertAttachment(Connection conn, ArrayList<Attachment> fileList) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertAttachment");
		
		try {
			for(int i = 0; i < fileList.size(); i++) {
				Attachment at = fileList.get(i);
				
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

	public Board selectBoard(Connection conn, int bo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board b = null;
		
		String query = prop.getProperty("selectBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				b = new Board(rset.getInt("board_id"),
							rset.getInt("board_type"),
							rset.getString("board_title"),
							rset.getString("board_content"),
							rset.getString("board_writer"),
							rset.getInt("board_count"),
							rset.getDate("create_date"),
							rset.getDate("modify_date"),
							rset.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return b;
	}

	public ArrayList<Attachment> selectThumbnail(Connection conn, int bId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
			      
		ArrayList<Attachment> list = null;
			      
		String query = prop.getProperty("selectThumbnail");
			      
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);
				         
			rset = pstmt.executeQuery();
				         
			list = new ArrayList<Attachment>();
				         
			while(rset.next()) {
				Attachment at = new Attachment();
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

	public int updateBoard(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setInt(3, board.getBoardId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteBoard(Connection conn, int bId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteBoard");
		
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

	public ArrayList<Reply> selectReplyList(Connection conn, int bId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Reply> list = null;
		
		String query = prop.getProperty("selectReplyList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Reply>();
			while(rset.next()) {
	            list.add(new Reply(rset.getInt("reply_id"),
			                        rset.getString("reply_content"),
			                        rset.getInt("REF_BID"),
			                        rset.getString("memberid"),
			                        rset.getDate("create_date"),
			                        rset.getDate("modify_date"),
			                        rset.getString("status")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertReply(Connection conn, Reply r) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertReply");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, r.getReplyContent());
			pstmt.setInt(2, r.getRefBid());
			pstmt.setString(3, r.getReplyWriter());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateAttachment(Connection conn, ArrayList<Attachment> fileList) {
		PreparedStatement pstmt = null;
		int result = 0;
		System.out.println(fileList);
		String query = prop.getProperty("updateAttachment");
		
		try {
			for(int i = 0; i < fileList.size(); i++) {
				Attachment at = fileList.get(i);
				
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setInt(3, at.getBoardId());
				pstmt.setInt(4, at.getFileId());
				
				result += pstmt.executeUpdate();
				System.out.println(at + "" + result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList selectBTlist(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
		
		String query = prop.getProperty("selectBTlist");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<Board>();
			while(rset.next()) {
				list.add(new Board(rset.getInt("board_id"),
								   rset.getInt("board_type"),
								   rset.getString("board_title"),
								   rset.getString("board_content"),
								   rset.getString("board_writer"),
								   rset.getInt("board_count"),
								   rset.getDate("create_date"),
								   rset.getDate("modify_date"),
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

	public ArrayList selectFTlist(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Attachment> list = null;
		
		String query = prop.getProperty("selectFTlist");
		
		try {
			stmt = conn.createStatement(); 
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<Attachment>();
			while(rset.next()) {
				list.add(new Attachment(rset.getInt("board_id"),
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

	public int deleteReply(Connection conn, int rId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteReply");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
}
