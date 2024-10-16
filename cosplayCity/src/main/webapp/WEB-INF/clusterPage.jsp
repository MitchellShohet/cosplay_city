<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><c:out value="${cluster.getTitle()} | @${cluster.getUser().getUserName()}"></c:out></title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<script type="text/javascript" src="/js/script.js"></script>
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
				<h1><c:out value="${cluster.title}"></c:out></h1>
				<h3><a href="/profile/${cluster.getUser().getId()}"><c:out value="@${cluster.getUser().getUserName()}"></c:out></a></h3>
				<p><c:out value="${cluster.getDescription()}"></c:out>
			</aside>
			<c:if test="${loggedInUser.getId() == cluster.getUser().getId()}">
				<a class="editButton" href="/editCluster/${cluster.getId()}">
					<span style="margin: 7px 0px;"></span>
					<span style="margin: 7px 0px;"></span>
					<span style="margin: 7px 0px;"></span>
				</a>
			</c:if>
		</section>
		<hr>
		<section class="clusterGridContainer">
			<c:forEach var="eachContent" items="${cluster.getContent() }">
				<img alt="Image of ${cluster.getUser().getUserName()}'s ${cluster.getTitle()} cosplay" src="/${eachContent}" onclick="openModal(this)" class="clusterPageImg">
				<div id="myModal" class="modal">
					<span class="close" onclick="closeModal()">&times;</span>
					<img class="modalContent" id="modalContent">
				</div>
			</c:forEach>
		</section>
	</main>
</body>
</html>