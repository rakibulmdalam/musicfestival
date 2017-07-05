<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="user" scope="request" class="de.tum.in.dbpra.model.bean.UserAccountBean"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<form method="post">
       <label>Username:</label>
            <input type="text" name="username" placeholder="Username"></br>
        <label>Password:</label>
            <input type="password" name="password" placeholder="Password"></br>
        <input type="submit" id = "login" value="Login" /></br>
    </form>

<%-- <% if (request.getParameter("username") != null){ %> --%>
<%-- 	<%= user.getRole() %>  --%>
<%-- <%	} %> --%>
	
	<% if (request.getAttribute("error") != null) { %>
	<p>User not found!</p>
	<%= request.getAttribute("error") %>

	<% } else { if (request.getParameter("username") != null && request.getParameter("username") != null){%>
		<p>Login Success!</p>
		<%} } %>
	

</body>


</html>