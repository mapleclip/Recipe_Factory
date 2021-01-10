package member.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;

import member.model.dao.MemberDAO;
import member.model.vo.Member;

public class MemberService {

	//회원가입 7.
	public int insertmember(Member member) {
		Connection conn = getConnection();
		int result = new MemberDAO().insertMember(conn, member);
		
		//회원가입 9.
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	//회원가입 16.
	public int checkId(String memberid) {
		Connection conn = getConnection();
		int result = new MemberDAO().checkId(conn, memberid);
		
		//회원가입 18.
		close(conn);
		
		return result;
	}

	//회원가입 26.
	public int checkEail(String userEmail) {
		Connection conn = getConnection();
		int result = new MemberDAO().checkEmail(conn, userEmail);
		
		//회원가입 28.
		close(conn);
		
		return result;
	}
	
	
	// 로그인
	public Member loginMember(Member member) {
		Connection conn = getConnection();
		
		
		MemberDAO mDAO = new MemberDAO();
		
		Member loginUser = mDAO.loginMember(conn, member);
		
		close(conn);
		return loginUser;
	}

	public Member selectMember(String loginUserId) {
		
		Connection conn = getConnection();
		
		Member member = new MemberDAO().selectMember(conn, loginUserId);
		
		close(conn);
		
		
		return member;
	}

	public int updateMember(Member myInfo) {
		Connection conn = getConnection();
		int result = new MemberDAO().updateMember(conn, myInfo);
		
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		
		return result;
	}

	public int updatePwd(String userId, String userPwd, String newPwd) {
		Connection conn = getConnection();
		
		int result = new MemberDAO().updatePwd(conn, userId, userPwd, newPwd);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int deleteMember(String userId) {
		Connection conn = getConnection();
		
		int result = new MemberDAO().deleteMember(conn, userId);
		
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public String searchId(String membername, String email) {
		
		Connection conn = getConnection();
		
		String result = new MemberDAO().searchId(conn, membername, email);
		
		close(conn);
		
		return result;
	}

	public String searchPw(String userId, String email) {
		
		Connection conn = getConnection();
		
		String result = new MemberDAO().searchPwd(conn, userId, email);
		
		close(conn);
		
		
		return result;
	}

	public int searchPwdUpdatePwd(String userId, String newPwd) {
		
		Connection conn = getConnection();
		
		int result = new MemberDAO().searchPwdUpdatePwd(conn, userId, newPwd);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

}
