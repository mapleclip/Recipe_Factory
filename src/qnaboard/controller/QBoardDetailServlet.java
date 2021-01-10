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
 * Servlet implementation class QBoardDetailServlet
 */
@WebServlet("/detail.qbo")
public class QBoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QBoardDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int qbo = Integer.parseInt(request.getParameter("qbo"));
		
		QnaBoard qb = new QnaboardService().selectQboard(qbo);
		
		String page = null;
		
		if(qb != null) {
			page = "WEB-INF/views/qnaboard/qboardDetail.jsp";
			
			request.setAttribute("QnaBoard", qb);
			request.getRequestDispatcher(page).forward(request, response);
			
		} else {
			page = "WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("msg", "1:1 문의사항 조회에 실패하였습니다.");
			request.getRequestDispatcher(page).forward(request, response);
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
