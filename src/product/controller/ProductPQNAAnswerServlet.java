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
@WebServlet("/answerPQNA.pr")
public class ProductPQNAAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductPQNAAnswerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pId = Integer.parseInt(request.getParameter("pId"));
		int pqbNo = Integer.parseInt(request.getParameter("pqbNo"));
		String pqbAnswer = request.getParameter("pqbAnswer");
		
		PQNA pqna = new PQNA();
		pqna.setPqbNo(pqbNo);
		pqna.setPqbAnswer(pqbAnswer);
		
		int result = new ProductService().answerPQNA(pqna);
		
		if(result > 0) {
			String page="detail.pr?pId=" + pId;
			response.sendRedirect(page);
		} else {
			request.setAttribute("msg", "문의글 답변 등록에 실패앴습니다.");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
