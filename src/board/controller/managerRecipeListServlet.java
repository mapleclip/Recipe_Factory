//레펙 레시피 3. /list.managerR로 연결 서블릿
package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import board.model.service.ManagerRecipeService;
import board.model.vo.MRAttachment;
import board.model.vo.PageInfo;
import board.model.vo.ManagerRecipe;

/**
 * Servlet implementation class managerRecipeFormServlet
 */
@WebServlet("/list.managerR")
public class managerRecipeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public managerRecipeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManagerRecipeService bservice = new ManagerRecipeService();
		
		//게시판 리스트 가져오기
		ArrayList<ManagerRecipe> bList = bservice.selectTList(1);
		ArrayList<ManagerRecipe> btList = bservice.selectTTList(1);
		//파일 리스트 가져오기
		ArrayList<MRAttachment> fList = bservice.selectTList(2);
		ArrayList<MRAttachment> ftList = bservice.selectTTList(2);
		
		//페이징 처리
		int listCount;	//총 게시물 개수
		int currentPage;//현재 페이지
		int pageLimit;	//한 페이지에 표시될 페이지 수
		int boardLimit;	//한 페이지에 보일 게시글 최대 개수
		int maxPage;	//전체 페이지 중 가장 마지막 페이지
		int startPage;	//페이징이 된 페이지 중 시작 페이지
		int endPage;	//페이징이 된 페이지 중 마지막 페이지
		
		listCount = bservice.getListCount();
		
		
		
		currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		pageLimit = 9;
		boardLimit = 9;
		
		//maxPage 계산
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		//startPage 계산
		startPage = pageLimit * ((currentPage - 1)/pageLimit) +1;
						
		//endPage 계산
		endPage = startPage + pageLimit -1;
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(currentPage, listCount, pageLimit, boardLimit, maxPage, startPage, endPage);
		ArrayList<ManagerRecipe> manaRList = bservice.selectList(pi);
		
		//레펙 레시피 7.
		String page = null;
		if((bList != null && fList !=null) && manaRList != null) {
			page = "WEB-INF/views/board/managerRecipeForm.jsp";
			request.setAttribute("bList", bList);
			request.setAttribute("fList", fList);
			request.setAttribute("btList", btList);
			request.setAttribute("ftList", ftList);
			request.setAttribute("manaRList", manaRList);
			request.setAttribute("pi", pi);
		} else {
			request.setAttribute("msg", "레시피 조회에 실패하였습니다.");
			page = "WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("pi", pi);
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
