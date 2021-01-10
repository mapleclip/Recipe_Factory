package board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import board.model.service.BoardService;
import board.model.vo.Attachment;
import board.model.vo.Board;
import common.MyFileRenamePolicy;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberRecipeUpdateServlet
 */
@WebServlet("/update.memberR")
public class MemberRecipeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberRecipeUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024 * 1024 * 10;
			String root = request.getSession().getServletContext().getRealPath("/");
			String savePath = root + "thumbnail_uploadFiles/";

			File f = new File(savePath);
			if (!f.exists()) {
				f.mkdir();
			}

			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());

			// 이제 multiRequest로 접근 가능!
			int bId = Integer.parseInt(multiRequest.getParameter("bId"));
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
			String bWriter = ((Member) request.getSession().getAttribute("loginUser")).getMemberid();
			String[] fId = multiRequest.getParameterValues("fId");
			String[] oldFiles = multiRequest.getParameterValues("oldFileName");

			ArrayList<String> saveFiles = new ArrayList<String>();// 바뀐 파일의 이름
			ArrayList<String> originFiles = new ArrayList<String>();// 원래 파일의 이름

			Enumeration<String> files = multiRequest.getFileNames();// 폼에서 전송된 파일 리스트의 이름 반환
			while (files.hasMoreElements()) {
				String name = files.nextElement();

				if (multiRequest.getFilesystemName(name) != null) {
					saveFiles.add(multiRequest.getFilesystemName(name));
					originFiles.add(multiRequest.getOriginalFileName(name));
				}
			}
			Board board = new Board();
			board.setBoardId(bId);
			board.setBoardTitle(title);
			board.setBoardContent(content);
			board.setBoardWriter(bWriter);
			board.setBoardType(2);

			ArrayList<Attachment> fileList = new ArrayList<Attachment>();
			// for문 역순으로 돌리기
			int j = 0;
			for (int i = originFiles.size() - 1; i >= 0; i--) {
				Attachment at = new Attachment();
				at.setBoardId(bId);
				at.setFileId(Integer.parseInt(fId[j++]));
				at.setFilePath(savePath);
				at.setOriginName(originFiles.get(i));
				at.setChangeName(saveFiles.get(i));

				// 섬네일 구분하기
				if (i == originFiles.size() - 1) {
					at.setFileLevel(0);
				} else {
					at.setFileLevel(1);
				}

				fileList.add(at);
			}

			int result = new BoardService().updateMemberRecipe(board, oldFiles, fileList);
			System.out.println("" + result);
			
			if (result > 0) {
				for (int i = 0; i < oldFiles.length; i++) {
					File oldFile = new File(savePath + oldFiles[i]);
					oldFile.delete();
				}
				response.sendRedirect("list.memberR");
			} else {
				for (int i = 0; i < saveFiles.size(); i++) {
					File failedFile = new File(savePath + saveFiles.get(i));
					failedFile.delete();
				}

				request.setAttribute("msg", "게시글 수정에 실패하였습니다.");
				request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
