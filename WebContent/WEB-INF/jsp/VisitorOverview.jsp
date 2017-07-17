<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="visitor" scope="request"
 	class="de.tum.in.dbpra.model.bean.VisitorBean"></jsp:useBean>
<html>
<jsp:include page="head.jsp">
	<jsp:param name="pageTitle" value="Overview" />
</jsp:include>
<body>
	<jsp:include page="visitorHeader.jsp">
		<jsp:param name="firstName" value="Helen" />
		<jsp:param name="photoUrl" value="https://uinames.com/api/photos/male/28.jpg" />
		<jsp:param name="currentTab" value="profile" />
	</jsp:include>
	userID:<%= request.getParameter("userID") %>
	<%
				if (request.getAttribute("error") != null) {
			%>
			<p>User not found!</p>
			<%=request.getAttribute("error")%>

			<% } else if (visitor.getBalance() != null) { %>
			<%= visitor.getFirstName() %>
			<% } %>
			
</body>
</html>