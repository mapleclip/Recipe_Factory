package board.model.dao;

import static common.JDBCTemplate.close;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import board.model.vo.Notice;

public class NoticeDAO {
	private Properties prop = new Properties();
	
	public NoticeDAO() {
		String fileName = NoticeDAO.class.getResource("/sql/notice/notice-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Notice> selectList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Notice> list = new ArrayList<Notice>();
				
				;
		
		String query = prop.getProperty("selectList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			while(rset.next()) {
				Notice no = new Notice(rset.getInt("notice_no"),
										rset.getString("notice_title"),
										rset.getString("notice_content"),
										rset.getString("memberid"),
										rset.getInt("notice_count"),
										rset.getDate("notice_date"));
				
				list.add(no);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return list;
	}
}
