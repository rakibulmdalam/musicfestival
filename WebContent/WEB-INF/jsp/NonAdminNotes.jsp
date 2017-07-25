<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="de.tum.in.dbpra.model.bean.NoteNotificationBean"%>
<%@ page import="de.tum.in.dbpra.model.bean.NoteBean"%>
<%@ page import="java.util.ArrayList"%>

<jsp:useBean id="employee" scope="request"
	class="de.tum.in.dbpra.model.bean.EmployeeBean"></jsp:useBean>
	
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" >
  <jsp:param name="pageTitle" value="Notes" />
</jsp:include>
<body>
	<jsp:include page="nonAdminHeader.jsp">
		<jsp:param name="firstName" value="${user.getUserName()}" />
		<jsp:param name="photoUrl" value="${user.getPhotoUrl()}"/>
		<jsp:param name="hasBandNotes" value="true" />
		<jsp:param name="currentTab" value="notes" />
	</jsp:include>
	<section class="section">
		<div class="columns">
			<div class="column is-6 is-offset-3">	
	
				<% ArrayList<NoteNotificationBean> notes = (ArrayList<NoteNotificationBean>) request.getAttribute("notes"); %>
				
				
				<% if(notes.size() == 0) { %>
					<h2 class="festival-day-label">You have not yet been assigned any notes by the administrator.</h2>
				<% } else { %>
				<table>
					<thead>
						<tr>
							<th>Area</th>
							<th>Date/Time</th>
							<th>Note</th>
							<th>Seen?</th>
						</tr>
						
					</thead>
					
					<tbody>
						<%
							for(NoteNotificationBean note : notes) {
								%>					
								<tr>
									<td><%=note.getNote().getArea().getName() %></td>
									<td><%=note.getNote().getCreationTime() %></td>
									<td><%=note.getNote().getContent() %></td>
									<td><%=note.getSeenTime() != null ? "yes" : "no" %></td>
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