<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<section class="hero is-small">
	<div class="hero-body">
		<div class="container has-text-centered">
			<h1 class="title is-1 title--branding is-spaced is-inline-flex">
				<span class="title__logo">M</span>
				<%
					if (request.getParameter("photoUrl") != null) {
				%>
				<span class="title__profile-pic"
					style="background-image: url('${param.photoUrl}')"></span>
				<%
					}
				%>
			</h1>
			<h2 class="subtitle">
				Hello ${param.firstName}! <a href="/logout"
					class="button is-light button--logout">logout</a>
			</h2>

		</div>
	</div>
</section>
<div class="tabs is-boxed is-centered">
	<ul>
		<li class="${param.currentTab eq 'profile' ? 'is-active' : ''}"><a
			href="/visitor">Profile</a></li>
		<li class="${param.currentTab eq 'timetable' ? 'is-active' : ''}"><a
			href="/visitor/timetable">Timetable</a></li>
		<li class="${param.currentTab eq 'purchases' ? 'is-active' : ''}"><a
			href="/visitor/purchases">Purchases</a></li>
	</ul>
</div>