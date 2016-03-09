<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Page</title>
<style type="text/css">
body{
color:blue;
background-color: #d8da3d} 
</style>
</head>
<body>
<!-- Site navigation menu -->
<ul class="navbar">
<li><a href="Menu.jsp">HomePage</a>
<li><a href="List.jsp">List</a>
<li><a href="Update.jsp">Update</a>
</ul>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
	<c:if test="${sqlStatement == null}">
		<c:set var="sqlStatement" value="" />
	</c:if>
	<h1>Welcome to the Update Page!</h1>
	<p>Please enter the statement (insert, delete or update) and click execute to show the results.</p>
	<p><b>SQL Statement:</b></p>
	<form action="Update_Servlet" method="post">
		<textarea name="sqlStatement" cols="60" rows="8">${sqlStatement}</textarea>
		<br> <br> <input type="submit" value="Execute">
	</form>
	<p>
	<b>SQL result:</b><br> ${sqlResult}
	</p>
</body>
</html>