package board.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import board.model.dao.ManagerRecipeDAO;
import board.model.vo.MRAttachment;
import board.model.vo.ManagerRecipe;
import board.model.vo.PageInfo;

public class ManagerRecipeService {
	public int getListCount() {
		Connection conn = getConnection();
		
		int result = new ManagerRecipeDAO().getListCount(conn);
		
		close(conn);
		
		return result;
	}

	public ArrayList<ManagerRecipe> selectList(PageInfo pi) {
		Connection conn = getConnection();
		ArrayList<ManagerRecipe> list = new ManagerRecipeDAO().selectList(conn, pi);
		close(conn);
		
		return list;
	}

	//레펙 레시피 4.
	//Attachment도 받아올 수 있게 <>지움
	public ArrayList selectTList(int i) {
		Connection conn = getConnection();
		
		ArrayList list = null;
		
		ManagerRecipeDAO dao = new ManagerRecipeDAO();
		
		if(i == 1) {
			list = dao.selectMBList(conn);
		} else {
			list = dao.selectMFList(conn);
		}
		
		close(conn);
		
		return list;
	}
	
	public ManagerRecipe selectManagerRecipe(int b) {
		Connection conn = getConnection();
		
		ManagerRecipeDAO dao = new ManagerRecipeDAO();
		
		int result = dao.updateCount(conn, b);
		
		ManagerRecipe mr = null;
		
		if(result > 0) {
			mr = new ManagerRecipeDAO().selectManagerRecipe(conn, b);
			
			if(mr != null) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return mr;
	}


	//레펙 레시피11.
	public ArrayList<MRAttachment> selectThumbnail(int bId) {
		Connection conn = getConnection();
		
		int result = new ManagerRecipeDAO().updateCount(conn, bId);
		
		ArrayList<MRAttachment> list = null;
		if(result > 0) {
			list = new ManagerRecipeDAO().selectThumbnail(conn, bId);
			
			//레펙 레시피 13.
			if(list != null) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return list;
	}

	//레펙 레시피 15.
	public int insertThumbnail(ManagerRecipe b, ArrayList<MRAttachment> fileList) {
		Connection conn = getConnection();
		
		ManagerRecipeDAO dao = new ManagerRecipeDAO();
		
		int result1 = dao.insertManageRecipeBoard(conn, b);
		int result2 = dao.insertMRAttachement(conn, fileList);
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1;
	}

	public int updateManagerRecipe(ManagerRecipe mr, ArrayList<MRAttachment> fileList) {
		Connection conn = getConnection();
		
		ManagerRecipeDAO dao = new ManagerRecipeDAO();
		
		int result1 = dao.updateManageRecipeBoard(conn, mr);
		int result2 = dao.updateMRAttachement(conn, fileList);
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1;
	}

	public int updateManagerRecipe(ManagerRecipe managerRecipe, String[] oldFiles, ArrayList<MRAttachment> fileList) {
		Connection conn = getConnection();
		
		ManagerRecipeDAO dao = new ManagerRecipeDAO();
		
		int result1 = dao.updateBoard(conn, managerRecipe);
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

	public int deleteManagerRecipe(int bId) {
		Connection conn = getConnection();
		
		int result = new ManagerRecipeDAO().deleteManagerRecipe(conn, bId);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public ArrayList selectTTList(int i) {
		Connection conn = getConnection();
		
		ArrayList list = null;
		
		ManagerRecipeDAO dao = new ManagerRecipeDAO();
		
		if(i == 1) {
			list = dao.selectTMBList(conn);
		} else {
			list = dao.selectTMFList(conn);
		}
		
		close(conn);
		
		return list;
	}

	

	
	
}
