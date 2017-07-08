<section class="hero is-small">
	<div class="hero-body">
		<div class="container has-text-centered">
			<h1 class="title is-1 is-spaced title--branding">
				<span class="title__logo">M</span>
			</h1>
			<h2 class="subtitle is-marginless">Hello ${param.firstName}!</h2>
			<a href="/logout" class="button is-small is-light button--logout">logout</a>
		</div>
	</div>
</section>
<div class="tabs is-boxed is-centered">
	<ul>
		<li class="${param.currentTab eq 'profile' ? 'is-active' : ''}"><a href="/admin">Profile</a></li>
		<li class="${param.currentTab eq 'notes' ? 'is-active' : ''}"><a href="/admin/notes">Notes</a></li>
		<li class="${param.currentTab eq 'schedules' ? 'is-active' : ''}"><a href="/admin/schedules">Schedules</a></li>
	</ul>
</div>