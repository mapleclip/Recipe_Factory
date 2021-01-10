package recproboard.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import recproboard.model.dao.RecproBoardDAO;
import recproboard.model.vo.RecproBoard;
public class RecproBoardService {

	public ArrayList<RecproBoard> selectList() {
		Connection conn = getConnection();
		
		ArrayList<RecproBoard> list = new RecproBoardDAO().selectList(conn);
		close(conn);
		return list;
	}

	public RecproBoard selectRboard(int rpb) {
		Connection conn = getConnection();
		
		RecproBoardDAO dao = new RecproBoardDAO();
		RecproBoard rb = null;
		
		rb = dao.selectRboard(conn, rpb);
		
		if(rb != null) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return rb;
	}

	public int updateRboard(RecproBoard rb) {
		Connection conn = getConnection();
		int result = new RecproBoardDAO().updateRboard(conn, rb);
		
		if(result >0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteRboard(int no) {
		Connection conn = getConnection();
		
		int result = new RecproBoardDAO().deleteRboard(conn, no);
		if(result > 0) {
			commit(conn); 
		} else {
			rollback(conn);
		}
		return result;
	}

	

	public int insertRboard(RecproBoard rb) {
		Connection conn = getConnection();
		int result = new RecproBoardDAO().insertRecPro(conn, rb);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

}
