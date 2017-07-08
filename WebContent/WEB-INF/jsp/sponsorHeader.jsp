<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<section class="hero is-small">
	<div class="hero-body">
		<div class="container has-text-centered">
			<h1 class="title is-1 is-spaced title--branding">
				<span class="title__logo">M</span>
			</h1>
			<h2 class="subtitle is-marginless">Hello ${param.name}!</h2>
			<a href="/logout" class="button is-small is-light button--logout">logout</a>
		</div>
	</div>
</section>
<div class="tabs is-boxed is-centered">
	<ul>
		<li class="${param.currentTab eq 'profile' ? 'is-active' : ''}"><a href="/sponsor">Profile</a></li>
		<%
			if (request.getParameter("hasProducts") != null) {
		%>
			<li class="${param.currentTab eq 'products' ? 'is-active' : ''}"><a href="/sponsor/products">Products</a></li>
			<li class="${param.currentTab eq 'statistics' ? 'is-active' : ''}"><a href="/sponsor/products/statistics">Statistics</a></li>
		<%
			}
		%>
	</ul>
</div>