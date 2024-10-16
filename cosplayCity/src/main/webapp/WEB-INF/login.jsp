<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login | Cosplay City</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
	<main class="loginContainer">	
		<section>
			<img alt="" src="/images/cosplay_city_logo.PNG">
			<h1>Cosplay City</h1>
			<a href="/register">Create an Account</a>
		</section>
		<hr class="verticalLine">
		<section>
			<h1>Login</h1>
			<form:form action="/processLogin" method="post" modelAttribute="userLogin">
				<form:errors path="email" class="error"></form:errors>
				<form:label path="email">Email:
					<form:input path="email"/>
				</form:label>
				<form:errors path="password" class="error"></form:errors>
				<form:label path="password">Password:
					<form:input type="password" path="password"/>
				</form:label>
				<button type="submit">Cosplay!</button>
			</form:form>
		</section>
	</main>
</body>
</html>