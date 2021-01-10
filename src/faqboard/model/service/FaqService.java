package faqboard.model.service;

import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.close;
import java.sql.Connection;
import java.util.ArrayList;

import faqboard.model.dao.FaqDAO;
import faqboard.model.vo.Faqboard;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.rollback;
public class FaqService {

	public ArrayList<Faqboard> selectList() {
		Connection conn = getConnection();
		
		ArrayList<Faqboard> list = new FaqDAO().selectList(conn);
		close(conn);
		return list;
	}

	public int insertFboard(Faqboard fb) {
		Connection conn = getConnection();
		int result = new FaqDAO().insertFaq(conn, fb);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int deleteFboard(int no) {
		Connection conn = getConnection();
		
		int result = new FaqDAO().deleteFboard(conn, no);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

}
