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
 * Servlet implementation class RecproBoardUpdateServlet
 */
@WebServlet("/rpbUpdate.re")
public class RecproBoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecproBoardUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int no = Integer.parseInt(request.getParameter("no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		RecproBoard rb = new RecproBoard();
		rb.setRecpro_no(no);
		rb.setRecpro_title(title);
		rb.setRecpro_content(content);
		
		int result = new RecproBoardService().updateRboard(rb);
		if(result > 0) {
			response.sendRedirect("detail.rpb?rpb="+no);
		} else {
			request.setAttribute("msg", "상품 제안 수정에 실패하였습니다");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp");
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
