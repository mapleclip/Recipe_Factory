package product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import product.model.Service.ProductService;
import product.model.vo.PQNA;

/**
 * Servlet implementation class ProductPQNASelectServlet
 */
@WebServlet("/selectPQNA.pr")
public class ProductPQNASelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductPQNASelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pqbNo = Integer.parseInt(request.getParameter("pqbNo"));
		
		PQNA pqna = new ProductService().selectPQNA(pqbNo);
		
		response.setContentType("application/json; charset=UTF-8");
		
		Gson gson = new Gson();
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(pqna, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
