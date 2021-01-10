package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Attachment;
import board.model.vo.Board;
import board.model.vo.PageInfo;

/**
 * Servlet implementation class memberRecipeListServlet
 */
@WebServlet("/list.memberR")
public class MemberRecipeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberRecipeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService service = new BoardService();

		//페이징 처리 변수
		int listCount;		//총 개시물 개수
		int currentPage;	//현재 페이지
		int pageLimit;		//한 페이지에 표시되 페이지 수
		int boardLimit;		//한 페이지에 보일 게시글 최대 개수
		int maxPage;		//전체 레이지 중 가장 마지막 페이지
		int startPage;		//페이징이 된 페이지 중 시작 페이지
		int endPage;		//페이징이 된 페이지 중 마지막 페이지
		
		listCount = service.getListCount();
		
		currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		pageLimit = 10;
		boardLimit = 9;
		
		//maxPage 계산
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		//startPage 계산
		startPage = pageLimit * ((currentPage - 1)/pageLimit) + 1;
		
		endPage = startPage + pageLimit  - 1;
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(currentPage, listCount, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		ArrayList<Board> bList = service.selectTlist(1, pi); // 게시판 리스트 가져오기
		ArrayList<Attachment> fList = service.selectTlist(2, pi); // 파일 리스트 가져오기
		
		ArrayList<Board> btList = service.selectTTlist(1); // 게시판 리스트 가져오기
		ArrayList<Attachment> ftList = service.selectTTlist(2); // 파일 리스트 가져오기
		
		String page = null;
		if(bList != null && fList != null) {
			request.setAttribute("bList", bList);
			request.setAttribute("fList", fList);
			request.setAttribute("btList", btList);
			request.setAttribute("ftList", ftList);
			request.setAttribute("pi", pi);
			page = "WEB-INF/views/board/memberRecipeList.jsp";
		} else {
			request.setAttribute("msg", "게시판 조회에 실패하였습니다.");
			page = "WEB-INF/views/common/errorPage.jsp";
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
