<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="user" scope="session"
 	class="de.tum.in.dbpra.model.bean.UserAccountBean"></jsp:useBean>

<html>
<jsp:include page="head.jsp" >
  <jsp:param name="pageTitle" value="Overview" />
</jsp:include>
<body>
	<jsp:include page="nonAdminHeader.jsp">
		<jsp:param name="firstName" value="${user.getUserName()}" />
		<jsp:param name="photoUrl" value="${user.getPhotoUrl()}"/>
		<jsp:param name="hasBandNotes" value="true" />
		<jsp:param name="currentTab" value="profile" />
	</jsp:include>
</body>
</html>