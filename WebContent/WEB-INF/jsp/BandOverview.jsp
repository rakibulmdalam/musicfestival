<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="user" scope="request"
 	class="de.tum.in.dbpra.model.bean.UserAccountBean"></jsp:useBean>

<html>
<jsp:include page="head.jsp" >
  <jsp:param name="pageTitle" value="Overview" />
</jsp:include>
<body>
	<jsp:include page="bandHeader.jsp">
		<jsp:param name="name" value="${user.getUserName()}" />
		<jsp:param name="hasProducts" value="true" />
		<jsp:param name="currentTab" value="profile" />
	</jsp:include>
</body>
</html>