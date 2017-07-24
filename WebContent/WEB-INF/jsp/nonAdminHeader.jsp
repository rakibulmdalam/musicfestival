<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<section class="hero is-dark">
	<div class="hero-body">
		<div class="container has-text-centered">
			<h1 class="title is-1 title--branding is-spaced is-inline-flex">
				<span class="title__logo">M</span>
				<%
					if (request.getParameter("photoUrl") != null && !request.getParameter("photoUrl").isEmpty()) {
				%>
				<span class="title__profile-pic"
					style="background-image: url('${param.photoUrl}')"></span>
				<%
					}
				%>
			</h1>
			<h2 class="subtitle">
				<strong>Hello ${param.firstName}!</strong> <a href="/logout"
					class="button is-light button--logout">logout<span id="logoutTime"></span></a>
			</h2>
		</div>
	</div>
	<div class="hero-foot">
		<div class="tabs is-boxed is-centered">
			<ul>
				<li class="${param.currentTab eq 'profile' ? 'is-active' : ''}"><a
					href="/employee">Profile</a></li>
				<li class="${param.currentTab eq 'shifts' ? 'is-active' : ''}"><a
					href="/employee/shifts">Shifts</a></li>
				<li class="${param.currentTab eq 'notes' ? 'is-active' : ''}"><a
					href="/employee/notes">Notes</a></li>
				<%
					if (request.getParameter("hasBandNotes") != null) {
				%>
				<li class="${param.currentTab eq 'band-notes' ? 'is-active' : ''}"><a
					href="/employee/band-notes">Band Notes</a></li>
				<%
					}
				%>
			</ul>
		</div>
	</div>
</section>
