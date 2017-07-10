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
						<div class="field has-addons">
							<p class="control">
								<span class="select"> <select>
										<option>Band</option>
										<option>Genre</option>
										<option>Stage</option>
								</select>
								</span>
							</p>
							<p class="control is-expanded">
								<input class="input" type="text"
									placeholder="Search by band, genre or stage">
							</p>
							<p class="control">
								<a class="button is-primary"> Search </a>
							</p>
						</div>
					</div>
				</div>
				<div class="field is-horizontal">
					<div class="field-label is-normal">
						<label class="label">Time range</label>
					</div>
					<div class="field-body">
						<div class="field has-addons">
							<p class="control is-expanded">
								<span class="select is-fullwidth"> <select
									name="start-time">
										<option value="11">11 am</option>
										<option value="12">12 noon</option>
										<option value="13">1 pm</option>
								</select>
								</span>
							</p>
						</div>
						<div class="field has-addons">
							<p class="control is-expanded">
								<span class="select is-fullwidth"> <select
									name="end-time">
										<option value="11">11 am</option>
										<option value="12">12 noon</option>
										<option value="13" selected="selected">1 pm</option>
								</select>
								</span>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<section class="section">
		<h2 class="festival-day-label">Tuesday, 25th July 2017</h2>
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
			<div class="stage-label">
				<span class="stage-label__text">Mainstage</span>
			</div>
			<div class="time-slots">
				<div class="time-slot time-slot--fixed time-slot--180mins">
					<div class="time-slot__image" style="background-image: url('https://www.tomorrowland.com/src/Frontend/Files/line-up/artists/600x/85-5880ca9f51eec.jpg')"></div>
					<div class="time-slot__text">
						<h2 class="time-slot__title">Carl Cox</h2>
						<h4 class="time-slot__info">12PM-3PM</h4>
					</div>
					<button class="time-slot__button delete delete--add-button"></button>
				</div>
				<div class="time-slot time-slot--fixed time-slot--60mins">
					<div class="time-slot__image" style="background-image: url('https://www.tomorrowland.com/src/Frontend/Files/line-up/artists/600x/668-595cb88c2bd69.jpg')"></div>
					<div class="time-slot__text">
						<h2 class="time-slot__title">Lucas & Steve</h2>
						<h4 class="time-slot__info">3PM-4PM</h4>
					</div>
					<button class="time-slot__button delete delete--add-button"></button>
				</div>
				<div class="time-slot time-slot--empty time-slot--60mins"></div>
				<div class="time-slot time-slot--fixed time-slot--60mins">
					<div class="time-slot__image" style="background-image: url('https://www.tomorrowland.com/src/Frontend/Files/line-up/artists/600x/226-58860fff53082.jpg')"></div>
					<div class="time-slot__text">
						<h2 class="time-slot__title">Sunnery James & Ryan Marciano</h2>
						<h4 class="time-slot__info">4PM-5PM</h4>
					</div>
					<button class="time-slot__button delete delete--add-button"></button>
				</div>
			</div>
			<div class="stage-label">
				<span class="stage-label__text">Trans energy</span>
			</div>
			<div class="time-slots">
				<div class="time-slot time-slot--empty time-slot--30mins"></div>
				<div class="time-slot time-slot--fixed time-slot--180mins">
					<div class="time-slot__image" style="background-image: url('https://www.tomorrowland.com/src/Frontend/Files/line-up/artists/600x/85-5880ca9f51eec.jpg')"></div>
					<div class="time-slot__text">
						<h2 class="time-slot__title">Carl Cox</h2>
						<h4 class="time-slot__info">12PM-3PM</h4>
					</div>
					<button class="time-slot__button delete delete--add-button"></button>
				</div>
				<div class="time-slot time-slot--empty time-slot--30mins"></div>
				<div class="time-slot time-slot--fixed time-slot--60mins">
					<div class="time-slot__image" style="background-image: url('https://www.tomorrowland.com/src/Frontend/Files/line-up/artists/600x/668-595cb88c2bd69.jpg')"></div>
					<div class="time-slot__text">
						<h2 class="time-slot__title">Lucas & Steve</h2>
						<h4 class="time-slot__info">3PM-4PM</h4>
					</div>
					<button class="time-slot__button delete delete--add-button"></button>
				</div>
				<div class="time-slot time-slot--empty time-slot--60mins"></div>
				<div class="time-slot time-slot--fixed time-slot--60mins">
					<div class="time-slot__image" style="background-image: url('https://www.tomorrowland.com/src/Frontend/Files/line-up/artists/600x/226-58860fff53082.jpg')"></div>
					<div class="time-slot__text">
						<h2 class="time-slot__title">Sunnery James & Ryan Marciano</h2>
						<h4 class="time-slot__info">4PM-5PM</h4>
					</div>
					<button class="time-slot__button delete delete--add-button"></button>
				</div>
			</div>
			<div class="stage-label">
				<span class="stage-label__text">Elrow</span>
			</div>
			<div class="time-slots">
				<div class="time-slot time-slot--empty time-slot--180mins"></div>
				<div class="time-slot time-slot--fixed time-slot--60mins">
					<div class="time-slot__image" style="background-image: url('https://www.tomorrowland.com/src/Frontend/Files/line-up/artists/600x/85-5880ca9f51eec.jpg')"></div>
					<div class="time-slot__text">
						<h2 class="time-slot__title">Carl Cox</h2>
						<h4 class="time-slot__info">12PM-3PM</h4>
					</div>
					<button class="time-slot__button delete delete--add-button"></button>
				</div>
				<div class="time-slot time-slot--empty time-slot--90mins"></div>
				<div class="time-slot time-slot--fixed time-slot--120mins">
					<div class="time-slot__image" style="background-image: url('https://www.tomorrowland.com/src/Frontend/Files/line-up/artists/600x/668-595cb88c2bd69.jpg')"></div>
					<div class="time-slot__text">
						<h2 class="time-slot__title">Lucas & Steve</h2>
						<h4 class="time-slot__info">3PM-4PM</h4>
					</div>
					<button class="time-slot__button delete delete--add-button"></button>
				</div>
				<div class="time-slot time-slot--empty time-slot--60mins"></div>
				<div class="time-slot time-slot--fixed time-slot--60mins">
					<div class="time-slot__image" style="background-image: url('https://www.tomorrowland.com/src/Frontend/Files/line-up/artists/600x/226-58860fff53082.jpg')"></div>
					<div class="time-slot__text">
						<h2 class="time-slot__title">Sunnery James & Ryan Marciano</h2>
						<h4 class="time-slot__info">4PM-5PM</h4>
					</div>
					<button class="time-slot__button delete delete--add-button"></button>
				</div>
			</div>
			<div class="stage-label">
				<span class="stage-label__text">My House</span>
			</div>
			<div class="time-slots">
				<div class="time-slot time-slot--empty time-slot--30mins"></div>
				<div class="time-slot time-slot--fixed time-slot--60mins">
					<div class="time-slot__image" style="background-image: url('https://www.tomorrowland.com/src/Frontend/Files/line-up/artists/600x/226-58860fff53082.jpg')"></div>
					<div class="time-slot__text">
						<h2 class="time-slot__title">Sunnery James & Ryan Marciano</h2>
						<h4 class="time-slot__info">4PM-5PM</h4>
					</div>
					<button class="time-slot__button delete delete--add-button"></button>
				</div>
			</div>
			<div class="stage-label">
				<span class="stage-label__text">Drumcode</span>
			</div>
			<div class="time-slots">
				<div class="time-slot time-slot--empty time-slot--60mins"></div>
				<div class="time-slot time-slot--fixed time-slot--360mins">
					<div class="time-slot__image" style="background-image: url('https://www.tomorrowland.com/src/Frontend/Files/line-up/artists/600x/226-58860fff53082.jpg')"></div>
					<div class="time-slot__text">
						<h2 class="time-slot__title">Sunnery James & Ryan Marciano</h2>
						<h4 class="time-slot__info">4PM-5PM</h4>
					</div>
					<button class="time-slot__button delete delete--add-button"></button>
				</div>
			</div>
		</div>
	</section>
</body>
</html>