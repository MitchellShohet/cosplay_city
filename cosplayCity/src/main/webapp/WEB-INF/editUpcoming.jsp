<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><c:out value="${upcoming.getName()} | Edit"></c:out></title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
	<header>
		<h2><a href="/discover">Cosplay City</a></h2>
		<nav>
			<hr class="verticalLine">
			<a href="/discover" class="navButton">Home</a>
			<hr class="verticalLine">	
			<a href="/profile/${loggedInUser.getId()}" class="navButton">Profile</a>
			<hr class="verticalLine">
			<a href="/processLogout" class="navButton">Logout</a>
			<hr class="verticalLine">
		</nav>
	</header>
	<main class="formContainer">
		<c:if test="${loggedInUser.getId() == upcoming.getUser().getId()}">
			<a class="editButton" href="/editProfile/${upcoming.getUser().getId()}">
				<span style="margin: 7px 0px;"></span>
				<span style="margin: 7px 0px;"></span>
				<span style="margin: 7px 0px;"></span>
			</a>
		</c:if>
		<div>
			<h3>Edit Upcoming Project:</h3>
			<form:form action="/processEditUpcoming/${upcoming.getId()}" method="post" modelAttribute="upcoming"> 
				<form:label path="name">
					<form:input path="name"/>
				<form:errors path="name" class="error"></form:errors>
				</form:label>
				<button type="submit" class="noMarginSubmit">Submit</button>
			</form:form>
		</div>
	</main>
</body>
</html>