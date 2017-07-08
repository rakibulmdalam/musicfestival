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
	<section class="hero is-fullheight">
		<div class="hero-body">
			<div class="container has-text-centered">
				<h1 class="title is-1 is-spaced title--branding">
					<span class="title__logo">M</span>
				</h1>
				<h2 class="subtitle">Welcome!</h2>
				<div class="columns">
					<div class="column is-one-third is-offset-one-third">
						<form method="post">
							<div class="field">
								<p class="control has-icons-left">
									<input class="input" type="email" placeholder="Email"> <span
										class="icon is-small is-left"> <i class="fa fa-envelope"></i>
									</span>
								</p>
							</div>
							<div class="field">
								<p class="control has-icons-left">
									<input class="input" type="password" placeholder="Password">
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
			<%-- <form method="post">
				<label>Username:</label> <input type="text" name="username"
					placeholder="Username"> <label>Password:</label> <input
					type="password" name="password" placeholder="Password"> <input
					type="submit" id="login" value="Login" />
			</form>

			<% if (request.getParameter("username") != null){ %>
				<%= user.getRole() %> 
			<%	} %>

			<%
				if (request.getAttribute("error") != null) {
			%>
			<p>User not found!</p>
			<%=request.getAttribute("error")%>

			<%
				} else {
					if (request.getParameter("username") != null && request.getParameter("username") != null) {
			%>
			<p>Login Success!</p>
			<%
				}
				}
			%> --%>
		
</body>
</html>