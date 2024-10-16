<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register | Cosplay City</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
	<main class="loginContainer">
		<section>
			<img alt="cosplay city logo" src="/images/cosplay_city_logo.PNG">
			<h1>Cosplay City</h1>
			<a href="/login">Already Registered?</a>
		</section>
		<hr class="verticalLine">
		<section>
			<h1>Create Your Account</h1>
			<h3 class="error"><c:out value="${loginNeeded}"></c:out></h3>
			<form:form action="/processRegister" method="post" modelAttribute="newUser">
				<form:errors path="userName" class="error"></form:errors>
				<form:label path="userName"> User Name:
					<form:input path="userName"/>
				</form:label>
				<form:errors path="email" class="error"></form:errors>	
				<form:label path="email">Email:
					<form:input path="email"/>
				</form:label>
				<form:errors path="password" class="error"></form:errors>			
				<form:label path="password">Password:
					<form:input type="password" path="password"/>
				</form:label>
				<form:errors path="confirmPassword" class="error"></form:errors>				
				<form:label path="confirmPassword">Confirm PW:
					<form:input type="password" path="confirmPassword"/>
				</form:label>
				<button type="submit">Cosplay!</button>
			</form:form>	
		</section>
	</main>
</body>
</html>