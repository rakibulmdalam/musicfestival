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
			<li class="is-active"><a>Your picks</a></li>
			<li><a>Search</a></li>
		</ul>
	</div>
	<section class="section">
		<div class="columns">
			<div class="column is-two-thirds is-offset-2 content">
				<h2>
					<strong>Tuesday, 25th July 2017</strong>
				</h2>
				<div class="box">
					<article class="media">
						<div class="media-left">
							<figure class="image is-200x200">
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
								<p class="subtitle is-6">Main stage, 2pm</p>
								<p>M83 is a French electronic music project currently based
									in Los Angeles.</p>
								<div class="content is-small">
									<p>Songs: Midnight City, Wait, We Own The Sky, Reunion,
										Outro</p>
								</div>
							</div>
						</div>
						<div class="media-right">
							<button class="delete"></button>
						</div>
					</article>
				</div>
				<div class="box">
					<article class="media">
						<div class="media-left">
							<figure class="image is-200x200">
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
								<p class="subtitle is-6">Main stage, 4pm</p>
								<p>Tycho (/ˈtaɪkoʊ/, like TIE-ko) is an American ambient
									music project led by Scott Hansen (born 1977) as primary
									composer, songwriter and producer. Hailing from San Francisco,
									California, he is known as ISO50 for his photographic and
									design works. His music is a combination of downtempo
									vintage-style synthesizers and ambient melodies.</p>
								<div class="content is-small">
									<p>Songs: Coastal Brake, See, Hours, Awake, Dive, Sunrise
										Projector, A Walk</p>
								</div>
							</div>
						</div>
						<div class="media-right">
							<button class="delete"></button>
						</div>
					</article>
				</div>
				<h2>
					<strong>Wednesday, 26th July 2017</strong>
				</h2>
				<div class="box">
					<article class="media">
						<div class="media-left">
							<figure class="image is-200x200">
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
								<p class="subtitle is-6">Stage A, 6pm</p>
								<p>Ernest Greene (born October 3, 1982), known
									professionally as Washed Out, is an American singer,
									songwriter, and record producer.</p>
								<p class="content is-small">Songs: Feel It All Around, It
									All Feels Right, Amor Fati, New Theory, Eyes Be Closed, All I
									Know, Get Lost, Hard To Say Goodbye</p>
							</div>
						</div>
						<div class="media-right">
							<button class="delete"></button>
						</div>
					</article>
				</div>
			</div>
		</div>
	</section>
</body>
</html>