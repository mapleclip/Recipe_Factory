package board.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import board.model.dao.BoardDAO;
import board.model.vo.Attachment;
import board.model.vo.Board;
import board.model.vo.PageInfo;
import board.model.vo.Reply;

public class BoardService {

	public int getListCount() {
		Connection conn = getConnection();
		
		int result = new BoardDAO().getListCount(conn);
		
		close(conn);
		
		return result;
	}

	


	// 멤버레시피
	public ArrayList selectTlist(int i, PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList list = null;
		
		BoardDAO dao = new BoardDAO();
		
		if(i == 1) {
			list = dao.selectBlist(conn, pi);
		} else {
			list = dao.selectFlist(conn, pi);
		}
		close(conn);
		
		return list;
	}

	// 멤버 레시피 등록
	public int insertMemberRecipe(Board b, ArrayList<Attachment> fileList) {
		Connection conn = getConnection();
		
		BoardDAO dao = new BoardDAO();
		
		int result1 = dao.insertBoard(conn, b);
		int result2 = dao.insertAttachment(conn, fileList);
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1;
	}

	public Board selectBoard(int bo) {
		Connection conn = getConnection();
		
		BoardDAO dao = new BoardDAO();
		
		int result = dao.updateCount(conn, bo);
		
		Board b = null;
		if(result > 0) {
			b = new BoardDAO().selectBoard(conn, bo);
			
			if(b != null) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return b;
	}

	public ArrayList<Attachment> selectThumbnail(int bId) {
		Connection conn = getConnection();
		
		int result = new BoardDAO().updateCount(conn, bId);
		
		ArrayList<Attachment> list = null;
		
		if(result > 0) {
			list = new BoardDAO().selectThumbnail(conn, bId);
			
			if(list != null) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return list;
	}

	public int updateMemberRecipe(Board board, String[] oldFiles, ArrayList<Attachment> fileList) {
		Connection conn = getConnection();
		
		BoardDAO dao = new BoardDAO();
		
		int result1 = dao.updateBoard(conn, board);
		int result2 = dao.updateAttachment(conn, fileList);
		int result3 = 0;
		System.out.println("test1");
		System.out.println("test1" + result1);
		System.out.println("test1" + result2);
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
			result3 = 1;
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result3;
	}

	public int deleteBoard(int bId) {
		Connection conn = getConnection();
		
		int result = new BoardDAO().deleteBoard(conn, bId);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public ArrayList<Reply> selectReplyList(int bId) {
		Connection conn = getConnection();
		
		ArrayList<Reply> list = new BoardDAO().selectReplyList(conn, bId);
		
		close(conn);
		
		return list;
	}

	public ArrayList<Reply> insertReply(Reply r) {
		Connection conn = getConnection();
		
		BoardDAO bDAO = new BoardDAO();
		
		int result = bDAO.insertReply(conn, r);
		
		ArrayList<Reply> list = null;
		if(result > 0) {
			commit(conn);
			list = bDAO.selectReplyList(conn, r.getRefBid());
		} else {
			rollback(conn);
		}
		close(conn);
		
		return list;
	}

	public ArrayList selectTTlist(int i) {
		Connection conn = getConnection();
		
		ArrayList list = null;
		
		BoardDAO dao = new BoardDAO();
		
		if(i == 1) {
			list = dao.selectBTlist(conn);
		} else {
			list = dao.selectFTlist(conn);
		}
		close(conn);
		
		return list;
	}

	public int deleteReply(int rId) {
		Connection conn = getConnection();
		
		int result = new BoardDAO().deleteReply(conn, rId);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}




}
