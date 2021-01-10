package qnaboard.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import qnaboard.model.dao.QnaBoardDAO;
import qnaboard.model.vo.QPageInfo;
import qnaboard.model.vo.QnaBoard;

public class QnaboardService {

	public ArrayList<QnaBoard> selectList() {
		Connection conn = getConnection();
		 
		ArrayList<QnaBoard> list = new QnaBoardDAO().selectList(conn);
		
		close(conn);
		
		
		return list;
	}

	public int insertQboard(QnaBoard qb) {
		Connection conn = getConnection();
		int result = new QnaBoardDAO().insertQna(conn, qb);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public QnaBoard selectQboard(int qbo) {
		Connection conn = getConnection();
		
		QnaBoardDAO dao = new QnaBoardDAO();
		
		QnaBoard qb = null;
		qb = dao.selectQnaboard(conn, qbo);
		if(qb != null) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return qb;
	}

	public int updateQboard(QnaBoard qb) {
		Connection conn = getConnection();
		int result = new QnaBoardDAO().updateQboard(conn,qb);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteQboard(int no) {
		Connection conn = getConnection();
		
		int result = new QnaBoardDAO().deleteQboard(conn, no);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	

	public int insertRepQboard(QnaBoard rqb) {
		Connection conn = getConnection();
		int result = new QnaBoardDAO().insertRepQna(conn, rqb);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int getListCount() {
		Connection conn = getConnection();
		int result = new QnaBoardDAO().getListCount(conn);
		close(conn);
		return result;
	}

	

	public ArrayList<QnaBoard> selectList(QPageInfo pi) {
		Connection conn = getConnection();
		ArrayList<QnaBoard> list1 = new QnaBoardDAO().selectList(conn, pi);
		close(conn);
		return list1;
	}

	
	
}
