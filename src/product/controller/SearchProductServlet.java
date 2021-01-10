package product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.Service.ProductService;
import product.model.vo.PAttachment;
import product.model.vo.PageInfo;
import product.model.vo.Product;

/**
 * Servlet implementation class SearchProductServlet
 */
@WebServlet("/searchProduct.sp")
public class SearchProductServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String searchContent = request.getParameter("searchcontent");
      ProductService pService = new ProductService();
      
      
      
      //페이징 처리 변수
      int listCount;      //총 개시물 개수
      int currentPage;   //현재 페이지
      int pageLimit;      //한 페이지에 표시되 페이지 수
      int boardLimit;      //한 페이지에 보일 게시글 최대 개수
      int maxPage;      //전체 레이지 중 가장 마지막 페이지
      int startPage;      //페이징이 된 페이지 중 시작 페이지
      int endPage;      //페이징이 된 페이지 중 마지막 페이지
      
      listCount = pService.getListCount();
      
      currentPage = 1;
      if(request.getParameter("currentPage") != null) {
         currentPage = Integer.parseInt(request.getParameter("currentPage"));
      }
      
      pageLimit = 10;
      boardLimit = 9;
      
      
      //maxPage 계산
      maxPage = (int)Math.ceil((double)listCount/boardLimit);
      
      //startPage 계산
      startPage = pageLimit * ((currentPage - 1)/pageLimit) + 1;
      
      endPage = startPage + pageLimit  - 1;
      if(maxPage < endPage) {
         endPage = maxPage;
      }
      
      PageInfo pi = new PageInfo(currentPage, listCount, pageLimit, boardLimit, maxPage, startPage, endPage);
      
      ArrayList<Product> plist = pService.selectSearchList(searchContent);
      ArrayList<PAttachment> fList = pService.selectFList(plist);
      String page = null;
      if(plist != null) {
         page = "WEB-INF/views/product/productSearchList.jsp";
         request.setAttribute("plist", plist);
         request.setAttribute("fList", fList);
         request.setAttribute("pi", pi);
      } else {
         page = "WEB-INF/views/common/errorPage/jsp";
         request.setAttribute("msg", "상품 조회에 실패하였습니다.");
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