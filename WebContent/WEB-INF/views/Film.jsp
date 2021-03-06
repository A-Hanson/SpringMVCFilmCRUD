<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Film</title>
</head>
<body>

<c:choose>

<c:when test="${ not empty film }">

<h4>Film Information</h4>
<table border= "1">
<tr>
<th>Title</th>
<th>Description</th>
<th>Release Year</th>
<th>Language</th>
<th>Rental Duration</th>
<th>Rental Rate</th>
<th>Length</th>
<th>Replacement Cost</th>
<th>Rating</th>
<th>Special Features</th>
<th>Category</th>
<!-- <th>Actors</th> -->
<!-- <th>Inventory</th> -->
</tr>
<tr>
<td>${film.title}</td>
<td>${film.description}</td>
<td>${film.releaseYear}</td>
<td>${film.language}</td>
<td>${film.rentalDuration}</td>
<td>${film.rentalRate}</td>
<td>${film.length}</td>
<td>${film.replacementCost}</td>
<td>${film.rating}</td>
<td>${film.specialFeatures}</td>
<td>${film.category}</td>
	<td> 
		<ol>
			<c:forEach var ="actor" items="${film.actors }">
				<li> ${actor.firstName} ${actor.lastName}	</li>
			</c:forEach>
	
		</ol>	
	</td>
<%-- <td>${film.inventory}</td> --%>
</tr>
</table>
<a href="goToUpdatePage.do">Update this film</a>
<a href="home.do">Go Back</a>
</c:when>

<c:otherwise>

<h1>Film not found</h1>

<a href="home.do">Go Back</a>

</c:otherwise>

</c:choose>

<p>

</form>
</p>

</body>
</html>