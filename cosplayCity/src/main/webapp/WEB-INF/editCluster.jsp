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
			<c:if test="${cluster.getUser().getProfile().getProfilePhoto() != null}">
				<img alt="${cluster.getUser().getUserName()}'s profile photo" src="/${cluster.getUser().getProfile().getProfilePhoto()}">
			</c:if>
			<c:if test="${cluster.getUser().getProfile().getProfilePhoto() == null}">
				<a href="/editProfile/${cluster.getUser().getProfile().getUser().getId()}"><img alt="Cosplay City Generic Profile Photo" src="/images/generic_profile_photo.jpg"></a>
			</c:if>
			<aside>
				<h1><c:out value="${loggedInUser.getUserName()}"></c:out></h1>
				<h3><a href="/clusterPage/${cluster.getId()}"><c:out value="${cluster.getTitle()}"></c:out></a></h3>
				<h3>Edit Cluster</h3>
			</aside>
			<form action="/deleteCluster/${cluster.getId()}" method="post" class="deleteButton">
				<input type="hidden" name="_method" value="delete">
				<button type="submit">Delete Cluster</button>
			</form>
		</section>
		<hr>
		<section class="formContainer">
			<form:form action="/processEditCluster/${cluster.getId()}" method="post" modelAttribute="cluster"> 
				<h3>Edit Cluster</h3>
				<form:errors path="title" class="error"></form:errors>
				<form:label path="title">Title:
					<form:input path="title"/>
				</form:label>
				<form:errors path="description" class="error"></form:errors>
				<form:label path="description">Description:
					<form:textarea path="description" maxlength="240" cols="36"/>
				</form:label>
				<form:errors path="content" class="error"></form:errors>
				<label>Reupload Photos: 
					<input type="file" name="file" multiple="true" class="fileUpload"/>
				</label>
				<button type="submit">Submit</button>
			</form:form>
		</section>
	</main>
</body>
</html>