package product.controller;

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

import common.MyFileRenamePolicy;
import product.model.Service.ProductService;
import product.model.vo.PAttachment;
import product.model.vo.Product;

/**
 * Servlet implementation class ThumbnailInsertServlet
 */
@WebServlet("/insert.pr")
public class ProductInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
		//encType이 multipart/form-date이기 때문에 requset.getParameter로 값을 받을 수 없음
		//cos.jar 를 추가하여 진행 <== 파일도 받고 다른 값들도 받아주는 역할
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024 * 1024 * 10;
			String root = request.getSession().getServletContext().getRealPath("/");
			String savePath = root + "thumbnail_uploadFiles/";
			
			File f = new File(savePath);
			if(!f.exists()) {
				f.mkdir();
			}
			//DefaultFileRenamePolicy : 같은 파일 명이 존재하는지 검사하고 있을 경우 파일 명 뒤에 숫자를 붙여 구문
			// aaa.zip aaa1.zip aaa2.zip
			//MultipartRequest multiRequest
			// = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			String pName = multiRequest.getParameter("pName");
			String pPrice = multiRequest.getParameter("pPrice");
			String pCategory = multiRequest.getParameter("pCategory");
			String pDescription = multiRequest.getParameter("pDescription");
			String pOrigin = multiRequest.getParameter("pOrigin");
			String pCapacity = multiRequest.getParameter("pCapacity");
			
			ArrayList<String> saveFiles = new ArrayList<String>();//바뀐 파일의 이름
			ArrayList<String> originFiles = new ArrayList<String>();// 원래 파일의 이름
			
			Enumeration<String> files = multiRequest.getFileNames();//폼에서 전송된 파일 리스트의 이름 반환
			while(files.hasMoreElements()) {
				String name = files.nextElement();
				
//				if(multiRequest.getFilesystemName(name) != null) {
					saveFiles.add(multiRequest.getFilesystemName(name));
					originFiles.add(multiRequest.getOriginalFileName(name));
//				}
			}
			
			Product p = new Product();
			p.setpName(pName);
			p.setpPrice(pPrice);
			p.setpCategory(pCategory);
			p.setpDescription(pDescription);
			p.setpOrigin(pOrigin);
			p.setpCapacity(pCapacity);
			
			ArrayList<PAttachment> fileList = new ArrayList<PAttachment>();
			
			for(int i = originFiles.size() - 1; i >= 0; i--) {
				PAttachment at = new PAttachment();
				at.setFilePath(savePath);
				at.setOriginName(originFiles.get(i));
				at.setChangeName(saveFiles.get(i));
				
				if(i == originFiles.size() - 1) {
					at.setFileLevel(0);
				} else {
					at.setFileLevel(1);
				}
				fileList.add(at);
			}
			
			int result = new ProductService().insertProduct(p, fileList);
			
			if(result > 0) {
				response.sendRedirect("list.pr");
			} else {
				for(int i = 0; i < saveFiles.size(); i++) {
					File failedFile = new File(savePath + saveFiles.get(i));
					failedFile.delete();
				}
				
				request.setAttribute("msg",  "상품 등록에 실패하였습니다.");
				request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);;
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
