<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Open Account</title>
</head>
<body>
<form name="login" action="Open">
Name:<input type="text" name="name" value=""><br>
Age:<input type="text" name="age" value=""><br>
Address:<input type="text" name="address" value=""><br>
Contact:<input type="text" name="contact" value=""><br>
Gender:<br><input type="radio" name="sex" value="Male">Male<br>
<input type="radio" name="sex" value="Female">Female<br>
<input type="radio" name="sex" value="Other">Other<br>
Adhaar no.:<input type="text" name="adhaar" value=""><br>

<input type="submit" value="Create"></input>
</form>
</body>
</html>