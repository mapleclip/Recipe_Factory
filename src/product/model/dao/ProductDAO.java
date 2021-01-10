package product.model.dao;

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

import cart.model.vo.Cart;
import product.model.vo.PAttachment;
import product.model.vo.Category;
import product.model.vo.PQNA;
import product.model.vo.PageInfo;
import product.model.vo.Product;
import product.model.vo.Review;

public class ProductDAO {
	private Properties prop = new  Properties();
	
	public ProductDAO() {
		String fileName = ProductDAO.class.getResource("/sql/product/product-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		};
	}

	public int getListCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		int listCount = 0;
		
		String query = prop.getProperty("getListCount");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}

	public ArrayList<Product> selectList(Connection conn, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		String query = prop.getProperty("selectList");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product(rset.getString("P_ID"),
						rset.getString("P_NAME"),
						rset.getString("P_PRICE"),
						rset.getString("P_CATEGORY"),
						rset.getString("P_DESCRIPTION"),
						rset.getString("P_ORIGIN"),
						rset.getString("P_CAPACITY"));
				
				list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<PAttachment> selectList(Connection conn, ArrayList<Product> list) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PAttachment> fList = new ArrayList<PAttachment>();
		
		String query = prop.getProperty("selectFList");
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				PAttachment a = new PAttachment(rset.getInt("FILE_ID"),
						rset.getString("P_ID"),
						rset.getString("ORIGIN_NAME"),
						rset.getString("CHANGE_NAME"),
						rset.getString("FILE_PATH"),
						rset.getInt("FILE_LEVEL"));
				
				fList.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return fList;
	}
	
	public ArrayList<Category> selectCList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Category> cList = new ArrayList<Category>();
		
		String query = prop.getProperty("selectCList");
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Category c = new Category(rset.getInt("CATE_ID"),
						rset.getString("CATE_NAME"));
				
