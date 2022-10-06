<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	   pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.2.1/dist/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
</head>
<body>
	<div class="container">
	<h3>Student Directory - Add/Update student</h3>
		<hr>
			<form action="/studentmanagement/students/save" method="POST">
			<input type="hidden" name="id" value="${Student.id}" />
				<input type="text" name="name" placeholder="Student Name" value="${Student.name}" />
				<input type="text" name="department" placeholder="Department" value="${Student.department}" />
				<input type="text" name="country" placeholder="Country" value="${Student.country}" />
				<button type="submit">Save Student</button>
			</form>
			<a href="/studentmanagement/students/list">Back to students list</a>
	</div>
</body>
</html>