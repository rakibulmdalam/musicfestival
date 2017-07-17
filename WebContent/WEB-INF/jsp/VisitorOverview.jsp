<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="visitor" scope="request"
 	class="de.tum.in.dbpra.model.bean.VisitorBean"></jsp:useBean>
<jsp:useBean id="user" scope="request"
 	class="de.tum.in.dbpra.model.bean.UserAccountBean"></jsp:useBean>

<html>
<jsp:include page="head.jsp">
	<jsp:param name="pageTitle" value="Overview" />
</jsp:include>
<body>
	<jsp:include page="visitorHeader.jsp">
		<jsp:param name="firstName" value="${user.getUserName()}" /> 
		<jsp:param name="photoUrl" value="Whatever"/>
		<jsp:param name="userID" value="${user.getUserID()}" />
		<jsp:param name="currentTab" value="profile" />
	</jsp:include>
	</br>
	
	<%
 				if (request.getAttribute("error") != null) { 
 				
 			%> 
 			<p>User not found!</p> 
			<%=request.getAttribute("error")%>

			<% } else if (visitor.getFirstName() != null) { %>
			</br><b>First Name:</b><%= visitor.getFirstName() %></br>
			<b>Last Name:</b><%= visitor.getLastName() %></br>
			<b>DOB:</b><%= visitor.getDateOfBirth() %></br>
			<b>Balance: € </b><%= visitor.getBalance() %></br>
			<b>Photo: </b><%= visitor.getPhotoUrl() %></br>
			<% } %>
			
</body>
</html>