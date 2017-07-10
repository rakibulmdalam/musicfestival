<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param name="pageTitle" value="Timetable" />
</jsp:include>
<body>
	<jsp:include page="visitorHeader.jsp">
		<jsp:param name="firstName" value="Helene" />
		<jsp:param name="photoUrl"
			value="https://uinames.com/api/photos/female/21.jpg" />
		<jsp:param name="currentTab" value="timetable" />
	</jsp:include>
	<div class="tabs is-centered is-marginless">
		<ul>
			<li class="is-active"><a href="/visitor/timetable">Your picks</a></li>
			<li><a href="/visitor/timetable/search">Search</a></li>
		</ul>
	</div>
	<section class="section">
		<div class="columns">
			<div class="column is-6 is-offset-3">
				<dl class="section__festival-day">
					<dt class="festival-day-label">Tuesday, 25th July 2017</dt>
					<dd class="box">
						<article class="media">
							<div class="media-left">
								<figure class="image band-image">
									<img
										src="https://yt3.ggpht.com/-hPmFo3y5L_k/AAAAAAAAAAI/AAAAAAAAAAA/T7BtIb0v-xM/s900-c-k-no-mo-rj-c0xffffff/photo.jpg"
										alt="Image">
								</figure>
							</div>
							<div class="media-content">
								<div class="content">
									<p class="title is-4">
										<strong>M83</strong>
									</p>
									<p class="subtitle is-6">Main stage, 2pm - 4pm</p>
									<div class="content is-small">
										<p>Genre: Electropop<br>Songs: Midnight City, Wait, We Own The Sky, Reunion,
											Outro</p>
									</div>
								</div>
							</div>
							<div class="media-right">
								<button class="delete"></button>
							</div>
						</article>
					</dd>
					<dd class="box">
						<article class="media">
							<div class="media-left">
								<figure class="image band-image">
									<img
										src="http://www.tychomusic.com/epoch-img/epoch-album-cover.png"
										alt="Image">
								</figure>
							</div>
							<div class="media-content">
								<div class="content">
									<p class="title is-4">
										<strong>Tycho</strong>
									</p>
									<p class="subtitle is-6">Main stage, 4pm - 6pm</p>
									<div class="content is-small">
										<p>Genre: Electronic<br>Songs: Coastal Brake, See, Hours, Awake, Dive, Sunrise
											Projector, A Walk</p>
									</div>
								</div>
							</div>
							<div class="media-right">
								<button class="delete"></button>
							</div>
						</article>
					</dd>
				</dl>
				<dl class="section__festival-day">
					<dt class="festival-day-label">Wednesday, 26th July 2017</dt>
					<dd class="box">
						<article class="media">
							<div class="media-left">
								<figure class="image band-image">
									<img
										src="http://static.spin.com/files/2017/06/WashedOut-1497280080.jpg"
										alt="Image">
								</figure>
							</div>
							<div class="media-content">
								<div class="content">
									<p class="title is-4">
										<strong>Washed Out</strong>
									</p>
									<p class="subtitle is-6">Stage A, 6pm - 7pm</p>
									<div class="content is-small">
										<p>Genre: Chillwave<br>Songs: Feel It All Around, It All Feels Right, Amor
											Fati, New Theory, Eyes Be Closed, All I Know, Get Lost, Hard
											To Say Goodbye</p>
									</div>
								</div>
							</div>
							<div class="media-right">
								<button class="delete"></button>
							</div>
						</article>
					</dd>
					<dd class="box">
						<article class="media">
							<div class="media-left">
								<figure class="image band-image">
									<img
										src="http://static.spin.com/files/2017/06/WashedOut-1497280080.jpg"
										alt="Image">
								</figure>
							</div>
							<div class="media-content">
								<div class="content">
									<p class="title is-4">
										<strong>Washed Out</strong>
									</p>
									<p class="subtitle is-6">Stage A, 6pm - 7pm</p>
									<div class="content is-small">
										<p>Genre: Chillwave<br>Songs: Feel It All Around, It All Feels Right, Amor
											Fati, New Theory, Eyes Be Closed, All I Know, Get Lost, Hard
											To Say Goodbye</p>
									</div>
								</div>
							</div>
							<div class="media-right">
								<button class="delete"></button>
							</div>
						</article>
					</dd>
					<dd class="box">
						<article class="media">
							<div class="media-left">
								<figure class="image band-image">
									<img
										src="http://static.spin.com/files/2017/06/WashedOut-1497280080.jpg"
										alt="Image">
								</figure>
							</div>
							<div class="media-content">
								<div class="content">
									<p class="title is-4">
										<strong>Washed Out</strong>
									</p>
									<p class="subtitle is-6">Stage A, 6pm - 7pm</p>
									<div class="content is-small">
										<p>Genre: Chillwave<br>Songs: Feel It All Around, It All Feels Right, Amor
											Fati, New Theory, Eyes Be Closed, All I Know, Get Lost, Hard
											To Say Goodbye</p>
									</div>
								</div>
							</div>
							<div class="media-right">
								<button class="delete"></button>
							</div>
						</article>
					</dd>
				</dl>
			</div>
		</div>
	</section>
</body>
</html>