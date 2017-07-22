package de.tum.in.dbpra.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.tum.in.dbpra.model.bean.Role;
import de.tum.in.dbpra.model.bean.UserAccountBean;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession(false);

		UserAccountBean user = (UserAccountBean) session.getAttribute("user");

		if (session != null && user != null && user.getRole() == Role.EMPLOYEE && user.getEmployeeRole().equals("ADMIN")) {
			chain.doFilter(req, resp);
		} else {
			session.invalidate();
			((HttpServletResponse) resp).sendRedirect("/login");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
