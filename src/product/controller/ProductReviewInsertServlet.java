package product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import product.model.Service.ProductService;
import product.model.vo.Review;

/**
 * Servlet implementation class ProductPQNAServlet
 */
@WebServlet("/writeReview.pr")
public class ProductReviewInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductReviewInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pId = request.getParameter("pId");
		String rTitle = request.getParameter("rTitle");
		String rContent = request.getParameter("rContent");
		String mId = ((Member)request.getSession().getAttribute("loginUser")).getMemberid();
		
		Review review = new Review();
		review.setpId(pId);
		review.setmId(mId);
		review.setrTitle(rTitle);
		review.setrContent(rContent);
		
		int result = new ProductService().insertReview(review);
		
		if(result > 0) {
			String page="detail.pr?pId=" + pId;
			response.sendRedirect(page);
		} else {
			request.setAttribute("msg", "리뷰 등록에 실패앴습니다.");
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
