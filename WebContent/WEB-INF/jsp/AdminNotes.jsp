<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="de.tum.in.dbpra.model.bean.EmployeeBean"%>
<%@ page import="de.tum.in.dbpra.model.bean.AreaBean"%>
<%@ page import="java.util.LinkedHashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashSet"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" >
  <jsp:param name="pageTitle" value="Overview" />
</jsp:include>
<body>
	<jsp:include page="adminHeader.jsp">
		<jsp:param name="firstName" value="${user.getUserName()}" />
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
				ArrayList<AreaBean> areas = (ArrayList<AreaBean>) request.getAttribute("areas");
				
				if(employees.size() == 0) { %>
				<h2 class="festival-day-label">There exists no employee you could issue a note to.</h2>
				<% } else { %>				
					<form action="/admin/notes" method="post">
						<div class="field">
							<label for="note_content" class="label" >Note:</label>
							<textarea class="textarea" name="note" id="note_content"><%=(request.getAttribute("prefill_note") != null ? request.getAttribute("prefill_note") : "") %></textarea>
						</div>
						
						<div class="field">
							<label class="label" for="area_id">Area:</label>
							<div class="select is-normal">
								<select name="area_id" id="area_id">
									<%
										for(AreaBean a : areas) {
										%>					
										<option value="<%=a.getId() %>"><%=a.getName() %></option>
									<% } %>
								</select>
							</div>
						</div>
						<p style="margin:25px 0 15px 0">Please tick at least one employee you want to notify of this note.</p>
						
						<table class="table">
							<thead>
								<tr>
									<th>Select</th>
									<th>Last Name</th>
									<th>First Name</th>
								</tr>
								
							</thead>
							
							<tbody>
								<%
									for(EmployeeBean e : employees.values()) {
										%>					
										<tr>
											<td><input<%= (request.getAttribute("prefill_employee_ids") != null && ((HashSet<Integer>)request.getAttribute("prefill_employee_ids")).contains(e.getUserID()) ? " checked=\"checked\"" : "") %> type="checkbox" name="employee_ids" value="<%=e.getUserID() %>" /></td>
											<td><%=e.getFirstName() %></td>
											<td><%=e.getLastName() %></td>
										</tr>
									<% } %>
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