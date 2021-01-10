package product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import product.model.Service.ProductService;
import product.model.vo.PQNA;
import product.model.vo.PageInfo;

/**
 * Servlet implementation class ProductPQNAListServlet
 */
@WebServlet("/listPQNA.pr")
public class ProductPQNAListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductPQNAListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService service = new ProductService();
		
		String pId = request.getParameter("pId");
		
		int listCount;		//총 개시물 개수
		int currentPage;	//현재 페이지
		int pageLimit;		//한 페이지에 표시되 페이지 수
		int boardLimit;		//한 페이지에 보일 게시글 최대 개수
		int maxPage;		//전체 레이지 중 가장 마지막 페이지
		int startPage;		//페이징이 된 페이지 중 시작 페이지
		int endPage;		//페이징이 된 페이지 중 마지막 페이지
		
		listCount = service.getQListCount(pId);
		
		currentPage = Integer.parseInt(request.getParameter("choosenNum"));
		
		pageLimit = 10;
		boardLimit = 10;
		
		//maxPage 계산
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		//startPage 계산
		startPage = pageLimit * ((currentPage - 1)/pageLimit) + 1;
		
		endPage = startPage + pageLimit  - 1;
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(currentPage, listCount, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		ArrayList<PQNA> qList= service.selectQList(pId, pi);
		
		response.setContentType("application/json; charset=UTF-8");
		
		Gson gson = new Gson();
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(qList, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
