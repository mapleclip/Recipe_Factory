package admin.controller;

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

import admin.model.service.AdminService;
import admin.model.vo.BAttachment;
import admin.model.vo.Banner;
import common.MyFileRenamePolicy;

/**
 * Servlet implementation class mainBannerUpdateServlet
 */
@WebServlet("/mainBannerUpdate.ad")
public class mainBannerUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mainBannerUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024 * 1024 * 10; // 10Mbyte로 전송 파일 용량 제한
			String root = request.getSession().getServletContext().getRealPath("/"); // webContent 경로 추출
			String savePath = root + "main_uploadFiles/";
			
			File f = new File(savePath);
			if(!f.exists()) { // f가 존재하지않으면
				f.mkdirs(); // 생성하게 함
			}
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// 이제 multiRequest로 접근 가능!
			int bId = Integer.parseInt(multiRequest.getParameter("bId"));
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
			String[] fId = multiRequest.getParameterValues("fId");
			String[] oldFiles = multiRequest.getParameterValues("oldFileName");
			
			for(int i = 0; i < fId.length; i++) {
				System.out.println("에프아이디 실행해!" + fId[i]);
			}
			
			ArrayList<String> saveFiles = new ArrayList<String>();		// 바뀐 파일의 이름을 저장할 ArrayList
			ArrayList<String> originFiles = new ArrayList<String>(); 	// 원본 파일의 이름을 저장할 ArrayList
			
			Enumeration<String> files = multiRequest.getFileNames(); // 폼에서 전송된 파일 리스트의 이름 반환
			while(files.hasMoreElements()) {
				String name = files.nextElement(); // 전송 순서 역순으로 가져옴 (4개 넣으면 4, 3, 2, 1)
				
				if(multiRequest.getFilesystemName(name) != null) { // MyRenamePolicy의 rename메소드에서 작성한 내용대로 rename이 된 파일이 null이 아니면
					saveFiles.add(multiRequest.getFilesystemName(name)); // 바뀐이름 가지고와
					originFiles.add(multiRequest.getOriginalFileName(name)); // 원래이름 가지고와
				}
			}
			
			Banner b = new Banner();
			b.setBannerId(bId);
			b.setBannerTitle(title);
			b.setBannerContent(content);
			
			ArrayList<BAttachment> fileList = new ArrayList<BAttachment>();
			int j = 0;			
			// for문 역순으로 돌리기
			for(int i = originFiles.size() - 1; i >= 0; i--) {
				BAttachment at = new BAttachment();
				at.setFileId(Integer.parseInt(fId[j++]));				
				at.setFilePath(savePath);
				at.setOriginName(originFiles.get(i));
				at.setChangeName(saveFiles.get(i));
				at.setBannerId(bId);
				
				fileList.add(at);
			}
			
			int result = new AdminService().updateMainBanner(b, oldFiles, fileList);
			
			
			if(result > 0) {
				for (int i = 0; i < oldFiles.length; i++) {
					File oldFile = new File(savePath + oldFiles[i]);
					oldFile.delete();
				}				
				response.sendRedirect("mainServlet");
			} else { // 없으면 저장된 사진 다 지워!
				for (int i = 0; i < saveFiles.size(); i++) {
					File failedFile = new File(savePath + saveFiles.get(i));
					failedFile.delete();
				}
				
				request.setAttribute("msg", "메인 배너 수정에 실패하였습니다.");
				request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
			}
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
