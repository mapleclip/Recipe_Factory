package cart.model.service;

import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import cart.model.dao.CartDAO;
import cart.model.vo.Buy;
import cart.model.vo.Cart;

public class CartService {
	
	
	

	public ArrayList<Cart> selectList(String memberId) {
		
		Connection conn = getConnection();
		
		ArrayList<Cart> list  = new CartDAO().selectList(conn, memberId);
		
		close(conn);
		
		return list;
	}

	public int deleteCart(String chk) {
		
		Connection conn = getConnection();
		
		int result = new CartDAO().deleteCart(conn, chk);
		
		close(conn);
		
		return result;
	}


	public int modifyCart(Cart cart) {
		
		Connection conn = getConnection();
		
		int result = new CartDAO().modifyCart(conn, cart);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public Cart selectCart(int cart_no) {
		
		Connection conn = getConnection();
		
		Cart cart = new CartDAO().selectCart(conn, cart_no);
		
		close(conn);
		
		return cart;
	}


	public ArrayList<Cart> selectProCartList(String chk) {
		
		Connection conn = getConnection();
		
		ArrayList<Cart> list = new CartDAO().selectProCartList(conn, chk);
		
		close(conn);
		
		return list;
	}

	public int insertBuy(Buy b) {
		
		Connection conn = getConnection();
		
		int result = new CartDAO().insertBuy(conn, b);

		
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}

	public int UpdateStatus(String chk) {
		
		Connection conn = getConnection();
		
		int result = new CartDAO().UpdateStatus(conn,chk);		
		
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}

	public ArrayList<Buy> selectBuy(String memberId) {
		
		Connection conn = getConnection();
		ArrayList<Buy> list = new CartDAO().selectBuy(conn, memberId);
		close(conn);
		
		return list;
	}


	
	
}
