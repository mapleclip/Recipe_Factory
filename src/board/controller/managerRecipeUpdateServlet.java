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
 * Servlet implementation class managerRecipeUpdateServlet
 */
@WebServlet("/updateManagerRecipe.th")
public class managerRecipeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public managerRecipeUpdateServlet() {
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
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
			int bId = Integer.parseInt(multiRequest.getParameter("bId"));
			
			String content1 = multiRequest.getFilesystemName("thumbnailImg1");
			String content2 = multiRequest.getFilesystemName("thumbnailImg2");
			String bWriter =  ((Member) request.getSession().getAttribute("loginUser")).getMemberid();
			String title = multiRequest.getParameter("title");
			int cap = Integer.parseInt(multiRequest.getParameter("cap"));
			int price = Integer.parseInt(multiRequest.getParameter("price"));
			String origin = multiRequest.getParameter("origin");
			String[] fId = multiRequest.getParameterValues("fId");
			String[] oldFiles = multiRequest.getParameterValues("oldFileName");
		
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
		
			ManagerRecipe managerRecipe = new ManagerRecipe();
			managerRecipe.setM_R_NO(bId);
			managerRecipe.setMemberId(bWriter);
			managerRecipe.setP_Id(pId);
			managerRecipe.setM_R_Title(title);
			managerRecipe.setM_R_Thumbnail(content1);
			managerRecipe.setM_R_Explanation(content2);
			managerRecipe.setM_R_Capacity(cap);
			managerRecipe.setM_R_price(price);
			managerRecipe.setM_R_Origin(origin);
			
			ArrayList<MRAttachment> fileList = new ArrayList<MRAttachment>();
		
			int j = 0;
			for (int i = originFiles.size() - 1; i >= 0; i--) {
				MRAttachment at = new MRAttachment();
				at.setm_R_NO(bId);
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
		
			int result = new ManagerRecipeService().updateManagerRecipe(managerRecipe, oldFiles, fileList);
		
		
			if(result > 0) {
				for (int i = 0; i < oldFiles.length; i++) {
					File oldFile = new File(savePath + oldFiles[i]);
					oldFile.delete();
				}
				response.sendRedirect("detail.MR?bId="+bId+ "&pId=" + pId);
			
			} else {
				for(int i = 0; i < saveFiles.size(); i++) {
					File failedFile = new File(savePath + saveFiles.get(i));
					failedFile.delete();
				}
			
				request.setAttribute("msg", "레펙 레시피 수정에 실패했습니다.");
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
