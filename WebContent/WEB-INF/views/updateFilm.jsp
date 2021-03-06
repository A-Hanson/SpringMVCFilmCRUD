<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Film</title>
</head>
<!--       <input type="text" name="abbreviation" value="PR"> -->
<body>
    <form action="UpdateFilm.do" method="POST">
      <input type="hidden" name="id" value="${film.id }">
      <label for="title">Title:</label>
      <input type="text" name="title" value="${film.title }">
      <br>
      <label for="description">Description:</label>
      <input type="text" name="description" value="${film.description }">
      <br>
      <label for="releaseYear">Release Year:</label>
      <input type="text" name="releaseYear" value="${film.releaseYear }">
      <br>
      <label for="rentalDuration">Rental Duration:</label>
      <input type="text" name="rentalDuration" value="${film.rentalDuration }">
      <br>
      <label for="rentalRate">Rental Rate:</label>
      <input type="text" name="rentalRate" value="${film.rentalRate }">
      <br>
      <label for="length">Length:</label>
      <input type="text" name="length" value="${film.length }">
      <br>
      <label for="replacementCost">Replacement Cost:</label>
      <input type="text" name="replacementCost" value="${film.replacementCost }">
      <br>
      <label for="rating">Rating:</label>
      <input type="text" name="rating" value="${film.rating }">
      <br>
      <label for="specialFeatures">Special Features:</label>
      <input type="text" name="specialFeatures" value="${film.specialFeatures }">
      <br>
      <label for="category">Category:</label>
      <input type="text" name="category" value="${film.category }">
      <br>
      <input type="submit" value="Update Film">
    </form>
</body>
</html>