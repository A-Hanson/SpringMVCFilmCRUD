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
<p>
<h4>President Information</h4>
<table border= "1">
<tr>
<th>Term Number</th>
<th>Name</th>
<th>Term Start</th>
<th>Term End</th>
<th>Party</th>
<th>Elections Won</th>
<th>Reason for Leaving</th>
</tr>
<tr>
<td>${president.termNumber}</td>
<td>${president.firstName} ${president.middleName} ${president.lastName} </td>
<td>${president.termStart}</td>
<td>${president.termEnd} </td>
<td>${president.party} </td>
<td>${president.electionsWon} </td>
<td>${president.whyLeftOffice} </td>
</tr>
</table>
</p>

<script type="text/javascript">Hello</script>
</body>
</html>