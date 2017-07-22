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
						</br>
						<% if (request.getAttribute("error") != null) { %>
						<div class="notification is-primary">
					
						  <button class="delete"></button>
						 	<strong><%=request.getAttribute("error")%></strong>
						 
						</div>
					
						
					</div>
				</div>
			</div>
		</div>
	</section>
	

			<%@ page import = "de.tum.in.dbpra.model.bean.Role" %>
			
			
			<%
				} else if (user.getRole() != null) { %>
				<input type = "hidden" name = "userID" value = "${user.getUserID()}" >
				<% } %>
			 
</body>
</html>