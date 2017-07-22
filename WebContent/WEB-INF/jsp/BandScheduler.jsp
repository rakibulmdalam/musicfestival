<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set"%>
<%@ page import="de.tum.in.dbpra.model.bean.ScheduleBean"%>
<%@ page import="de.tum.in.dbpra.model.bean.StageBean"%>
<%@ page import="de.tum.in.dbpra.model.bean.BandBean"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" >
  <jsp:param name="pageTitle" value="Overview" />
</jsp:include>
<body>
	<jsp:include page="adminHeader.jsp">
		<jsp:param name="firstName" value="${user.getUserName()}" />
		<jsp:param name="currentTab" value="schedules" />
	</jsp:include>
	
	<section class="section">
	
		<div class="columns">
			<div class="column is-6 is-offset-3">
			<div class="box">
			
				<% if(request.getAttribute("form_error") != null) { %>
				<div class="error">Error: Note cannot be empty and at least one recipient needs to be selected.</div>
				<% } %>
					
				<% 
				ArrayList<BandBean> bands = (ArrayList<BandBean>) request.getAttribute("bands");
				
				if(bands.size() == 0) { %>
				<h2 class="festival-day-label">There exists no band you could enter a schedule for.</h2>
				<% } else { %>				
					<form action="/admin/schedules" method="post">
						<div class="field">
  							<label for="band_id" class="label">Band:</label>
							<div class="select is-normal">
								<select name="band_id" id="band_id">
									<%
										for(BandBean b : bands) {
										%>					
										<option value="<%=b.getUserID() %>"><%=b.getName() %></option>
									<% } %>
								</select>
							</div>
						</div>
						<p style="margin:25px 0 15px 0">Please enter the schedule for the selected band.</p>
						
						<table class="table">
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
										<input class="input is-small" type="text" name="day_build_up" placeholder="YYYY-MM-DD" />
									</td>
									<td>
										<div class="select is-small">
											<select name="time_build_up">
												<% for(int i = 0; i <= 23; i++) { %>
												<option value="<%=i*60 %>"><%=i + ":00" %></option>
												<option value="<%=i*60+30 %>"><%=i + ":30" %></option>
												<% } %>
											</select>
										</div>
									</td>
								</tr>
								<tr>
									<td>Start Playing</td>
									<td>
										<input class="input is-small" type="text" name="day_start_playing" placeholder="YYYY-MM-DD" />
									</td>
									<td>
										<div class="select is-small">
											<select name="time_start_playing">
												<% for(int i = 0; i <= 23; i++) { %>
												<option value="<%=i*60 %>"><%=i + ":00" %></option>
												<option value="<%=i*60+30 %>"><%=i + ":30" %></option>
												<% } %>
											</select>
										</div>
									</td>
								</tr>
								<tr>
									<td>End Playing</td>
									<td>
										<input class="input is-small" type="text" name="day_end_playing" placeholder="YYYY-MM-DD" />
									</td>
									<td>
										<div class="select is-small">
											<select name="time_end_playing">
												<% for(int i = 0; i <= 23; i++) { %>
												<option value="<%=i*60 %>"><%=i + ":00" %></option>
												<option value="<%=i*60+30 %>"><%=i + ":30" %></option>
												<% } %>
											</select>
										</div>
									</td>
								</tr>
								<tr>
									<td>Leave Stage</td>
									<td>
										<input class="input is-small" type="text" name="day_leave_stage" placeholder="YYYY-MM-DD" />
									</td>
									<td>
										<div class="select is-small">
											<select name="time_leave_stage">
												<% for(int i = 0; i <= 23; i++) { %>
												<option value="<%=i*60 %>"><%=i + ":00" %></option>
												<option value="<%=i*60+30 %>"><%=i + ":30" %></option>
												<% } %>
											</select>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
						
						<input type="submit" class="button is-primary is-fullwidth" value="Create" name="submit" />
					</form>
			<% } %>
									
				</div>
				</div>
			</div>
	</section>

	<section class="section">
		<%
		if (request.getAttribute("schedules") == null || request.getAttribute("dates") == null) {
		%>
			<h2 class="has-text-centered">No schedules matching your query were found</h2>
		<%
		} else {
			HashMap<String, HashMap<String, List<ScheduleBean>>> schedules = (HashMap)request.getAttribute("schedules");
			for (String date : (Set<String>)request.getAttribute("dates")) {
		%>
			<h2 class="festival-day-label"><%=date%></h2>
			<div class="timetable">
			<div class="intervals">
				<div class="interval">12PM</div>
				<div class="interval">1PM</div>
				<div class="interval">2PM</div>
				<div class="interval">3PM</div>
				<div class="interval">4PM</div>
				<div class="interval">5PM</div>
				<div class="interval">6PM</div>
				<div class="interval">7PM</div>
				<div class="interval">8PM</div>
				<div class="interval">9PM</div>
				<div class="interval">10PM</div>
				<div class="interval">11PM</div>
				<div class="interval">12AM</div>
				<div class="interval">1AM</div>
			</div>
			<% HashMap<String, List<ScheduleBean>> schedulesOnDate = schedules.get(date);
			for (Map.Entry<String, List<ScheduleBean>> schedulesByStage : schedulesOnDate.entrySet()) { %>	
			<div class="stage-label">
				<span class="stage-label__text"><%= schedulesByStage.getKey() %></span>
			</div>
			<div class="time-slots">
				<%
				ScheduleBean prevSchedule = null;
				for (ScheduleBean schedule : schedulesByStage.getValue()) {
					if (prevSchedule != null) {
						long minutesAfterPrev = (schedule.getTimeStartPlaying().getTime() - prevSchedule.getTimeFinishPlaying().getTime()) / (1000 * 60); %>
						<div class="time-slot time-slot--empty time-slot--<%= minutesAfterPrev %>mins"></div>
				<%
					}
					prevSchedule = schedule;
				%>
				<div class="time-slot time-slot--fixed time-slot--<%= schedule.getDurationInMinutes()%>mins">
					<div class="time-slot__image" style="background-image: url('<%= schedule.getBand().getPhotoUrl() %>')"></div>
					<div class="time-slot__text">
						<h2 class="time-slot__title"><%= schedule.getBand().getName() %></h2>
						<h4 class="time-slot__info"><%=schedule.getFormattedTimeStartPlaying() %>-<%=schedule.getFormattedTimeFinishPlaying() %></h4>
					</div>
				</div>
				<%
				}
				%>
			</div>
		</div>
		<% } %>
		<% } %>
		<% } %>
	</section>
</body>
</html>