package notice.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.serivce.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsertServlet
 */
@WebServlet("/Noticeinsert.no")
public class NoticeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String userId = "admin";
		String date = request.getParameter("date");
		String content = request.getParameter("content");
		
		Date dat = null;
		if(!date.equals("")) {
			String[] dateArr = date.split("-");
			
			int year = Integer.parseInt(dateArr[0]);
			int month = Integer.parseInt(dateArr[1]) -1;
			int day = Integer.parseInt(dateArr[2]);
			
			dat = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
		} else {
			dat = new Date(new GregorianCalendar().getTimeInMillis());
		}
		
		
		Notice n = new Notice(title,content, userId, dat);
		
		int result = new NoticeService().insertNotice(n);
		
		if(result > 0) {
			response.sendRedirect("NoticeListServlet.no");
		} else {
			request.setAttribute("msg", "공지사항 등록에 실패하였습니다");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
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
