<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="de.tum.in.dbpra.model.bean.ScheduleBean"%>	
<%@ page import="de.tum.in.dbpra.model.bean.SongBean"%>

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
		<jsp:param name="currentTab" value="profile" />
	</jsp:include>
	
	<div class="section">
		<div class="columns">
			<div class="column is-offset-one-quarter is-half">
				<div class="content has-text-centered">
					<h2>Band Overview</h2>
					<table>
						<tr>
							<td>
								Name:
							</td>
							<td>
								<%=band.getName() %>
							</td>
						</tr>
						<tr>
							<td>
								Email:
							</td>
							<td>
								<%=band.getEmail() %>
							</td>
						</tr>
						<tr>
							<td>
								Genre:
							</td>
							<td>
								<%=band.getGenre() %>
							</td>
						</tr>
						<tr>
							<td>
								Number of Members:
							</td>
							<td>
								<%=band.getNumberOfMembers() %>
							</td>
						</tr>
						<tr>
							<td>
								Phone:
							</td>
							<td>
								<%=band.getPhone() %>
							</td>
						</tr>
					</table>
				</div>
				<div class="content">
					
					<h2>Schedules</h2>
					<table>
						<tr>
							<td>Time Build Up</td>
							<td>Time Start Playing</td>
							<td>Time Finish Playing</td>
							<td>Time Leave Stage</td>
							<td>Stage</td>
						</tr>
						<% for( ScheduleBean schedule: band.getSchedules()){ %>
						<tr>
							<td><%=schedule.getTimeBuildUp() %></td>
							<td><%=schedule.getTimeStartPlaying() %></td>
							<td><%=schedule.getTimeFinishPlaying() %></td>
							<td><%=schedule.getTimeLeaveStage() %></td>
							<td><%=schedule.getStage().getName() %></td>
						</tr>
						<%} %>
					</table>
				</div>
				<div class="content">
					
					<h2>List of Songs</h2>
					<ul>
						<% for( SongBean song: band.getSongs()){ %>
						<li>
							<%=song.getTitle() %>
						</li>
						<%} %>
					</ul>
				</div>
				<div class="content">		
					<h2>Add Song</h2>
					<form method="post">
						<div class="field">
							<label class="label">Title</label>
							<div class="control">
						    	<input type="text" class="input" placeholder="enter song title" id="newSongTitle" name="newSongTitle">
							</div>
						</div>
						
						<div class="field is-grouped">
						 	<div class="control">
								<input type="submit" value="add song" class="button is-primary">
						  	</div>
						</div>	
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>