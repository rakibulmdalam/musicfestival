package de.tum.in.dbpra.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import de.tum.in.dbpra.model.bean.Role;

@WebFilter("/employee/*")
public class NonAdminFilter extends AuthenticationFilter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		redirectDisallowed(req, resp, chain,
				user -> user.getRole() == Role.EMPLOYEE && !user.getEmployeeRole().equals("ADMIN"));
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
