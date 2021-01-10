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
 * Servlet implementation class mianBannerInsertServlet
 */
@WebServlet("/mainBannerInsert.ad")
public class mianBannerInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mianBannerInsertServlet() {
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
			if(!f.exists()) { // f가 존재하지 않으면
				f.mkdirs(); // 걔 만들어!
			}
			
			// DefaultFileRenamePolicy : 같은 파일 명이 존재하는지 검사하고, 존재 할 경우 파일 명 뒤에 숫자를 붙여 구분
			// 		aaa.zip 	aaa1.zip	aaa2.zip
			//		MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy()); // 하지만 마지막 매개변수는 사용안할거야
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// 이제 multiRequest로 접근 가능!
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
			
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
			b.setBannerTitle(title);
			b.setBannerContent(content);
			
			ArrayList<BAttachment> fileList = new ArrayList<BAttachment>();
			// for문 역순으로 돌리기
			for(int i = originFiles.size() - 1; i >= 0; i--) {
				BAttachment at = new BAttachment();
				at.setFilePath(savePath);
				at.setOriginName(originFiles.get(i));
				at.setChangeName(saveFiles.get(i));
				
				fileList.add(at);
			}
			
			int result = new AdminService().insertMainBanner(b, fileList);
			
			if(result > 0) {
				response.sendRedirect("mainServlet");
			} else { // 없으면 저장된 사진 다 지워!
				for(int i = 0; i < saveFiles.size(); i++) {
					File failedFile = new File(savePath + saveFiles.get(i));
					failedFile.delete();
				}
				
				request.setAttribute("msg", "메인 배너 등록에 실패하였습니다.");
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
