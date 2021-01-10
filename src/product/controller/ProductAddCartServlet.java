package product.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.model.vo.Cart;
import member.model.vo.Member;
import product.model.Service.ProductService;

/**
 * Servlet implementation class CartAddProduct
 */
@WebServlet("/addCard.pr")
public class ProductAddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductAddCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pId = request.getParameter("pId");
		String memberid = ((Member)request.getSession().getAttribute("loginUser")).getMemberid();
		String pName = request.getParameter("pName");
		int price = Integer.parseInt(request.getParameter("price"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		
		Cart cart = new Cart();
		
		cart.setP_id(pId);
		cart.setMember_id(memberid);
		cart.setP_name(pName);
		cart.setCart_price(price);
		cart.setAmount(amount);
		
		int result = new ProductService().addCart(cart);
		response.setContentType("application/json; charset=UTF-8");
		
		if(result > 0) {
			PrintWriter out = response.getWriter();
			out.println(true);
			out.flush();
			out.close();
		} else {
			PrintWriter out = response.getWriter();
			out.println(false);
			out.flush();
			out.close();
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
