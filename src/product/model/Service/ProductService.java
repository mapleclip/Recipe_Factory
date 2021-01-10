package product.model.Service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import cart.model.vo.Cart;
import product.model.dao.ProductDAO;
import product.model.vo.PAttachment;
import product.model.vo.Category;
import product.model.vo.PQNA;
import product.model.vo.PageInfo;
import product.model.vo.Product;
import product.model.vo.Review;

public class ProductService {

	public int getListCount() {
		Connection conn = getConnection();
		
		int listCount = new ProductDAO().getListCount(conn);
		
		close(conn);
		
		return listCount;
	}

	public ArrayList<Product> selectList(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Product> list = new ProductDAO().selectList(conn, pi);
		
		close(conn);
		
		return list;
	}

	public ArrayList<PAttachment> selectFList(ArrayList<Product> list) {
		Connection conn = getConnection();
		
		ArrayList<PAttachment> fList = new ProductDAO().selectList(conn, list);
		
		close(conn);
		
		return fList;
	}
	
	public ArrayList<Category> selectCList() {
		Connection conn = getConnection();
		
		ArrayList<Category> cList = new ProductDAO().selectCList(conn);
		
		close(conn);
		
		return cList;
	}

	public int insertProduct(Product p, ArrayList<PAttachment> fileList) {
		Connection conn = getConnection();
		
		ProductDAO dao = new ProductDAO();
		
		int result1 = dao.insertProduct(conn, p);
		int result2 = dao.insertAttachment(conn, fileList);
		int result3 = 0;
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
			result3 = 1;
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result3;
	}

	public Product selectProduct(String pId) {
		Connection conn = getConnection();
		
		Product board = new ProductDAO().selectProduct(conn, pId);
		
		close(conn);
		
		return board;
	}

	public ArrayList<PAttachment> selectThumbnail(String pId) {
		Connection conn = getConnection();
		
		ArrayList<PAttachment> list = new ProductDAO().selectThumbnail(conn, pId);
		
		close(conn);
		
		return list;
	}

	public ArrayList<Review> selectRList(String pId, PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Review> list = new ProductDAO().selectRList(conn, pId, pi);
		
		close(conn);
		
		return list;
	}

	public int updateProduct(Product p, ArrayList<PAttachment> fileList) {
		Connection conn = getConnection();
		
		ProductDAO dao = new ProductDAO();
		
		int result1 = dao.updateProduct(conn, p);
		int result2 = dao.updateAttachment(conn, fileList);
		int result3 = 0;
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
			result3 = 1;
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result3;
	}

	public int getRListCount(String pId) {
		Connection conn = getConnection();
		
		int listCount = new ProductDAO().getRListCount(conn, pId);
		
		close(conn);
		
		return listCount;
	}

	public Review selectReview(int rNo) {
		Connection conn = getConnection();
		
		Review review = new ProductDAO().selectReview(conn, rNo);
		
		close(conn);
		
		return review;
	}

	public int getQListCount(String pId) {
		Connection conn = getConnection();
		
		int listCount = new ProductDAO().getQListCount(conn, pId);
		
		close(conn);
		
		return listCount;
	}

	public ArrayList<PQNA> selectQList(String pId, PageInfo qpi) {
		Connection conn = getConnection();
		
		ArrayList<PQNA> list = new ProductDAO().selectQList(conn, pId, qpi);
		
		close(conn);
		
		return list;
	}

	public PQNA selectPQNA(int pqbNo) {
		Connection conn = getConnection();
		
		PQNA pqna = new ProductDAO().selectPQNA(conn, pqbNo);
		
		close(conn);
		
		return pqna;
	}

	public int insertPQNA(PQNA pqna) {
		Connection conn = getConnection();
		
		int result = new ProductDAO().insertPQNA(conn, pqna);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int insertReview(Review review) {
		Connection conn = getConnection();
		
		int result = new ProductDAO().insertReview(conn, review);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int answerPQNA(PQNA pqna) {
		Connection conn = getConnection();
		
		int result = new ProductDAO().answerPQNA(conn, pqna);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int addCart(Cart cart) {
		Connection conn = getConnection();
		
		int result = new ProductDAO().addCart(conn, cart);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public ArrayList<Product> selectList(PageInfo pi, int cateId) {
		Connection conn = getConnection();
		
		ArrayList<Product> list = new ProductDAO().selectList(conn, pi, cateId);
		
		close(conn);
		
		return list;
	}

	public ArrayList<Product> selectSearchList(String searchContent) {
	      Connection conn = getConnection();
	      
	      ArrayList<Product> plist = new ProductDAO().selectSearchList(conn, searchContent);
	      
	      close(conn);
	      return plist;
	   }
	public ArrayList<Product> selectPMRList(String pId) {
	      Connection conn = getConnection();
	      
	      ArrayList list = null;
	      
	      ProductDAO dao = new ProductDAO();
	      
	      list = dao.selectPMBList(conn, pId);
	      
	      close(conn);
	      
	      return list;
	   }
	public ArrayList<PAttachment> selectAMRList(String pId) {
	      Connection conn = getConnection();
	      
	      ArrayList list = null;
	      
	      ProductDAO dao = new ProductDAO();
	      
	      list = dao.selectAMRList(conn, pId);
	      
	      close(conn);
	      
	      return list;
	}
	
	public ArrayList<Product> SelectPList() {
	      Connection conn = getConnection();
	      ArrayList<Product> list = new ProductDAO().selectPList(conn);
	      close(conn);
	      
	      return list;
	}

	public Product selectProduct(int pId) {
	      Connection conn = getConnection();
	      
	      Product board = new ProductDAO().selectProduct(conn, pId);
	      
	      close(conn);
	      
	      return board;
	   }
	
	public ArrayList selectTTlist(int i) {
	      Connection conn = getConnection();
	      
	      ArrayList list = null;
	      
	      ProductDAO dao = new ProductDAO();
	      
	      if(i == 1) {
	         list = dao.selectPTlist(conn);
	      } else {
	         list = dao.selectPFTlist(conn);
	      }
	      close(conn);
	      
	      return list;
	   }
}
