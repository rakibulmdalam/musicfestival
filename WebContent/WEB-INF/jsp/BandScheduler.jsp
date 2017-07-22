<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="java.util.ArrayList"%>
<%@ page import="de.tum.in.dbpra.model.bean.BandBean"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" >
  <jsp:param name="pageTitle" value="Overview" />
</jsp:include>
<body>
	<jsp:include page="adminHeader.jsp">
		<jsp:param name="firstName" value="Boss" />
		<jsp:param name="currentTab" value="schedules" />
	</jsp:include>
	
	<section class="section">
		<div class="columns">
			<div class="column is-6 is-offset-3">
				<% if(request.getAttribute("form_error") != null) { %>
				<div class="error">Error: Note cannot be empty and at least one recipient needs to be selected.</div>
				<% } %>
					
				<% 
				ArrayList<BandBean> bands = (ArrayList<BandBean>) request.getAttribute("bands");
				
				if(bands.size() == 0) { %>
				<h2 class="festival-day-label">There exists no band you could enter a schedule for.</h2>
				<% } else { %>				
					<form action="/admin/schedules" method="post">
						
						<label for="band_id" method="post">Band:</label>
						<select name="band_id" id="band_id">
							<%
								for(BandBean b : bands) {
								%>					
								<option value="<%=b.getUserID() %>"><%=b.getName() %></option>
							<% } %>
						</select>
						
						<p style="margin:25px 0 15px 0">Please enter the schedule for the selected band.</p>
						
						<table>
							<thead>
								<tr>
									<th></th>
									<th>Day</th>
									<th>Time</th>
								</tr>
							</thead>
														
							<tbody>												
								<tr>
									<td>Build Up</td>
									<td>
										<input type="text" name="day_build_up" placeholder="YYYY-MM-DD" />
									</td>
									<td>
										<select name="time_build_up">
											<% for(int i = 0; i <= 23; i++) { %>
											<option value="<%=i*60 %>"><%=i + ":00" %></option>
											<option value="<%=i*60+30 %>"><%=i + ":30" %></option>
											<% } %>
										</select>
									</td>
								</tr>
								<tr>
									<td>Start Playing</td>
									<td>
										<input type="text" name="day_start_playing" placeholder="YYYY-MM-DD" />
									</td>
									<td>
										<select name="time_start_playing">
											<% for(int i = 0; i <= 23; i++) { %>
											<option value="<%=i*60 %>"><%=i + ":00" %></option>
											<option value="<%=i*60+30 %>"><%=i + ":30" %></option>
											<% } %>
										</select>
									</td>
								</tr>
								<tr>
									<td>End Playing</td>
									<td>
										<input type="text" name="day_end_playing" placeholder="YYYY-MM-DD" />
									</td>
									<td>
										<select name="time_end_playing">
											<% for(int i = 0; i <= 23; i++) { %>
											<option value="<%=i*60 %>"><%=i + ":00" %></option>
											<option value="<%=i*60+30 %>"><%=i + ":30" %></option>
											<% } %>
										</select>
									</td>
								</tr>
								<tr>
									<td>Leave Stage</td>
									<td>
										<input type="text" name="day_leave_stage" placeholder="YYYY-MM-DD" />
									</td>
									<td>
										<select name="time_leave_stage">
											<% for(int i = 0; i <= 23; i++) { %>
											<option value="<%=i*60 %>"><%=i + ":00" %></option>
											<option value="<%=i*60+30 %>"><%=i + ":30" %></option>
											<% } %>
										</select>
									</td>
								</tr>
							</tbody>
						</table>
						
						<input type="submit" value="Create" name="submit" style="margin-top:15px" />
					</form>
			<% } %>
									
				</div>
			</div>
	</section>
</body>
</html>