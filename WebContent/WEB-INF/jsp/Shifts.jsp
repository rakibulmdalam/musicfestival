<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="de.tum.in.dbpra.model.bean.ShiftBean"%>
<%@ page import="java.util.ArrayList"%>

<jsp:useBean id="employee" scope="request"
	class="de.tum.in.dbpra.model.bean.EmployeeBean"></jsp:useBean>
	
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" >
  <jsp:param name="pageTitle" value="Shifts" />
</jsp:include>
<body>
	<jsp:include page="nonAdminHeader.jsp">
		<jsp:param name="firstName" value="${user.getUserName()}" />
		<jsp:param name="photoUrl" value="${user.getPhotoUrl()}"/>
		<jsp:param name="hasBandNotes" value="true" />
		<jsp:param name="currentTab" value="shifts" />
	</jsp:include>
	<section class="section">
		<div class="columns">
			<div class="column is-6 is-offset-3">
		
		<% if(employee.getShifts().size() == 0) { %>
		<h2 class="festival-day-label">You have not yet been assigned any shifts.</h2>
		<% } else { %>
				<table>
					<thead>
						<tr>
							<th>Start</th>
							<th>End</th>
							<th>Area Name</th>
						</tr>
						
					</thead>
					
					<tbody>
						<%
							for(ShiftBean shift : employee.getShifts()) {
								%>					
								<tr>
									<td><%=shift.getStartingTime() %></td>
									<td><%=shift.getEndingTime() %></td>
									<td><%=shift.getArea().getName() %></td>
								</tr>
								<%
							}		
						} %>
					</tbody>
				</table>
				
			</div>
		</div>
	</section>
</body>
</html>