package board.model.service;

import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.close;
import java.sql.Connection;
import java.util.ArrayList;

import board.model.dao.NoticeDAO;
import board.model.vo.Notice;

public class NoticeService {

	public ArrayList<Notice> selectList() {
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDAO().selectList(conn);
		
		close(conn);
		
		
		return list;
	}

}
