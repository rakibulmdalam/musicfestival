<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
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
				<div>
					
					<h1>List of Songs</h1>
					<ul>
						<% for( SongBean song: band.getSongs()){ %>
						<li>
							<%=song.getTitle() %>
						</li>
						<%} %>
					</ul>
					
					
					<h1>Add Song</h1>
					<form method="post">
						<table>
							<tr>
								<td>
									Title
								</td>
								<td>
									<input type="text" id="newSongTitle" name="newSongTitle">
								</td>
								<td>
									&nbsp;
								</td>
								<td>
									<input type="submit" id="newSongSubmit" value="add song">
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html></html>