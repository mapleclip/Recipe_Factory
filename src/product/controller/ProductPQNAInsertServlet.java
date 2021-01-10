package product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import product.model.Service.ProductService;
import product.model.vo.PQNA;

/**
 * Servlet implementation class ProductPQNAServlet
 */
@WebServlet("/writePQNA.pr")
public class ProductPQNAInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductPQNAInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pId = request.getParameter("pId");
		String pqbTitle = request.getParameter("pqbTitle");
		String pqbContent = request.getParameter("pqbContent");
		String mId = ((Member)request.getSession().getAttribute("loginUser")).getMemberid();
		
		PQNA pqna = new PQNA();
		pqna.setpId(pId);
		pqna.setPqbTitle(pqbTitle);
		pqna.setPqbContent(pqbContent);
		pqna.setmId(mId);
		
		int result = new ProductService().insertPQNA(pqna);
		
		if(result > 0) {
			String page="detail.pr?pId=" + pId;
			response.sendRedirect(page);
		} else {
			request.setAttribute("msg", "문의글 등록에 실패앴습니다.");
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
