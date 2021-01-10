package cart.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.model.service.CartService;
import cart.model.vo.Buy;

/**
 * Servlet implementation class CartBuyServlet
 */
@WebServlet("/importBuy")
public class CartBuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartBuyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getParameter("UTF-8");
		
		//상품관련
		
		String p_id = request.getParameter("p_id");
		String p_name = request.getParameter("p_name");
		int amount = Integer.parseInt(request.getParameter("amount"));
		
		int totalPrice = Integer.parseInt(request.getParameter("total"));
				
		//주문자
		String orderName = request.getParameter("orderName");
		String memberId = request.getParameter("memberId");
		String email = request.getParameter("email");
		String orderPhone = request.getParameter("orderPhone");
		String myPostal = request.getParameter("joinPostal");
		String myAddress1 = request.getParameter("joinAddress1");
		String myAddress2 = request.getParameter("joinAddress2");
		String orderAddress = myPostal + myAddress1 + myAddress2;		
		String orderRequire = request.getParameter("joinAddress2");
		
		
		String[] chkArr = request.getParameterValues("chk");
		String chkList ="";
		for(int i=0; i< chkArr.length; i++) {
			if(i == chkArr.length) {
			} else {
				chkList += chkArr[i]+",";
			}
		}



		Buy b = new Buy(memberId, p_id, p_name, amount, orderName, orderAddress, orderPhone, orderRequire, totalPrice);
		int result = new CartService().insertBuy(b);
		
		String chk = chkList.substring(0, chkList.length()-1);
		int result2 = new CartService().UpdateStatus(chk);
		
		
		String page = null;
		
		if(result >0 && result2>0) {
			request.setAttribute("orderName", orderName);
			request.setAttribute("email", email);
			request.setAttribute("orderPhone", orderPhone);
			request.setAttribute("orderAddress", orderAddress);
			request.setAttribute("totalPrice", totalPrice);
			page = "WEB-INF/views/cart/importBuy.jsp";

		} else {
			page = "WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("msg", "구매에 실패하였습니다. ");
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
