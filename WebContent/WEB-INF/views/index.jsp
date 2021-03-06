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
	<form action="" method="get">
	<label for="filmById">Film Id:</label> <input type="text" name="filmById">
	<input type="submit" value="Search">
	</form>
</div>
<div>
	<h3>Search by Keyword</h3>
	<form action="" method="get">
	<label for="keyWord">Keyword:</label> <input type="text" name="keyWord">
	<input type="submit" value="Search">
	</form>
</div>


</body>
</html>