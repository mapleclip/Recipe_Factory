package admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import admin.model.vo.PageInfo;
import cart.model.vo.Buy;


/**
 * Servlet implementation class mainFormServlet
 */
@WebServlet("/main.ad")
public class mainFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mainFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글의 전체 갯수를 먼저 구한 후 페이지 번호에 따라 그 때 그 때 게시물들을 가져올 예정
		AdminService aService = new AdminService();
		
		// 페이징 처리 변수
		int listCount;	 // 총 게시물 갯수
		int currentPage; // 현재 페이지
		int pageLimit;	 // 한 페이지에 표시될 페이지 수
		int boardLimit;  // 한 페이지에 보일 게시글 최대 갯수
		int maxPage; 	 // 전체 페이지 중 가장 마지막 페이지
		int startPage; 	 // 페이징이 된 페이지 중 시작 페이지
		int endPage; 	 // 페이징이 된 페이지 중 마지막 페이지
		
		listCount = aService.getListCount();
		
		currentPage = 1; // 페이지를 요청하지 않았을때는 기본적으로 1페이지다 하고 설정
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		pageLimit = 10; // 한 페이지에 표시될 페이지 수 10개
		boardLimit = 10; // 한 페이지에 보일 게시글 최대 갯수 10개
		
		// maxPage 계산
		maxPage = (int)Math.ceil((double)listCount/boardLimit); // Math.ceil = 올림
		
		// startPage 계산
		startPage = pageLimit * ((currentPage - 1)/pageLimit) + 1;
		
		// endPage 계산
		endPage = startPage + pageLimit -1;
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		// 변수가 너무 많으니 전부 담을 PageInfo 객체 생성
		PageInfo pi = new PageInfo(currentPage, listCount, pageLimit, boardLimit, maxPage, startPage, endPage);
		ArrayList<Buy> list = aService.selectList(pi);
		
		String page = null;
		if(list != null) {
			page = "WEB-INF/views/admin/adminMain.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
		} else {
			page = "WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("msg", "관리자 페이지 조회에 실패하였습니다.");
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
