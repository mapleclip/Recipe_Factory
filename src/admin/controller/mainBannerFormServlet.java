package admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import admin.model.vo.BAttachment;
import admin.model.vo.Banner;

/**
 * Servlet implementation class mainBannerUpdateServlet
 */
@WebServlet("/mainBanner.ad")
public class mainBannerFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mainBannerFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService service = new AdminService();
		
		ArrayList<Banner> bList = service.selectTTlist(1);
		ArrayList<BAttachment> fList = service.selectTTlist(0);
		
		if(bList.size() != 0) {
			request.setAttribute("bList", bList);
			request.setAttribute("fList", fList);
			request.getRequestDispatcher("WEB-INF/views/admin/mainSlideUpdateForm.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("WEB-INF/views/admin/mainSlideInsertForm.jsp").forward(request, response);
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
