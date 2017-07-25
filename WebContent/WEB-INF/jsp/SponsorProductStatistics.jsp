<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" >
  <jsp:param name="pageTitle" value="Product Statistics" />
</jsp:include>
<body>
	<jsp:include page="sponsorHeader.jsp">
		<jsp:param name="name" value="TUM" />
		<jsp:param name="hasProducts" value="true" />
		<jsp:param name="currentTab" value="statistics" />
	</jsp:include>
</body>
</html>