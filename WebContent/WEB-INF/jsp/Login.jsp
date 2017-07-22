<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="user" scope="request"
	class="de.tum.in.dbpra.model.bean.UserAccountBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" >
  <jsp:param name="pageTitle" value="Login" />
</jsp:include>
<body>
	<section class="hero is-fullheight is-dark">
		<div class="hero-body">
			<div class="container has-text-centered">
				<h1 class="title is-1 title--branding is-spaced is-inline-flex">
					<span class="title__logo">M</span>
				</h1>
				<h2 class="subtitle">Welcome!</h2>
				<div class="columns">
					<div class="column is-one-third is-offset-one-third">
						<form method="post">
							<div class="field">
								<p class="control has-icons-left">
									<input class="input" type="email" name = "email" placeholder="Email" > <span
										class="icon is-small is-left"> <i class="fa fa-envelope"></i>
									</span>
								</p>
							</div>
							<div class="field">
								<p class="control has-icons-left">
									<input class="input" type="password" name = "password" placeholder="Password" >
									<span class="icon is-small is-left"> <i class="fa fa-lock"></i>
									</span>
								</p>
							</div>
							<div class="field">
								<p class="control">
									<input type="submit" value="Login" class="button is-success is-fullwidth">
								</p>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
<!-- 			 <form method="post"> -->
<!-- 				<label>Username:</label> <input type="text" name="username" -->
<!-- 					placeholder="Username"> <label>Password:</label> <input -->
<!-- 					type="password" name="password" placeholder="Password"> <input -->
<!-- 					type="submit" id="login" value="Login" /> -->
<!-- 			</form> -->

<%-- 			<% if (request.getParameter("Email") != null){ %> --%>
<%-- 				<%= user.getRole() %>  --%>
<%-- 			<%	} %> --%>
			<%@ page import = "de.tum.in.dbpra.model.bean.Role" %>
			
			<%
				if (request.getAttribute("error") != null) {
			%>
			<p>User not found!</p>
			<%=request.getAttribute("error")%>

			<%
				} else if (user.getRole() != null) { %>
				<input type = "hidden" name = "userID" value = "${user.getUserID()}" >
				<% } %>
<%-- 				<%	Role employee = Role.EMPLOYEE; --%>
<!-- // 	  				Role band = Role.BAND;  -->
<!-- // 	 				Role sponsor = Role.SPONSOR;  -->
<!-- // 	 				Role visitor = Role.VISITOR; -->
<!-- //  					if ( user.getRole() ==  employee ){ -->
<!-- //  						response.sendRedirect("./employee") ;  }   -->
<!-- //  					if ( user.getRole() ==  band ){  -->
<!-- //  						response.sendRedirect("./band") ;  }   -->
<!-- //  					if ( user.getRole() ==  sponsor ){  -->
<!-- //  						response.sendRedirect("./sponsor") ;  }   -->
<!-- //  					if ( user.getRole() ==  visitor ){  -->
<!-- //  						response.sendRedirect("./visitor") ;  }   -->
<!-- //  				} -->

<%-- 			 %> --%>
			 
</body>
</html>