<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="de.tum.in.dbpra.model.bean.StageBean"%>
<%@ page import="de.tum.in.dbpra.model.bean.EmployeeBean"%>
<%@ page import="de.tum.in.dbpra.model.bean.AreaBean"%>
<%@ page import="java.util.LinkedHashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashSet"%>

<!DOCTYPE html>


<jsp:useBean id="band" scope="request"
	class="de.tum.in.dbpra.model.bean.BandBean"></jsp:useBean>

	
<jsp:useBean id="user" scope="session"
	class="de.tum.in.dbpra.model.bean.UserAccountBean"></jsp:useBean>

<html>

<jsp:include page="head.jsp" >
  <jsp:param name="pageTitle" value="Overview" />
</jsp:include>

<body>
	<jsp:include page="bandHeader.jsp">
		<jsp:param name="name" value="${user.getUserName()}" />
		<jsp:param name="photoUrl" value="${user.getPhotoUrl()}"/>
		<jsp:param name="hasProducts" value="true" />
		<jsp:param name="currentTab" value="notes" />
	</jsp:include>
	
	<section class="section">
		<div class="columns">
			<div class="column is-6 is-offset-3">
				<div class="box">
				<% if(request.getAttribute("form_error") != null) { %>
				<div class="error">Error: Note cannot be empty and at least one recipient needs to be selected.</div>
				<% } %>
					
				<% 
				LinkedHashMap<Integer, EmployeeBean> employees = (LinkedHashMap<Integer, EmployeeBean>) request.getAttribute("employees");
				
				if(employees.size() == 0) { %>
				<h2 class="festival-day-label">There exists no employee you could issue a note to.</h2>
				<% } else { %>				
					<form action="/band/notes" method="post">
						<div class="field">
							<label for="note_content" class="label" >Note:</label>
							<textarea class="textarea" name="note" id="note_content"><%=(request.getAttribute("prefill_note") != null ? request.getAttribute("prefill_note") : "") %></textarea>
						</div>
						
						<p style="margin:25px 0 15px 0">Please tick at least one employee/stage combination for which this note is relevant.</p>
						
						<table class="table">
							<thead>
								<tr>
									<th>Select</th>
									<th>Last Name</th>
									<th>First Name</th>
									<th>Stage</th>
								</tr>
								
							</thead>
							
							<tbody>
								<%
									for(EmployeeBean e : employees.values()) {
										for(StageBean b : e.getStages()) {
										%>					
										<tr>
											<td><input<%= (request.getAttribute("prefill_employee_ids") != null && ((HashSet<Integer>)request.getAttribute("prefill_employee_ids")).contains(e.getUserID() + "-" + b.getName()) ? " checked=\"checked\"" : "") %> type="checkbox" name="employee_ids" value="<%=e.getUserID() %>-<%=b.getName() %>" /></td>
											<td><%=e.getFirstName() %></td>
											<td><%=e.getLastName() %></td>
											<td><%=b.getName() %></td>
										</tr>
									<% }} %>
							</tbody>
						</table>
						
						<input type="submit" class="button is-primary is-fullwidth" value="Create" name="submit" />
					</form>
			<% } %>
				</div>			
				</div>
			</div>
	</section>
</body>
</html>