				cList.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return cList;
	}

	public int insertProduct(Connection conn, Product p) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertProduct");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, p.getpName());
			pstmt.setString(2, p.getpPrice());
			pstmt.setString(3, p.getpCategory());
			pstmt.setString(4, p.getpDescription());
			pstmt.setString(5, p.getpOrigin());
			pstmt.setString(6, p.getpCapacity());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertAttachment(Connection conn, ArrayList<PAttachment> fileList) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertAttachment");
		
		try {
			for(int i = 0; i < fileList.size(); i++) {
				PAttachment at = fileList.get(i);
				
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setString(3, at.getFilePath());
				pstmt.setInt(4, at.getFileLevel());
				
				result += pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Product selectProduct(Connection conn, String pId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Product product = null;
		
		String query = prop.getProperty("selectProduct");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pId);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				product = new Product(rset.getString("P_ID"),
						rset.getString("P_NAME"),
						rset.getString("P_PRICE"),
						rset.getString("CATE_NAME"),
						rset.getString("P_DESCRIPTION"),
						rset.getString("P_ORIGIN"),
						rset.getString("P_CAPACITY"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return product;
	}

	public ArrayList<PAttachment> selectThumbnail(Connection conn, String pId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PAttachment> list = new ArrayList<PAttachment>();
		
		String query = prop.getProperty("selectThumbnail");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				PAttachment at = new PAttachment();
				at.setFileId(rset.getInt("FILE_ID"));
				at.setpId(rset.getString("P_ID"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setChangeName(rset.getString("CHANGE_NAME"));
				at.setFilePath(rset.getString("FILE_PATH"));
				
				list.add(at);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Review> selectRList(Connection conn, String pId, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Review> list = new ArrayList<Review>();
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		String query = prop.getProperty("selectRList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Review r = new Review(rset.getInt("REVIEW_NO"),
						rset.getString("MEMBERID"),
						rset.getString("P_ID"),
						rset.getString("REVIEW_TITLE"),
						rset.getString("REVIEW_CONTENT"),
						rset.getString("REVIEW_FILE_NAME"),
						rset.getDate("REVIEW_DATE"));
				
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int updateProduct(Connection conn, Product p) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateProduct");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, p.getpName());
			pstmt.setString(2, p.getpPrice());
			pstmt.setString(3, p.getpCategory());
			pstmt.setString(4, p.getpDescription());
			pstmt.setString(5, p.getpOrigin());
			pstmt.setString(6, p.getpCapacity());
			pstmt.setString(7, p.getpId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateAttachment(Connection conn, ArrayList<PAttachment> fileList) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateAttachment");
		
		try {
			for(int i = 0; i < fileList.size(); i++) {
				PAttachment at = fileList.get(i);
				
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setString(3, at.getpId());
				pstmt.setInt(4, at.getFileId());
				
				result += pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int getRListCount(Connection conn, String pId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		
		String query = prop.getProperty("getRListCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}

	public Review selectReview(Connection conn, int rNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Review review = null;
		
		String query = prop.getProperty("selectReview");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				review = new Review(rset.getInt("REVIEW_NO"),
						rset.getString("MEMBERID"),
						rset.getString("P_ID"),
						rset.getString("REVIEW_TITLE"),
						rset.getString("REVIEW_CONTENT"),
						rset.getString("REVIEW_FILE_NAME"),
						rset.getDate("REVIEW_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return review;
	}

	public int getQListCount(Connection conn, String pId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		
		String query = prop.getProperty("getQListCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}

	public ArrayList<PQNA> selectQList(Connection conn, String pId, PageInfo qpi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PQNA> list = new ArrayList<PQNA>();
		
		int startRow = (qpi.getCurrentPage() - 1) * qpi.getBoardLimit() + 1;
		int endRow = startRow + qpi.getBoardLimit() - 1;
		String query = prop.getProperty("selectQList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				PQNA q = new PQNA(rset.getInt("PQB_NO"),
						rset.getString("P_ID"),
						rset.getString("MEMBERID"),
						rset.getString("PQB_TITLE"),
						rset.getString("PQB_CONTENT"),
						rset.getString("PQB_ANSWER"),
						rset.getDate("PQB_DATE"),
						rset.getDate("PQB_A_DATE"));
				
				list.add(q);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public PQNA selectPQNA(Connection conn, int pqbNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PQNA pqna = null;
		
		String query = prop.getProperty("selectPQNA");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pqbNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				pqna = new PQNA(rset.getInt("PQB_NO"),
						rset.getString("P_ID"),
						rset.getString("MEMBERID"),
						rset.getString("PQB_TITLE"),
						rset.getString("PQB_CONTENT"),
						rset.getString("PQB_ANSWER"),
						rset.getDate("PQB_DATE"),
						rset.getDate("PQB_A_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return pqna;
	}

	public int insertPQNA(Connection conn, PQNA pqna) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertPQNA");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pqna.getpId());
			pstmt.setString(2, pqna.getmId());
			pstmt.setString(3, pqna.getPqbTitle());
			pstmt.setString(4, pqna.getPqbContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertReview(Connection conn, Review review) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertReview");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, review.getmId());
			pstmt.setString(2, review.getpId());
			pstmt.setString(3, review.getrTitle());
			pstmt.setString(4, review.getrContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int answerPQNA(Connection conn, PQNA pqna) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("answerPQNA");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pqna.getPqbAnswer());
			pstmt.setInt(2, pqna.getPqbNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int addCart(Connection conn, Cart cart) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("addCart");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cart.getP_id());
			pstmt.setString(2, cart.getMember_id());
			pstmt.setString(3, cart.getP_name());
			pstmt.setInt(4, cart.getCart_price());
			pstmt.setInt(5, cart.getAmount());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}

		return result;
	}

	public ArrayList<Product> selectList(Connection conn, PageInfo pi, int cateId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		String query = prop.getProperty("selectListCategory");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cateId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product(rset.getString("P_ID"),
						rset.getString("P_NAME"),
						rset.getString("P_PRICE"),
						rset.getString("P_CATEGORY"),
						rset.getString("P_DESCRIPTION"),
						rset.getString("P_ORIGIN"),
						rset.getString("P_CAPACITY"));
				
				list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Product> selectSearchList(Connection conn, String searchContent) {
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      ArrayList<Product> plist = new ArrayList<Product>();
	      
	      String query = prop.getProperty("searchProduct");
	      
	      try {
	         pstmt = conn.prepareStatement(query);
	         pstmt.setString(1, "%" + searchContent + "%");
	         pstmt.setString(2, "%" + searchContent);
	         pstmt.setString(3, searchContent + "%");
	         
	         rset = pstmt.executeQuery();
	         
	         while(rset.next()) {
	            Product p = new Product(rset.getString("p_id"),
	                              rset.getString("p_name"),
	                              rset.getString("p_price"),
	                              rset.getString("p_category"),
	                              rset.getString("p_description"),
	                              rset.getString("p_origin"),
	                              rset.getString("p_capacity"));
	            plist.add(p);
	         }
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }finally {
	         close(rset);
	         close(pstmt);
	      }
	      return plist;
	   }
	
	public ArrayList<Product> selectPMBList(Connection conn, String pId) {
	      Statement stmt = null;
	      ResultSet rset = null;
	      ArrayList<Product> list = null;
	      
	      String query = prop.getProperty("selectPMBList");
	      query += "(" + pId + ")";
	            
	      try {
	         stmt = conn.createStatement();
	         
	         rset = stmt.executeQuery(query);
	         
	         
	         list = new ArrayList<Product>();
	                  
	         while(rset.next()) {
	            list.add(new Product(rset.getString("P_ID"),
	                                 rset.getString("P_NAME"),
	                                 rset.getString("P_PRICE"),
	                                 rset.getString("P_CATEGORY"),
	                                 rset.getString("P_DESCRIPTION"),
	                                 rset.getString("P_ORIGIN"),
	                                 rset.getString("P_CAPACITY")));
	            
	            
	         
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(rset);
	         close(stmt);
	      }
	      
	      
	      return list;
	   }
	
	public ArrayList<PAttachment> selectAMRList(Connection conn, String pId) {
	      Statement stmt = null;
	      ResultSet rset = null;
	      ArrayList<PAttachment> list = null;
	      
	      String query = prop.getProperty("selectAMRList");
	      query += "(" + pId + ")";
	      
	      System.out.println("쿼리"+query);
	            
	      try {
	         stmt = conn.createStatement();
	         
	         rset = stmt.executeQuery(query);
	         
	         list = new ArrayList<PAttachment>();
	         
	         while(rset.next()) {
	            list.add(new PAttachment(rset.getString("P_ID"),
	                              rset.getString("CHANGE_NAME")));
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(rset);
	         close(stmt);
	      }
	      
	      return list;
	   }
	   public ArrayList<Product> selectPList(Connection conn) {
		      Statement stmt = null;
		      ResultSet rset = null;
		      ArrayList<Product> list = new ArrayList<Product>();
		      
		      String query = prop.getProperty("selectPList");
		      
		      try {
		         stmt = conn.createStatement();
		         rset = stmt.executeQuery(query);
		         
		         while(rset.next()) {
		            Product p = new Product(rset.getString("P_ID"),
		                  rset.getString("P_NAME"),
		                  rset.getString("P_PRICE"),
		                  rset.getString("P_CATEGORY"),
		                  rset.getString("P_DESCRIPTION"),
		                  rset.getString("P_ORIGIN"),
		                  rset.getString("P_CAPACITY"));
		            
		            list.add(p);
		         }
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally {
		         close(rset);
		         close(stmt);
		      }
		      
		      return list;
	}
	   public Product selectProduct(Connection conn, int pId) {
		      PreparedStatement pstmt = null;
		      ResultSet rset = null;
		      Product product = null;
		      
		      String query = prop.getProperty("selectProduct");
		      
		      try {
		         pstmt = conn.prepareStatement(query);
		         pstmt.setInt(1, pId);
		         
		         rset = pstmt.executeQuery();
		         if(rset.next()) {
		            product = new Product(rset.getString("P_ID"),
		                  rset.getString("P_NAME"),
		                  rset.getString("P_PRICE"),
		                  rset.getString("CATE_NAME"),
		                  rset.getString("P_DESCRIPTION"),
		                  rset.getString("P_ORIGIN"),
		                  rset.getString("P_CAPACITY"));
		            
		         }
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally {
		         close(rset);
		         close(pstmt);
		      }
		      return product;
		   }   
	   public ArrayList selectPTlist(Connection conn) {
		      Statement stmt = null;
		      ResultSet rset = null;
		      ArrayList<Product> list = null;
		      
		      String query = prop.getProperty("selectPTlist");
		      
		      try {
		         stmt = conn.createStatement();
		         rset = stmt.executeQuery(query);
		         
		         list = new ArrayList<Product>();
		         while(rset.next()) {
		            list.add(new Product(rset.getString("P_ID"),
		                  rset.getString("P_NAME"),
		                  rset.getString("P_PRICE"),
		                  rset.getString("P_CATEGORY"),
		                  rset.getString("P_DESCRIPTION"),
		                  rset.getString("P_ORIGIN"),
		                  rset.getString("P_CAPACITY")));
		         }
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally {
		         close(rset);
		         close(stmt);
		      }
		      
		      return list;
		   } 
	   
	   
	   
	   public ArrayList selectPFTlist(Connection conn) {
		      Statement stmt = null;
		      ResultSet rset = null;
		      ArrayList<PAttachment> list = null;
		      
		      String query = prop.getProperty("selectPFTlist");
		      
		      try {
		         stmt = conn.createStatement(); 
		         rset = stmt.executeQuery(query);
		         
		         list = new ArrayList<PAttachment>();
		         while(rset.next()) {
		            list.add(new PAttachment(rset.getInt("file_id"),
		                              rset.getString("p_id"),
		                              rset.getString("origin_name"),
		                              rset.getString("change_name"),
		                              rset.getString("file_path"),
		                              rset.getInt("file_level")));
		         }
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally {
		         close(rset);
		         close(stmt);
		      }
		      
		      return list;
		   }
}
