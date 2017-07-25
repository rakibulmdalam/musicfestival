<head>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<link
	href="https://fonts.googleapis.com/css?family=Karla:400,400i,700,700i|Muli:900"
	rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/css/bulma.min.css" />
<link rel="stylesheet" type="text/css" href="/css/app.css" />
<link rel="stylesheet" type="text/css" href="/css/timetable.css" />
<script src="/js/app.js"></script>
<script type="text/javascript">
	<% if(session.getAttribute("loginTime") != null) { %>
	var logoutTime = new Date('<%=(new SimpleDateFormat("EE MMM d y H:m:s ZZZ").format((Date) session.getAttribute("loginTime"))) %>').getTime();

	window.onload = function() {
		var logoutTimeSpan = document.getElementById('logoutTime');
		
		setInterval(function() {
			var diff = ~~((logoutTime - new Date().getTime() + 900000) / 1000);			
			var s = ((~~(diff / 60) < 10) ? '0' : '') + (~~(diff / 60)) + ':' + ((diff % 60 < 10) ? '0' : '') + (diff % 60);
			
			if(diff < 0) {
				window.location.href = '/logout';
			}

			logoutTimeSpan.innerHTML = '&nbsp;(' + s + ')';
		}, 1000);
	}
	<% } %>
</script>
<title>M: The Music Festival | ${param.pageTitle}</title>
</head>