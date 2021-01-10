package product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.Service.ProductService;
import product.model.vo.PAttachment;
import product.model.vo.Category;
import product.model.vo.Product;

/**
 * Servlet implementation class ProductUpdateFormServlet
 */
@WebServlet("/productUpdateForm.pr")
public class ProductUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpdateFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pId = request.getParameter("pId");
		
		ProductService service = new ProductService();
		
		Product product = service.selectProduct(pId);
		
		ArrayList<PAttachment> fileList = service.selectThumbnail(pId);
		
		ArrayList<Category> cList = new ProductService().selectCList();
		
		String page = null;
		if(fileList != null) {
			request.setAttribute("product", product);
			request.setAttribute("fileList", fileList);
			request.setAttribute("cList", cList);
			page = "WEB-INF/views/product/productUpdateForm.jsp";
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
