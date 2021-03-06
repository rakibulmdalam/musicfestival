<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set"%>
<%@ page import="de.tum.in.dbpra.model.bean.ScheduleBean"%>
<%@ page import="de.tum.in.dbpra.model.bean.StageBean"%>

	
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param name="pageTitle" value="Timetable" />
</jsp:include>
<body>
	<jsp:include page="visitorHeader.jsp">
		<jsp:param name="firstName" value="${user.getUserName()}" />
		<jsp:param name="photoUrl" value="${user.getPhotoUrl()}" />
		<jsp:param name="currentTab" value="timetable" />
	</jsp:include>
	<div class="tabs sub-tabs is-centered is-marginless">
		<ul>
			<li><a href="/visitor/timetable">Your picks</a></li>
			<li class="is-active"><a href="/visitor/timetable/search">Search</a></li>
		</ul>
	</div>
	<section class="section">
		<div class="columns">
			<div class="column is-8 is-offset-2">
				<div class="field is-horizontal">
					<div class="field-label is-normal">
						<label class="label">Search query</label>
					</div>
					<div class="field-body">
						<form class="field has-addons" method="get">
							<p class="control">
								<span class="select"> <select name="type">
									<% String type = request.getParameter("type"); %>
										<option value="band" <%= type != null && type.equals("band") ? "selected" : "" %>>Band</option>
										<option value="genre" <%= type != null && type.equals("genre") ? "selected" : "" %>>Genre</option>
										<option value="stage" <%= type != null && type.equals("stage") ? "selected" : "" %>>Stage</option>
								</select>
								</span>
							</p>
							<p class="control is-expanded">
								<% String query = request.getParameter("query"); %>
								<input class="input" type="text"
									placeholder="Search by band, genre or stage" name="query" value="<%= query != null ? query : "" %>">
							</p>
							<p class="control">
								<button type="submit" class="button is-primary"> Search </button>
							</p>
						</form>
					</div>
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
			for (String date : (List<String>)request.getAttribute("dates")) {
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
					long minutesAfterPrev;
					for (ScheduleBean schedule : schedulesByStage.getValue()) {
						if (prevSchedule != null) {
							minutesAfterPrev = (schedule.getTimeStartPlaying().getTime() - prevSchedule.getTimeFinishPlaying().getTime()) / (1000 * 60);
						} else {
							long millisInDay = 60 * 60 * 24 * 1000;
							long noon = (schedule.getTimeStartPlaying().getTime() / millisInDay) * millisInDay + 60 * 60 * 10 * 1000;
							minutesAfterPrev = (schedule.getTimeStartPlaying().getTime() - noon) / (1000 * 60);
						}
						prevSchedule = schedule;
					%>
						
						<div class="time-slot time-slot--empty time-slot--<%= minutesAfterPrev %>mins"></div>
						<div class="time-slot time-slot--fixed time-slot--<%= schedule.getDurationInMinutes()%>mins">
							<div class="time-slot__image" style="background-image: url('<%= schedule.getBand().getPhotoUrl() %>')"></div>
							<div class="time-slot__text">
								<h2 class="time-slot__title"><%= schedule.getBand().getName() %></h2>
								<h4 class="time-slot__info"><%=schedule.getFormattedTimeStartPlaying() %>-<%=schedule.getFormattedTimeFinishPlaying() %></h4>
							</div>
							<form method="post">
							<% List<Integer> visitorScheduleIds = (List<Integer>)request.getAttribute("visitorScheduleIds"); 
							   if (visitorScheduleIds.contains(schedule.getId())) { %>
								<input class="input is-hidden" type="number" name="deleteId" value=<%=schedule.getId() %>>
								<button class="time-slot__button delete" type="submit"></button>
							<% } else { %>
								<input class="input is-hidden" type="number" name="addId" value=<%=schedule.getId() %>>
								<button class="time-slot__button delete delete--add-button"></button>
							<% } %>
							</form>
						</div>
					<% } %>
				</div>
				<% } %>
			</div>
		<% } %>
		<% } %>
	</section>
	<% if (request.getParameter("added") != null) { %>
	<article id="confirmation-message" class="message is-success">
  		<div class="message-header">
    		<p>Schedule added to your timetable!</p>
    		<button class="delete" onclick="removeConfirmation()"></button>
  		</div>
	</article>
	<% } else if (request.getParameter("deleted") != null) { %>
	<article id="confirmation-message" class="message is-success">
  		<div class="message-header">
    		<p>Schedule deleted from your timetable!</p>
    		<button class="delete" onclick="removeConfirmation()"></button>
  		</div>
	</article>
	<% } %>
</body>
</html>