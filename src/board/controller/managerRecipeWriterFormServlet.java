//레펙 레시피 12.
package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.Service.ProductService;
import product.model.vo.Product;

/**
 * Servlet implementation class managerRecipeWriterFormServlet
 */
@WebServlet("/writManagerRecipeForm.th")
public class managerRecipeWriterFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public managerRecipeWriterFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService pservice = new ProductService();
				
		ArrayList<Product> product = pservice.SelectPList();
		
		
		//ProductService service = new ProductService();
		
		//Product product = service.selectProduct(pId);
		String page = null;
		if(product != null) {
			page = "WEB-INF/views/board/managerRecipeWriteForm.jsp";
			request.setAttribute("product", product);
		} else {
			page = "WEB-INF/views/common/errorPage/jsp";
			request.setAttribute("msg", "레시피 등록 페이지 로딩에 실패하였습니다.");
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
