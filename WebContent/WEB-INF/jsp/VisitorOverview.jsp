<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param name="pageTitle" value="Overview" />
</jsp:include>
<body>
	<jsp:include page="visitorHeader.jsp">
		<jsp:param name="firstName" value="Helene" />
		<jsp:param name="photoUrl" value="https://uinames.com/api/photos/female/21.jpg" />
		<jsp:param name="currentTab" value="profile" />
	</jsp:include>
</body>
</html>