<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><c:out value="@${profile.getUser().getUserName()} | Profile"></c:out></title>
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
			<c:if test="${profile.getProfilePhoto() != null}">
				<img alt="${profile.getUser().getUserName()}'s profile photo" src="/${profile.getProfilePhoto()}">
			</c:if>
			<c:if test="${profile.getProfilePhoto() == null}">
				<a href="/editProfile/${profile.getUser().getId()}"><img alt="Cosplay City Generic Profile Photo" src="/images/generic_profile_photo.jpg"></a>
			</c:if>
			<aside>
				<h1><c:out value="@${profile.getUser().getUserName()}"></c:out></h1>
				<h5><c:out value="${profile.getPronouns()}"></c:out></h5>
				<p><c:out value="${profile.getBio()}"></c:out></p>
				<c:if test="${profile.getBio() == null}">
					<p><c:out value="${profile.getUser().getUserName()} hasn't updated their bio yet.."></c:out></p>
				</c:if>
			</aside>
			<c:if test="${loggedInUser.getId() == profile.getUser().getId()}">
				<a class="editButton" href="/editProfile/${profile.getUser().getId()}">
					<span style="margin: 7px 0px;"></span>
					<span style="margin: 7px 0px;"></span>
					<span style="margin: 7px 0px;"></span>
				</a>
			</c:if>
		</section>
		<hr>
		<section class="contentArea">
			<section class="clusterArea">
				<h3>Clusters</h3>
				<div class="clusterGridContainer">
					<c:forEach var="eachCluster" items="${profile.getUser().getClusters()}">
						<div class="clusterThumbnail">
							<a href="/clusterPage/${eachCluster.getId()}">
								<img alt="Thumbnail Image for ${eachCluster.getTitle()} Cluster" src="/${eachCluster.getContent().get(0)}" class="testImgHover">
							</a> 
							<h5><c:out value="${eachCluster.getTitle()}"></c:out></h5>
						</div>
					</c:forEach> 
					<c:if test="${loggedInUser.getId() == profile.getUser().getId()}">
						<div>
							<a href="/newCluster" class="newCluster">+</a> 
							<h5><a href="/newCluster">Add New</a></h5>
						</div>
					</c:if>
				</div>
			</section>
			<hr class="verticalLine">
			<section class="upcomingArea">
				<h3>Upcoming</h3>
				<ul>
					<c:forEach var="eachUpcoming" items="${profile.getUser().getUpcomings()}">
						<li><c:out value="${eachUpcoming.getName()}"></c:out></li>
					</c:forEach>
				</ul>
				<c:if test="${profile.getUser().getUpcomings().isEmpty()}">
					<p><c:out value="${profile.getUser().getUserName()} doesn't have any upcoming projects!"></c:out></p>
				</c:if>		
				<c:if test="${loggedInUser.getId() == profile.getUser().getId()}">
					<a href="/editProfile/${profile.getUser().getId()}" class="submitButton">Add New</a>
				</c:if>
			</section>
		</section>
	</main>
</body>
</html>

