//레펙 레시피 10.
package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import board.model.service.*;
import board.model.vo.MRAttachment;
import board.model.vo.ManagerRecipe;
import product.model.Service.ProductService;
import product.model.vo.PAttachment;
import product.model.vo.Product;

/**
 * Servlet implementation class managerRecipeDetailServlet
 */
@WebServlet("/detail.MR")
public class managerRecipeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public managerRecipeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bId = Integer.parseInt(request.getParameter("bId"));
		String pId = request.getParameter("pId");
		
		System.out.println("pId"+pId);
						
		ManagerRecipeService service = new ManagerRecipeService();
		ProductService pservice = new ProductService();
		
		//해당 게시판 정보 조회
		ManagerRecipe managerRecipe = service.selectManagerRecipe(bId);
			
		//해당 게시판의 사진 리스트 조회
		ArrayList<MRAttachment> fileList = service.selectThumbnail(bId);
		
		//재료 리스트 가져오기
		ArrayList<Product> pMRList = pservice.selectPMRList(pId);
		
		//재료 파일 리스트 가져오기
		ArrayList<PAttachment> aMRList = pservice.selectAMRList(pId);
		
		String page = null;
		if(fileList != null) {
			request.setAttribute("ManagerRecipe", managerRecipe);
			request.setAttribute("fileList", fileList);
			request.setAttribute("pMRList", pMRList);
			request.setAttribute("aMRList", aMRList);
			page = "WEB-INF/views/board/managerRecipedetail.jsp";
		} else {
			request.setAttribute("msg", "레펙 레시피 상세보기에 실패하였습니다.");
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
