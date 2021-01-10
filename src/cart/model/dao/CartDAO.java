package cart.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import cart.model.vo.Buy;
import cart.model.vo.Cart;

public class CartDAO {
	
	private Properties prop = new Properties();
	
	public CartDAO() {
		String fileName = CartDAO.class.getResource("/sql/cart/cart-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	


	public ArrayList<Cart> selectList(Connection conn, String memberId) {
		
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		ArrayList<Cart> list = new ArrayList<Cart>();
		
		String query = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,memberId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Cart c = new Cart(rset.getInt("CART_NO"),
								  rset.getString("P_ID"),
								  rset.getString("MEMBERID"),
								  rset.getString("PNAME"),
								  rset.getInt("CART_PPRICE"),
								  rset.getInt("AMOUNT"),
								  rset.getString("STATUS"));
				list.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int deleteCart(Connection conn, String chk) {
		
		Statement stmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteCart");
		query += "(" + chk + ")";
		
		try {
//			pstmt = conn.prepareStatement(query);
			stmt = conn.createStatement();
//			pstmt.setString(1, chk);
			
//			result = pstmt.executeUpdate();
			result = stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		
		return result;
	}



	public int modifyCart(Connection conn, Cart cart) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("modifyCart");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cart.getAmount());
			pstmt.setInt(2, cart.getCart_no());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}

		return result;
	}




	public Cart selectCart(Connection conn, int cart_no) {
		
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Cart c = null;
		
		String query = prop.getProperty("selectCartDetail");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cart_no);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				 c = new Cart(rset.getInt("CART_NO"),
							  rset.getString("P_ID"),
							  rset.getString("MEMBERID"),
							  rset.getString("PNAME"),
							  rset.getInt("CART_PPRICE"),
							  rset.getInt("AMOUNT"),
							  rset.getString("STATUS"));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return c;
	}


	public ArrayList<Cart> selectProCartList(Connection conn, String chk) {
		
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Cart> list = new ArrayList<Cart>();
		
		String query = prop.getProperty("selectProCart");
		query += "(" + chk + ")";
		
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);

			while(rset.next()) {
				Cart c = new Cart(rset.getInt("CART_NO"),
								  rset.getString("P_ID"),
								  rset.getString("MEMBERID"),
								  rset.getString("PNAME"),
								  rset.getInt("CART_PPRICE"),
								  rset.getInt("AMOUNT"),
								  rset.getString("STATUS"));
				list.add(c);
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		
		return list;
	}




	public int insertBuy(Connection conn, Buy b) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertBuy");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getMemberid());
			pstmt.setString(2, b.getP_id());
			pstmt.setString(3, b.getP_name());
			pstmt.setInt(4, b.getAmount());
			pstmt.setString(5, b.getOrderName());
			pstmt.setString(6, b.getOrderAddress());
			pstmt.setString(7, b.getOrderPhone());
			pstmt.setString(8, b.getOrderRequire());
			pstmt.setInt(9, b.getTotalPrice());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}




	public int UpdateStatus(Connection conn, String chk) {

		Statement stmt = null;
		int result = 0;
		
		
		String query = prop.getProperty("UpdateStatus");
		query += "(" + chk + ")";
		
		try {

			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return result;
	}




	public ArrayList<Buy> selectBuy(Connection conn, String memberId) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Buy> list = new ArrayList<Buy>();
		
		String query = prop.getProperty("selectBuy");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Buy b = new Buy(rset.getInt("ORDERNO"),
								rset.getString("MEMBERID"),
								rset.getString("P_ID"),
								rset.getString("P_NAME"),
								rset.getInt("AMOUNT"),
								rset.getString("ORDERNAME"),
								rset.getString("ORDERADDRESS"),
								rset.getString("ORDERPHONE"),
								rset.getString("ORDERREQUIRE"),
								rset.getInt("TOTALPRICE"),
								rset.getDate("ORDERDATE"),
								rset.getString("BUY_STATUS"),
								rset.getString("DELI_STATUS"));
				list.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}





}
