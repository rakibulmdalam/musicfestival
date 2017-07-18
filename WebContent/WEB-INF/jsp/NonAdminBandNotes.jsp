<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<%@ page import="de.tum.in.dbpra.model.bean.EmployeeBean"%>
<%@ page import="de.tum.in.dbpra.model.bean.BandEmployeeInteractionBean"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>

<jsp:useBean id="employee" scope="request"
	class="de.tum.in.dbpra.model.bean.EmployeeBean"></jsp:useBean>
	
<jsp:include page="head.jsp" >
  <jsp:param name="pageTitle" value="Band Notes" />
</jsp:include>
<body>
	<jsp:include page="nonAdminHeader.jsp">
		<jsp:param name="firstName" value="Employee" />
		<jsp:param name="hasBandNotes" value="true" />
		<jsp:param name="currentTab" value="band-notes" />
	</jsp:include>
	
	<h1>Band Notes</h1>
	<p>Here, you can see all notes that were issued to you by bands.</p>
	
	<% ArrayList<BandEmployeeInteractionBean> interactions = (ArrayList<BandEmployeeInteractionBean>) request.getAttribute("interactions"); %>
	
	
	<% if(interactions.size() == 0) { %>
		<em>You have not yet been assigned any notes.</em>
	<% } else { %>
	<table>
		<thead>
			<tr>
				<th>Band Name</th>
				<th>Stage</th>
				<th>Date/Time</th>
				<th>Description</th>
			</tr>
			
		</thead>
		
		<tbody>
			<%
				for(BandEmployeeInteractionBean interaction : interactions) {
					%>					
					<tr>
						<td><%=interaction.getBand().getName() %></td>
						<td><%=interaction.getStage().getName() %></td>
						<td><%=interaction.getInteractionDate() %></td>
						<td><%=interaction.getDescription() %></td>
					</tr>
					<%
				}		
			} %>
		</tbody>
	</table>
</body>
</html>