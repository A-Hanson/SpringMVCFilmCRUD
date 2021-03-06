<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Film List</title>
</head>
<body>
Films Found:
<ol>
<c:forEach var="film" items="${film}">
<li> 
<a href="filmInfo?film_id=${film.id}">${film.title} ${film.description}</a>
</li>
</c:forEach>
</ol>
<p>
</p>
</body>
</html>