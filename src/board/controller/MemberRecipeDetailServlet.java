package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Attachment;
import board.model.vo.Board;
import board.model.vo.Reply;

/**
 * Servlet implementation class MemberRecipeDetailServlet
 */
@WebServlet("/detail.memberR")
public class MemberRecipeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberRecipeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bId = Integer.parseInt(request.getParameter("bId"));
		
		BoardService service = new BoardService();
		
		Board board = service.selectBoard(bId);
		
		ArrayList<Reply> list = new BoardService().selectReplyList(bId);
		
		ArrayList<Attachment> fileList = service.selectThumbnail(bId);
		System.out.println("보드" + board);
		System.out.println("파일리스트" + fileList);
		System.out.println("댓글 리스트 찍어봐" + list);
		
		String page = null;
		if(fileList != null) {
			request.setAttribute("board", board);
			request.setAttribute("fileList", fileList);
			request.setAttribute("list", list);
			page="WEB-INF/views/board/memberRecipeDetail.jsp";
		} else {
			request.setAttribute("msg", "회원레시피 상세조회에 실패하였습니다.");
			page="WEB-INF/views/common/errorPage.jsp";
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
