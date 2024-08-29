<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
		<div>
			<h3>Edit Profile:</h3>
			<section class="profileDataContainer">
				<form:form action="/processEditProfile/${profile.getUser().getId()}" method="post" modelAttribute="profile" enctype="multipart/form-data"> 
					<label>Profile Picture:
						<input type="file" name="file" class="fileUpload" placeholder="profile picture">
					</label>
					<form:label path="pronouns"> Pronouns: 
						<form:input path="pronouns"/>
						<form:errors path="pronouns" class="error"></form:errors>
					</form:label>
					<form:label path="bio">Bio: 						
						<form:textarea path="bio" maxlength="150" cols="41"/>
						</form:label>
					<button type="submit" class="submitButton">Submit</button>
				</form:form>
			</section>
			<hr>
			<h4>Upcoming:</h4>
			<c:forEach var="eachUpcoming" items="${user.getUpcomings()}">
				<div class="littleFormContainer">
					<p><c:out value="${eachUpcoming.getName() }"></c:out></p>
					<a href="/editUpcoming/${eachUpcoming.getId()}"><button>Edit</button></a>
					<form action="/deleteUpcoming/${eachUpcoming.getId()}" method="post" class="deleteButton">
						<input type="hidden" name="_method" value="delete">
						<button type="submit" class="noMarginSubmit">Delete</button>
					</form>
				</div>
			</c:forEach>
			<div class="littleFormContainer">
				<form:form action="/processNewUpcoming/${user.getId()}" method="post" modelAttribute="newUpcoming">
					<form:errors path="name" class="error"></form:errors>
					<form:label path="name" >Add New: 
						<form:input path="name"/>
						<button type="submit" class="noMarginSubmit">Submit</button>
					</form:label>
				</form:form>
			</div>
			<hr>
			<h4>Clusters:</h4>
				<c:forEach var="eachCluster" items="${user.getClusters()}">
					<div class="littleFormContainer">
						<p><c:out value="${eachCluster.getTitle()}"></c:out></p>
						<a href="/editCluster/${eachCluster.getId()}"><button>Edit</button></a>
						<form action="/deleteCluster/${eachCluster.getId()}" method="post" class="deleteButton">
							<input type="hidden" name="_method" value="delete">
							<button type="submit" class="noMarginSubmit">Delete</button>
						</form>					
					</div>
				</c:forEach>
			<a href="/newCluster"><button class="submitButton">Add New</button></a>
		</div>
	</main>
</body>
</html>