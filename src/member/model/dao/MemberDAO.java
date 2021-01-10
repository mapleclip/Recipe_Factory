package member.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import member.model.vo.Member;

public class MemberDAO {
	
	private Properties prop = new Properties();
	
	public MemberDAO() {
		String fileName = MemberDAO.class.getResource("/sql/member/member-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//회원가입 8.
	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertMember");
		
		//member-query에 insertMember작성
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1,  member.getMemberid());
			pstmt.setString(2,  member.getMembername());
			pstmt.setString(3,  member.getPassword());
			pstmt.setString(4,  member.getPhone());
			pstmt.setString(5,  member.getEmail());
			pstmt.setString(6,  member.getPostal());
			pstmt.setString(7,  member.getAddress1());
			pstmt.setString(8,  member.getAddress2());
			pstmt.setString(9,  member.getGender());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	//회원가입 17.
	public int checkId(Connection conn, String memberid) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("idCheck");
		
		//member-query에 idCheck 넣기
		//db만들고 이름 바뀌면 idCheck다시 수정
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, memberid);
			rset = pstmt.executeQuery();	//결과는 무조건 하나니까 if로
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	//회원가입 27.
	public int checkEmail(Connection conn, String userEmail) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("emailCheck");
		
		//member-query에 emailCheck넣기
		//db만들고 이름 변경
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setNString(1,  userEmail);
			
			rset = pstmt.executeQuery();	//결과는 무조건 하나니까 if로
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public Member loginMember(Connection conn, Member member) {
		// 위치홀더가 필요하므로 PreparedStatement
		// select쿼리 = ResultSet
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// 최종적으로 나온 rset을 member에 담는다
		Member loginUser = null;
		
		String query = prop.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberid());
			pstmt.setString(2, member.getPassword());
			// select = executequery
			
			
			rset = pstmt.executeQuery();
			// 실제로 보내는 부분
			
			if(rset.next()) { //다음 값이 있으면
				loginUser = new Member(rset.getString("memberid"),
										rset.getString("password"),
										rset.getString("membername"),
										rset.getString("phone"),
										rset.getString("email"),
										rset.getString("postal"),
										rset.getString("address1"),
										rset.getString("address2"),
										rset.getString("gender"),
										rset.getDate("enroll_date"),
										rset.getDate("delete_date"),
										rset.getString("status"));
	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println(loginUser);
		return loginUser;
	}

	public Member selectMember(Connection conn, String loginUserId) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		
		String query = prop.getProperty("selectMember");
		
		

		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loginUserId);
			
			rset = pstmt.executeQuery();
			
			//rset은 0,1개니까 if문
			if(rset.next()) {
				member = new Member(rset.getString("MEMBERID"),
						rset.getString("PASSWORD"),
						rset.getString("MEMBERNAME"),
						rset.getString("PHONE"),
						rset.getString("EMAIL"),
						rset.getString("POSTAL"),
						rset.getString("ADDRESS1"),
						rset.getString("ADDRESS2"),
						rset.getString("GENDER"),
						rset.getDate("ENROLL_DATE"),
						rset.getDate("DELETE_DATE"),
						rset.getString("STATUS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return member;
	}

	public int updateMember(Connection conn, Member myInfo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, myInfo.getMembername());
			pstmt.setString(2, myInfo.getPhone());
			pstmt.setString(3, myInfo.getEmail());
			pstmt.setString(4, myInfo.getPostal());
			pstmt.setString(5, myInfo.getAddress1());
			pstmt.setString(6, myInfo.getAddress2());
			pstmt.setString(7, myInfo.getMemberid());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updatePwd(Connection conn, String userId, String userPwd, String newPwd) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updatePwd");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newPwd);
			pstmt.setString(2, userId);
			pstmt.setString(3, userPwd);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteMember(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return result;
	}

	public String searchId(Connection conn, String membername, String email) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String result = null;
		
		String query = prop.getProperty("checkId");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, membername);
			pstmt.setString(2, email);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getString(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return result;
	}

	public String searchPwd(Connection conn, String userId, String email) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String result = null;
		
		String query = prop.getProperty("searchPwd"); 
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, email);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public int searchPwdUpdatePwd(Connection conn, String userId, String newPwd) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("searchPwdUpdatePwd");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newPwd);
			pstmt.setString(2, userId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

}
