package product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.Service.ProductService;
import product.model.vo.PQNA;

/**
 * Servlet implementation class ProductPQNAAnswerServlet
 */
@WebServlet("/answerPQNAForm.pr")
public class ProductPQNAAnswerFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductPQNAAnswerFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pqbNo = Integer.parseInt(request.getParameter("pqbNo"));
		
		PQNA pqna = new ProductService().selectPQNA(pqbNo);
		
		String page = null;
		if(pqna != null) {
			request.setAttribute("pqna", pqna);
			page = "WEB-INF/views/product/answerPQNAForm.jsp";
		} else {
			request.setAttribute("msg", "답변 페이지 불러오기에 실패앴습니다.");
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
