package product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.Service.ProductService;
import product.model.vo.Category;

/**
 * Servlet implementation class ThumbnailWriteFormServlet
 */
@WebServlet("/writeProductForm.pr")
public class ProductWriteFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductWriteFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Category> cList = new ProductService().selectCList();
		
		String page = null;
		if(cList != null) {
			page = "WEB-INF/views/product/productWriteForm.jsp";
			request.setAttribute("cList", cList);
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
