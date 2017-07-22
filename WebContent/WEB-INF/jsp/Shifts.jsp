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
		<jsp:param name="firstName" value="Employee" />
		<jsp:param name="hasBandNotes" value="true" />
		<jsp:param name="currentTab" value="shifts" />
	</jsp:include>
	
	<h1>Shifts</h1>
	<p>Here, you can see all your shifts you were assigned to.</p>
	
	
	<% if(employee.getShifts().size() == 0) { %>
		<em>You have not yet been assigned any shifts.</em>
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
</body>
</html>