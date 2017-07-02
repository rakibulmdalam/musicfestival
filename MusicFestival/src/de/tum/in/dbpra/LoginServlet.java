package de.tum.in.dbpra;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tum.in.dbpra.model.bean.UserAccountBean;
import de.tum.in.dbpra.model.dao.UserAccountDAO;



/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
		dispatcher.forward(request, response);
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			UserAccountDAO dao = new UserAccountDAO();
			UserAccountBean user = new UserAccountBean();
			user.setUserName(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
        	dao.checkUserAccount(user);
        	request.setAttribute("user", user);

    	} catch (Throwable e) {
    		e.printStackTrace();
    		request.setAttribute("error", e.getMessage());
    	}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
		dispatcher.forward(request, response);
		
	}

}

