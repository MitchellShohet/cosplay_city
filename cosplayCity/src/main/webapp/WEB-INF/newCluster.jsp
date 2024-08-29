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
	<main>
		<section class="userInfoArea">
			<c:if test="${loggedInUser.getProfile().getProfilePhoto() != null}">
				<img alt="${loggedInUser.getUserName()}'s profile photo" src="/${loggedInUser.getProfile().getProfilePhoto()}">
			</c:if>
			<c:if test="${loggedInUser.getProfile().getProfilePhoto() == null}">
				<a href="/editProfile/${cluster.getUser().getProfile().getUser().getId()}"><img alt="Cosplay City Generic Profile Photo" src="/images/generic_profile_photo.jpg"></a>
			</c:if>
			<aside>
				<h1><c:out value="${loggedInUser.getUserName()}"></c:out></h1>
				<h3>Add New Cluster</h3>
			</aside>
		</section>
		<hr>
		<section class="formContainer">
			<form:form action="/processNewCluster" method="post" modelAttribute="newCluster" enctype="multipart/form-data">
				<h1>Add New Cluster</h1>
				<form:label path="title">Title:
					<form:input path="title"/>
					<form:errors path="title" class="error"></form:errors>
				</form:label>
				<form:label path="description">Description:
					<form:textarea path="description" maxlength="240" cols="34"/>
					<form:errors path="description" class="error"></form:errors>
				</form:label>
				<label value="photosInput">Upload photos: 
					<input type="file" name="file" multiple="true" class="fileUpload"/>
					<form:errors path="content" class="error"></form:errors>
				</label>
				<button type="submit" class="submitButton">Submit</button>
			</form:form>
		</section>
	</main>
</body>
</html>