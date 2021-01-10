package cart.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.Service.ProductService;
import product.model.vo.PAttachment;
import product.model.vo.PQNA;
import product.model.vo.PageInfo;
import product.model.vo.Product;
import product.model.vo.Review;

/**
 * Servlet implementation class detail_orderProduct
 */
@WebServlet("/detail.order_product")
public class detail_orderProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public detail_orderProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pId = request.getParameter("p_id");
		ProductService service = new ProductService();
		
		Product product = service.selectProduct(pId);
		
		ArrayList<PAttachment> fileList = service.selectThumbnail(pId);
		
		
		//페이징 처리 변수
		int listCount;		//총 개시물 개수
		int currentPage;	//현재 페이지
		int pageLimit;		//한 페이지에 표시되 페이지 수
		int boardLimit;		//한 페이지에 보일 게시글 최대 개수
		int maxPage;		//전체 레이지 중 가장 마지막 페이지
		int startPage;		//페이징이 된 페이지 중 시작 페이지
		int endPage;		//페이징이 된 페이지 중 마지막 페이지
		
		listCount = service.getRListCount(pId);
		
		currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		pageLimit = 5;
		boardLimit = 10;
		
		//maxPage 계산
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		//startPage 계산
		startPage = pageLimit * ((currentPage - 1)/pageLimit) + 1;
		
		endPage = startPage + pageLimit  - 1;
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo rpi = new PageInfo(currentPage, listCount, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		ArrayList<Review> rList= service.selectRList(pId, rpi);
		
		
		listCount = service.getQListCount(pId);

		currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		pageLimit = 5;
		boardLimit = 10;
		
		//maxPage 계산
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		//startPage 계산
		startPage = pageLimit * ((currentPage - 1)/pageLimit) + 1;
		
		endPage = startPage + pageLimit  - 1;
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo qpi = new PageInfo(currentPage, listCount, pageLimit, boardLimit, maxPage, startPage, endPage);

		ArrayList<PQNA> qList = service.selectQList(pId, qpi);

		String page = null;
		if(product != null && fileList != null && rList != null && qList != null) {
			request.setAttribute("product", product);
			request.setAttribute("fileList", fileList);
			request.setAttribute("rList", rList);
			request.setAttribute("qList", qList);
			request.setAttribute("rpi", rpi);
			request.setAttribute("qpi", qpi);
			page = "WEB-INF/views/product/productDetail.jsp";
		} else {
			request.setAttribute("msg", "상품 상세보기에 실패앴습니다.");
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
