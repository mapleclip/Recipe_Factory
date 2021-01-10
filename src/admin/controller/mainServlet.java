package admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import admin.model.vo.BAttachment;
import admin.model.vo.Banner;
import board.model.service.BoardService;
import board.model.service.ManagerRecipeService;
import board.model.vo.Attachment;
import board.model.vo.Board;
import board.model.vo.MRAttachment;
import board.model.vo.ManagerRecipe;
import product.model.Service.ProductService;
import product.model.vo.PAttachment;
import product.model.vo.Product;

/**
 * Servlet implementation class mainServlet
 */
@WebServlet("/mainServlet")
public class mainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 배너 데이터 가져오기
		AdminService service = new AdminService();
		ArrayList<Banner> bList = service.selectTTlist(1); // 게시판 리스트 가져오기
		ArrayList<BAttachment> fList = service.selectTTlist(2); // 파일 리스트 가져오기
		
		// 멤버레시피 데이터 가져오기
		BoardService memService = new BoardService();
		ArrayList<Board> btList = memService.selectTTlist(1);
		ArrayList<Attachment> ftList = memService.selectTTlist(2);
		
		// 상품데이터 가져오기
		ProductService pService = new ProductService();
		ArrayList<Product> ptList = pService.selectTTlist(1);
		ArrayList<PAttachment> pftList = pService.selectTTlist(2);
		
		// 관리자레시피 데이터 가져오기
		ManagerRecipeService manaService = new ManagerRecipeService();
		ArrayList<ManagerRecipe> manabtList = manaService.selectTTList(1);
		ArrayList<MRAttachment> manaftList = manaService.selectTTList(2);
		
		String page = null;
		if(bList != null && fList != null) {
			request.setAttribute("bList", bList);
			request.setAttribute("fList", fList);
			request.setAttribute("btList", btList);
			request.setAttribute("ftList", ftList);
			request.setAttribute("ptList", ptList);
			request.setAttribute("pftList", pftList);
			request.setAttribute("manabtList", manabtList);
			request.setAttribute("manaftList", manaftList);
			page = "WEB-INF/views/common/main.jsp";
		} else {
			request.setAttribute("msg", "메인페이지 조회에 실패하였습니다.");
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
