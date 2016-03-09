<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List Page</title>
<style type="text/css">
body{
color:blue;
background-color: #d8da3d
}
</style>
</head>
<body>
<!-- Site navigation menu -->
<ul class="navbar">
<li><a href="Menu.jsp">HomePage</a>
<li><a href="List.jsp">List</a>
<li><a href="Update.jsp">Update</a>
</ul>
<h1>Welcome to the List Page</h1>
<form action="List_Servlet" method="post">
<table cellspacing=4>
<tr>
<td>Please Select One of the Tables and Click "List" to List the Table Contents!</td>
</tr>
<tr>
<td><input type="radio" name="table" value="Users_List">Users_List</td>
</tr>
<tr>
<td><input type="radio" name="table" value="Favoriate_Book">Favoriate_Book</td>
</tr>
<tr>
<td><input type="radio" name="table" value="Book_Info">Book_Info</td>
</tr>
<tr>
<td></td>
<td><input type="submit" value="List"></td>
</tr>
</table>
</form>
<p>
<b>Table Content:</b><br> ${sqlResults}
</p>
</body>
</html>