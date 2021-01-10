package recproboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recproboard.model.service.RecproBoardService;
import recproboard.model.vo.RecproBoard;

/**
 * Servlet implementation class RecproBoardInsertServlet
 */
@WebServlet("/Rboardinsert.re")
public class RecproBoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecproBoardInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		RecproBoard rb = new RecproBoard(title, content, writer);
		
		int result = new RecproBoardService().insertRboard(rb);
		
		if(result > 0) {
			response.sendRedirect("RecProListServlet.rp");
		} else {
			request.setAttribute("msg", "상품제안 등록에 실패하였습니다");
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
