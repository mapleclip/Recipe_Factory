package board.controller;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet
.http.HttpServletResponse;

import board.model.service.ManagerRecipeService;
import board.model.vo.MRAttachment;
import board.model.vo.ManagerRecipe;
import product.model.Service.ProductService;
import product.model.vo.Product;

/**
 * Servlet implementation class managerRecipeUpdateFormServlet
 */
@WebServlet("/managerRecipeUpdateForm.bo")
public class managerRecipeUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public managerRecipeUpdateFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bId = Integer.parseInt(request.getParameter("bId"));
		
		ProductService pservice = new ProductService();
		
		ArrayList<Product> product = pservice.SelectPList();
		
		
		ManagerRecipeService service = new ManagerRecipeService();
		ManagerRecipe managerRecipe = service.selectManagerRecipe(bId);
		
		ArrayList<MRAttachment> fileList = service.selectThumbnail(bId);
				
		request.setAttribute("managerRecipe", managerRecipe);
		request.setAttribute("fileList", fileList);
		
		String page = null;
		if(managerRecipe != null) {
			page = "WEB-INF/views/board/managerRecipeUpdateForm.jsp";
			request.setAttribute("managerRecipe", managerRecipe);
			request.setAttribute("product", product);
		} else {
			page = "WEB-INF/views/common/errorPage/jsp";
			request.setAttribute("msg", "상품 등록 페이지 로딩에 실패하였습니다.");
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
