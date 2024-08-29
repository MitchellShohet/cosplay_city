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
		<c:forEach var="eachCluster" items="${allClusters}">
			<div class="clusterCard">
				<div class="photoUserNameCombo">
					<c:if test="${eachCluster.getUser().getProfile().getProfilePhoto() != null}">
						<img alt="${eachCluster.getUser().getUserName()}'s profile photo" src="/${eachCluster.getUser().getProfile().getProfilePhoto()}" class="profilePhotoSmall">
					</c:if>
					<c:if test="${eachCluster.getUser().getProfile().getProfilePhoto() == null}">
						<a href="/editProfile/${profile.getUser().getId()}"><img alt="Cosplay City Generic Profile Photo" src="/images/generic_profile_photo.jpg" class=></a>
					</c:if>
					<h3><a href="/profile/${eachCluster.getUser().getId()}"><c:out value="@${eachCluster.getUser().getUserName() }"></c:out></a></h3>
					<c:if test="${loggedInUser.getId() == eachCluster.getUser().getId()}">
						<a class="editButton" href="/editCluster/${eachCluster.getId()}"><hr><hr><hr></a>
					</c:if>
				</div>
				<h3><a href="/clusterPage/${eachCluster.getId()}"><c:out value="${eachCluster.getTitle() }"></c:out></a></h3>
				<hr>
				<div class="contentContainer">
					<c:forEach var="eachContent" items="${eachCluster.getContent() }">
						<img alt="Image of ${eachCluster.getUser().getUserName()}'s ${eachCluster.getTitle()} cosplay" src="/${eachContent }" class="clusterCardContent">
					</c:forEach>
				</div>
			</div>
		</c:forEach>
	</main>
</body>
</html>