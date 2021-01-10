//레펙 레시피 11.
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


import board.model.service.ManagerRecipeService;
import board.model.vo.MRAttachment;
import board.model.vo.ManagerRecipe;
import common.MyFileRenamePolicy;
import member.model.vo.Member;

/**
 * Servlet implementation class managerRecipeinserServlet
 */
@WebServlet("/insertManagerRecipe.th")
public class managerRecipeinsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public managerRecipeinsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize =1024*1024*10;
			String root = request.getSession().getServletContext().getRealPath("/");
			String savePath = root + "managerRecipe_uploadFiles/";
			
			File f = new File(savePath);
			if(!f.exists()) {
				f.mkdirs();
			}
			
			//레펙 레시피 14.
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
				
			ArrayList<String> saveFiles = new ArrayList<String>();
			ArrayList<String> originFiles = new ArrayList<String>();
			
			Enumeration<String> files = multiRequest.getFileNames();
			
			while(files.hasMoreElements()) {
				String name = files.nextElement();
				
				if(multiRequest.getFilesystemName(name) != null) {
					saveFiles.add(multiRequest.getFilesystemName(name));
					originFiles.add(multiRequest.getOriginalFileName(name));
				}
			}
			
			String title = multiRequest.getParameter("title");
			String content1 = multiRequest.getFilesystemName("thumbnailImg1");
			String content2 = multiRequest.getFilesystemName("thumbnailImg2");
			String bWriter = ((Member)request.getSession().getAttribute("loginUser")).getMemberid();
			int cap = Integer.parseInt(multiRequest.getParameter("cap"));
			int price = Integer.parseInt(multiRequest.getParameter("price"));
			String origin = multiRequest.getParameter("origin");
			String[] irr = multiRequest.getParameterValues("food");
			String pId = "";
			if(irr != null) {
				for(int i = 0; i < irr.length; i ++) {
					if(i == irr.length -1)
						pId += irr[i];
					else
						pId += irr[i]+",";
				}
			}
						
			ManagerRecipe b = new ManagerRecipe();
			
			b.setM_R_Title(title);
			b.setP_Id(pId);
			b.setM_R_Thumbnail(content1);
			b.setM_R_Explanation(content2);
			b.setMemberId(bWriter);
			b.setM_R_Capacity(cap);
			b.setM_R_price(price);
			b.setM_R_Origin(origin);
			
			
			ArrayList<MRAttachment> fileList = new ArrayList<MRAttachment>();
			for(int i = originFiles.size() - 1; i >= 0; i--) {
				MRAttachment at = new MRAttachment();
				at.setFilePath(savePath);
				at.setOriginName(originFiles.get(i));
				at.setChangeName(saveFiles.get(i));
				
				if(i == originFiles.size() -1) {
					at.setFileLevel(0);
				} else {
					at.setFileLevel(1);
				}
				
				fileList.add(at);
			}
			
			int result = new ManagerRecipeService().insertThumbnail(b, fileList);
			
			//레펙 레시피 16.
			if(result > 0) {
				response.sendRedirect("list.managerR");
			} else {
				for(int i = 0; i < saveFiles.size(); i++) {
					File failedFile = new File(savePath + saveFiles.get(i));
					failedFile.delete();
				}
				
				request.setAttribute("msg", "레시피 등록에 실패하였습니다.");
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
