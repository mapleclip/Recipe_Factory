package qnaboard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import qnaboard.model.vo.QPageInfo;
import qnaboard.model.service.QnaboardService;
import qnaboard.model.vo.QnaBoard;

/**
 * Servlet implementation class Board_one
 */
@WebServlet("/QboardListServlet.qb")
public class QboardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QboardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//문의글
		
		
		
		
		QnaboardService qService = new QnaboardService();
		
		
		
		int listCount; //총 페이지 개수
		int currentPage; //현재 페이지
		int pageLimit; //한 페이지에 표시될 페이지 수
		int boardLimit; //한 페이지에 보일 게시글 최대 개수
		int maxPage; //전체 페이지 중 가장 마지막 페이지
		int startPage; //페이징이 된 페이지 중 시작 페이지
		int endPage; //페이징이 된 페이지 중 마지막 페이지
		
		listCount = qService.getListCount();
		
		currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		pageLimit = 10;
		boardLimit = 10;
		// maxPage 계산
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		startPage = pageLimit * ((currentPage - 1)/pageLimit) + 1;
		
		endPage = startPage + pageLimit - 1;
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		QPageInfo pi = new QPageInfo(currentPage, listCount, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		ArrayList<QnaBoard> list = new QnaboardService().selectList();
		ArrayList<QnaBoard> list1 = qService.selectList(pi);
		
		
		
		
		
		
		
		
		
		
		
		
		
		String page = null;
		
		if(list != null) {
			page = "WEB-INF/views/qnaboard/qboardList.jsp";
			request.setAttribute("list", list);
			request.setAttribute("list1", list1);
			request.setAttribute("pi", pi);
			
		} else {
			page = "WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("msg", "1:1문의게시판 조회에 실패하였습니다");
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
