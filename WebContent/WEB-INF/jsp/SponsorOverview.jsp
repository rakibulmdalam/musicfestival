<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>


<jsp:useBean id="sponsor" scope="request"
	class="de.tum.in.dbpra.model.bean.SponsorBean"></jsp:useBean>

	
<jsp:useBean id="user" scope="session"
	class="de.tum.in.dbpra.model.bean.UserAccountBean"></jsp:useBean>

<html>

<jsp:include page="head.jsp" >
  <jsp:param name="pageTitle" value="Overview" />
</jsp:include>

<body>
	<jsp:include page="sponsorHeader.jsp">
		<jsp:param name="name" value="${user.getUserName()}" />
		<jsp:param name="photoUrl" value="${user.getPhotoUrl()}"/>
		<jsp:param name="hasProducts" value="true" />
		<jsp:param name="currentTab" value="profile" />
	</jsp:include>
	
	<div class="section">
		<div class="columns">
			<div class="column is-offset-one-quarter is-half">
				<div class="content has-text-centered">
					<h2>Sponsor Overview</h2>
					<table>
						<tr>
							<td>
								Name:
							</td>
							<td>
								<%=sponsor.getName() %>
							</td>
						</tr>
						<tr>
							<td>
								Email:
							</td>
							<td>
								<%=sponsor.getEmail() %>
							</td>
						</tr>
						<tr>
							<td>
								Paid Money(euro):
							</td>
							<td>
								<%=sponsor.getPaidMoney() %>
							</td>
						</tr>
						<tr>
							<td>
								Phone:
							</td>
							<td>
								<%=sponsor.getPhone() %>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>