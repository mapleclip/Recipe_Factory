//회원가입 5.
package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberinsertServlet
 */
@WebServlet(urlPatterns = "/insert.me", name = "MemberInsertServlet")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원가입 6.
		request.setCharacterEncoding("UTF-8");
		
		String memberid = request.getParameter("joinMemberid");
		String membername = request.getParameter("joinMembername");
		String password = request.getParameter("joinPassword1");
		String phone = request.getParameter("joinPhone");
		String post = request.getParameter("joinPostal");
		String address1 = request.getParameter("joinAddress1");
		String address2 = request.getParameter("joinAddress2");
		String email = request.getParameter("joinEmail");
		String gender = request.getParameter("gender");
		
		//member에 담아서 한꺼번에 전달
		Member member = new Member(memberid, membername, password, phone, email,  post, address1, address2, gender);
		
		int result = new MemberService().insertmember(member);
		
		//회원가입 10.
		if(result > 0) {
			response.sendRedirect(request.getContextPath() + "/");
		} else {
			request.setAttribute("msg", "회원가입에 실패하였습니다.");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
