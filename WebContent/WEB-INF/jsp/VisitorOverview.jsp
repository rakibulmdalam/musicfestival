<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="visitor" scope="request"
 	class="de.tum.in.dbpra.model.bean.VisitorBean"></jsp:useBean>
<jsp:useBean id="user" scope="session"
 	class="de.tum.in.dbpra.model.bean.UserAccountBean"></jsp:useBean>

<html>
<jsp:include page="head.jsp">
	<jsp:param name="pageTitle" value="Overview" />
</jsp:include>
<body>
<%
 				if (user.getUserName() != null) { 
 				
 			%>
	<jsp:include page="visitorHeader.jsp">
		<jsp:param name="firstName" value="${user.getUserName()}" /> 
		<jsp:param name="photoUrl" value="${user.getPhotoUrl()}"/>
		<jsp:param name="userID" value="${user.getUserID()}" />
		<jsp:param name="currentTab" value="profile" />
	</jsp:include>
	<% } %>
	</br>
	
		<%  if (visitor.getFirstName() != null) { %>
			<div style="padding:100px;">
			</br><b>First Name: </b><%= visitor.getFirstName() %></br></br>
			<b>Last Name: </b><%= visitor.getLastName() %></br></br>
			<b>DOB: </b><%= visitor.getDateOfBirth() %></br></br>
			<b>Balance: € </b><%= visitor.getBalance() %></br></br>
			</div>
			<% } %>
			
</body>
</html>