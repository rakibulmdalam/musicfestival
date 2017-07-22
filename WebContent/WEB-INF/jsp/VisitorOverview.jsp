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

	<div class="section">
		<div class="columns">
			<div class="column is-offset-one-quarter is-half">
				<div class="content has-text-centered">
					<h4>
						First Name:
						<b><%=visitor.getFirstName()%></b>
						<br>
					
						Last Name:
						<b><%=visitor.getLastName()%></b>
						<br>
					
						Date of birth:
						<b><%=visitor.getDateOfBirth()%></b>
						<br>
					
						Balance: €
						<b><%=visitor.getBalance()%></b>
					</h4>
				</div>
			</div>
		</div>
	</div>

</body>
</html>