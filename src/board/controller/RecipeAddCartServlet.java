package board.controller;

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
import product.model.vo.Product;

/**
 * Servlet implementation class RecipeAddCartServlet
 */
@WebServlet("/addRCard.ca")
public class RecipeAddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeAddCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pId=request.getParameter("pId");
		int amount=Integer.parseInt(request.getParameter("amount"));
		int price=Integer.parseInt(request.getParameter("price"));
		String pName=request.getParameter("pName");
		
		String[] pIds = pId.split(",");
		
		int result = 0;
		for(int i = 0; i < pIds.length; i++) {
			Product p = new ProductService().selectProduct(Integer.parseInt(pIds[i]));
			
			Cart cart = new Cart();
			cart.setP_id(p.getpId());
			cart.setMember_id(((Member)request.getSession().getAttribute("loginUser")).getMemberid());
			cart.setP_name(p.getpName());
			cart.setAmount(amount);
			cart.setCart_price(price);
			
			result = new ProductService().addCart(cart);
		}
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
