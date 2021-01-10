package cart.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.model.service.CartService;
import cart.model.vo.Cart;


/**
 * Servlet implementation class CartPurchaseServlet
 */
@WebServlet("/purchaseCart.me")
public class CartPurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartPurchaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String[] chkArr = request.getParameterValues("chk");
		String chkList ="";
		for(int i=0; i< chkArr.length; i++) {
			if(i == chkArr.length) {
			} else {
				chkList += chkArr[i]+",";
			}
		}


		String chk = chkList.substring(0, chkList.length()-1);
		
		
		ArrayList<Cart> list = new CartService().selectProCartList(chk);
		// 나 또 뻘 짓 했 니 ?  아 놔.. 뻘 짓 아 니 다 ^0^
		
		request.setAttribute("chk", chk);
		request.setAttribute("list", list);
		request.getRequestDispatcher("WEB-INF/views/cart/cartPurchase.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
