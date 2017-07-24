<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="de.tum.in.dbpra.model.bean.VisitorBean"%>
<%@ page import="de.tum.in.dbpra.model.bean.ScheduleBean"%>
<%@ page import="de.tum.in.dbpra.model.bean.SongBean"%>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.stream.Collectors" %>

<jsp:useBean id="visitor" scope="request"
	class="de.tum.in.dbpra.model.bean.VisitorBean"></jsp:useBean>
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
			<li class="is-active"><a href="/visitor/timetable">Your
					picks</a></li>
			<li><a href="/visitor/timetable/search">Search</a></li>
		</ul>
	</div>
	<section class="section">
		<div class="columns">
			<div class="column is-6 is-offset-3">
				<% if (visitor.getTimetable().isEmpty()) {%>
					<div class="has-text-centered">
						<h1 class="title">Your timetable is empty</h1>
						<h2 class="subtitle is-6">Search for schedules and add them to your timetable!</h2>
					</div>
				<% } else {
					for (String day : (Set<String>)request.getAttribute("scheduleDays")) { %>
					<dl class="section__festival-day">
						<dt class="festival-day-label"><%= day %></dt>

						<% for (ScheduleBean item : visitor.getTimetable()) { %>
						<% if (item.getDateWithoutTime().equals(day)) {%>
						<dd class="box">
							<article class="media">
								<div class="media-left">
									<figure class="image band-image">
										<img
											src="<%=item.getBand().getPhotoUrl() %>"
											alt="Image">
									</figure>
								</div>
								<div class="media-content">
									<div class="content">
										<p class="title is-4">
											<strong><%=item.getBand().getName()%></strong>
										</p>
										<p class="subtitle is-6"><%=item.getStage().getName()%>,
											<%=item.getFormattedTimeStartPlaying()%>
											-
											<%=item.getFormattedTimeFinishPlaying()%></p>
										<div class="content is-small">
											<p>
												Genre:
												<%=item.getBand().getGenre()%><br>
											</p>
											<% if (!item.getBand().getSongs().isEmpty()) %>
											<p>
												Songs:
												<%= item.getBand().getFormattedSongNames() %>
											</p>
										</div>
									</div>
								</div>
								<div class="media-right">
									<form method="post">
										<input class="input is-hidden" type="number" name="deleteId" value=<%=item.getId() %>>
										<button class="delete" type="submit"></button>
									</form>
								</div>
							</article>
						</dd>
					
					<% } %>
					<% } %>
					</dl>
					<% } %>
					<% } %>
				
			</div>
		</div>
	</section>
	<% if (request.getParameter("deleted") != null) { %>
	<article id="confirmation-message" class="message is-success">
  		<div class="message-header">
    		<p>Schedule deleted from your timetable!</p>
    		<button class="delete" onclick="removeConfirmation()"></button>
  		</div>
	</article>
	<% } %>
</body>
</html>