<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Presidents</title>
</head>
<body>
Presidents:
<ol>
<c:forEach var="pres" items="${presidents}">
<li> 
<a href="presidentInfo?termNumber=${pres.termNumber}">${pres.firstName} ${pres.middleName} ${pres.lastName}</a>
</li>
</c:forEach>
</ol>
<p>
</p>
</body>
</html>