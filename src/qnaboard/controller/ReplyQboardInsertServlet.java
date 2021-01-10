package qnaboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qnaboard.model.service.QnaboardService;
import qnaboard.model.vo.QnaBoard;

/**
 * Servlet implementation class ReplyQboardInsertServlet
 */
@WebServlet("/ReplyQboardInsert.rq")
public class ReplyQboardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyQboardInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String name = request.getParameter("repwriter");
		int no = Integer.parseInt(request.getParameter("no"));
		String content = request.getParameter("content");
		QnaBoard rqb = new QnaBoard(no,title,name, content);
		
		int result = new QnaboardService().insertRepQboard(rqb);
		System.out.println(result);
		if(result > 0) {
			response.sendRedirect("QboardListServlet.qb");
		} else {
			request.setAttribute("msg", "1:1문의 등록에 실패하였습니다");
			request.getRequestDispatcher("WEB-INF/common/errorPage.jsp");
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
