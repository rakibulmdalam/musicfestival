<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" >
  <jsp:param name="pageTitle" value="Overview" />
</jsp:include>
<body>
	<jsp:include page="adminHeader.jsp">
		<jsp:param name="firstName" value="Boss" />
		<jsp:param name="currentTab" value="schedules" />
	</jsp:include>
</body>
</html>