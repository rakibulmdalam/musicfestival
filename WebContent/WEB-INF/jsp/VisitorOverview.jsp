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
	<jsp:include page="visitorHeader.jsp">
		<jsp:param name="firstName" value="${user.getUserName()}" />
		<jsp:param name="photoUrl" value="${user.getPhotoUrl()}" />
		<jsp:param name="currentTab" value="profile" />
	</jsp:include>

	<section class="section">
		<div class="container has-text-centered">
			<h1 class="title is-2"><%=visitor.getFirstName()%>
				<%=visitor.getLastName()%></h1>
			<h2 class="subtitle">
				Date of birth:
				<%=visitor.getDateOfBirth()%><br> Balance: €<%=visitor.getBalance()%>
			</h2>
		</div>
	</section>
</body>
</html>