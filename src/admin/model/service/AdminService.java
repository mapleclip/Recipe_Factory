package admin.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import admin.model.dao.AdminDAO;
import admin.model.vo.BAttachment;
import admin.model.vo.Banner;
import admin.model.vo.PageInfo;
import cart.model.vo.Buy;
import member.model.vo.Member;

public class AdminService {

	// 총 게시물 갯수
	public int getListCount() {
		Connection conn = getConnection();
		
		int result = new AdminDAO().getListCount(conn);
		
		close(conn);
		
		return result;
	}

	public ArrayList<Buy> selectList(admin.model.vo.PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Buy> list = new AdminDAO().selectList(conn, pi);
		
		close(conn);
		
		return list;
	}

	public int getMemberListCount() {
		Connection conn = getConnection();
		
		int result = new AdminDAO().getMemberListCount(conn);
		
		close(conn);
		
		return result;
	}

	public ArrayList<Member> selectMemberList(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Member> list = new AdminDAO().selectMemberList(conn, pi);
		
		close(conn);
		
		return list;
	}

	public int insertMainBanner(Banner b, ArrayList<BAttachment> fileList) {
		Connection conn = getConnection();
		
		AdminDAO dao = new AdminDAO();
		
		int result1 = dao.insertMainBanner(conn, b);
		int result2 = dao.insertAttachment(conn, fileList);
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1;
	}


	public ArrayList selectTTlist(int i) {
		Connection conn = getConnection();
		
		ArrayList list = null;
		
		AdminDAO dao = new AdminDAO();
		
		if(i == 1) {
			list = dao.selectBlist(conn);
		} else {
			list = dao.selectFlist(conn);
		}
		close(conn);
		
		return list;
	}

	public int updateMainBanner(Banner b, String[] oldFiles, ArrayList<BAttachment> fileList) {
		Connection conn = getConnection();
		
		AdminDAO dao = new AdminDAO();
		
		int result1 = dao.updateBanner(conn, b);
		int result2 = dao.updateAttachment(conn, fileList);
		int result3 = 0;
		System.out.println("test1" + result1);
		System.out.println("test2" + result2);
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
			result3 = 1;
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result3;
	}


	public int updateDeli(String chk) {
		Connection conn = getConnection();
		
		int result = new AdminDAO().UpdateDeil(conn, chk);
		
		close(conn);
		
		return result;		
		
	}



}
