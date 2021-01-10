package cart.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.model.service.CartService;
import cart.model.vo.Cart;

/**
 * Servlet implementation class CartModifyServlet
 */
@WebServlet("/modify.ca")
public class CartModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cart_no = Integer.parseInt(request.getParameter("cart_no"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		
		Cart cart = new Cart();
		cart.setCart_no(cart_no);
		cart.setAmount(amount);
		
		System.out.println("장바구니 번호" + cart_no + " , " + "장바구니 수량" + amount);
		
		
		int result = new CartService().modifyCart(cart);

		if(result > 0) { 
			response.sendRedirect("cart.me"); 
		} else {
			request.setAttribute("msg", "장바구니 수량 변경에 실패하였습니다.");
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
