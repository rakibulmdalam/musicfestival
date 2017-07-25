package de.tum.in.dbpra.filter;

import java.io.IOException;
import java.util.Date;
import java.util.function.Predicate;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.tum.in.dbpra.model.bean.UserAccountBean;

public abstract class AuthenticationFilter implements Filter {

	public abstract void destroy();

	public abstract void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException;

	public abstract void init(FilterConfig arg0) throws ServletException;

	public void redirectDisallowed(ServletRequest req, ServletResponse resp, FilterChain chain,
			Predicate<UserAccountBean> allowedUsersPredicate) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession(false);
		UserAccountBean user = null;
		
		if (session != null) {
			user = (UserAccountBean) session.getAttribute("user");
		}
		
		if (session != null && user != null && allowedUsersPredicate.test(user)) {
			Date now = new Date();
			Date loginDate = (Date) session.getAttribute("loginTime");

			long diff = now.getTime() - loginDate.getTime();

			if (diff > 15 * 60 * 1000) {
				session.invalidate();
				((HttpServletResponse) resp).sendRedirect("/login");
				return;
			}

			chain.doFilter(req, resp);
		} else {
			if (session != null) {
				session.invalidate();
			}
			((HttpServletResponse) resp).sendRedirect("/login");
		}
	}
}
