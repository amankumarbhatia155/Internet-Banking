<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<script type="text/javascript"
src="function.js"></script>
</head>
<body>
<center>
<form name="login" action="Login">
User name:<input type="text"  name="usrname" value="" id="1"><br>
Password:<input type="password" name="pwd" value="" id="2"><br>
<input type="button" value="SignUp" onclick="signup()">
<input type="submit" value="SIgnIn" >
</form>
</center>
</body>
</html>