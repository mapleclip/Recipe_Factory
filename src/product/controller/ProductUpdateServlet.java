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
 * Servlet implementation class ProductUpdateServlet
 */
@WebServlet("/update.pr")
public class ProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			String pId = multiRequest.getParameter("pId");
			String pName = multiRequest.getParameter("pName");
			String pPrice = multiRequest.getParameter("pPrice");
			String pCategory = multiRequest.getParameter("pCategory");
			String pDescription = multiRequest.getParameter("pDescription");
			String pOrigin = multiRequest.getParameter("pOrigin");
			String pCapacity = multiRequest.getParameter("pCapacity");
			String[] fId = multiRequest.getParameterValues("fId");
			String[] oldFilesName = multiRequest.getParameterValues("oldFileName");
			
			ArrayList<String> saveFiles = new ArrayList<String>();//바뀐 파일의 이름
			ArrayList<String> originFiles = new ArrayList<String>();// 원래 파일의 이름
			
			ArrayList<String> changeFid = new ArrayList<String>();
			ArrayList<String> oldFiles = new ArrayList<String>();
			
			Enumeration<String> files = multiRequest.getFileNames();//폼에서 전송된 파일 리스트의 이름 반환
			int x = 1;
			while(files.hasMoreElements()) {
				String name = files.nextElement();
				if(multiRequest.getFilesystemName(name) != null) {
					saveFiles.add(multiRequest.getFilesystemName(name));
					originFiles.add(multiRequest.getOriginalFileName(name));
					changeFid.add(fId[x]);
					oldFiles.add(oldFilesName[x]);
				}
				x--;
			}
			
			Product p = new Product();
			p.setpId(pId);
			p.setpName(pName);
			p.setpPrice(pPrice);
			p.setpCategory(pCategory);
			p.setpDescription(pDescription);
			p.setpOrigin(pOrigin);
			p.setpCapacity(pCapacity);
			
			ArrayList<PAttachment> fileList = new ArrayList<PAttachment>();
			
			for(int i = originFiles.size() - 1; i >= 0; i--) {
				PAttachment at = new PAttachment();
				at.setpId(pId);
				at.setFileId(Integer.parseInt(changeFid.get(i)));
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
			
			int result = new ProductService().updateProduct(p, fileList);
			
			if(result > 0) {
				for(int i = 0; i < oldFiles.size(); i++) {
					File oldFile = new File(savePath + oldFiles.get(i));
					oldFile.delete();
				}
				response.sendRedirect("list.pr");
			} else {
				for(int i = 0; i < saveFiles.size(); i++) {
					File failedFile = new File(savePath + saveFiles.get(i));
					failedFile.delete();
				}
				
				request.setAttribute("msg", "상품 등록에 실패하였습니다.");
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
