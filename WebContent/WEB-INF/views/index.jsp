<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC Film Site</title>
</head>
<body>
<h1>Welcome to the Film Site</h1>


<div>
	<h3>Find a Film by it's Id</h3>
	<form action="findFilmById.do" method="get">
	<label for="findFilmById">Film Id:</label> <input type="text" name="filmid">
	<input type="submit" value="Search">
	</form>
</div>
<div>
	<h3>Search by Keyword</h3>
	<form action="findFilmByKeyword.do" method="get">
	<label for="findFilmByKeyword">Keyword:</label> <input type="text" name="findFilmByKeyword">
	<input type="submit" value="Search">
	</form>
</div>


</body>
</html>