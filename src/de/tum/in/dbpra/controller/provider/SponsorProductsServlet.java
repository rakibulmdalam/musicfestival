package de.tum.in.dbpra.controller.provider;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.tum.in.dbpra.model.bean.ProductBean;
import de.tum.in.dbpra.model.dao.ProductDAO;

public class SponsorProductsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3548548572339513246L;

	public SponsorProductsServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		int providerId = (int) session.getAttribute("userID");
		ProductDAO productDAO = new ProductDAO();
		try {
			ArrayList<ProductBean> products = productDAO.getProductsByProvider(providerId);
			req.setAttribute("products", products);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/SponsorProducts.jsp");
		dispatcher.forward(req, resp);
	}

}
