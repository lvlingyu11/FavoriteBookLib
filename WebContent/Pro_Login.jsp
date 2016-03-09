<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Please Login Here</title>
<style type="text/css">
body{
color:purple;
background-color: #d8da3d} 
</style>
</head>
<body>
<h1>Welcome</h1>
<%
String error_message="";
Object error=request.getAttribute("error");
if(error!=null)
	error_message=error.toString();
%>
<form action="Pro_Login_Servlet" method="post">
<table cellspacing=4>
<tr>
<td>User ID:</td>
<td><input name="User_ID" type="text" size="20"></td>
</tr>
<tr>
<td>Password:</td>
<td><input name="Password" type="password" size="15"></td>
</tr>
<tr>
<td></td>
<td><input type="submit" value="Login"></td>
<td style="color:red"><%=error_message %>
</tr>
</table>
</form>
</body>
</html>