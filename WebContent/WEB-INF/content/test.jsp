<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TEST USER</title>
</head>
<body>
	<h1>TEST USER</h1>
	<hr />
	<h2>Get Identify Code</h2>
	<form action="/CheckoutServer/user/getIdentifyCode" method="post">
		Phone Number:<input type="text" name="phone" /><br /> 
		<input type="submit" value="Get Identify Code" />
	</form>
	<hr />
	<h2>Get Password</h2>
	<form action="/CheckoutServer/user/login" method="post">
		Phone Number:<input type="text" name="phone" /><br /> 
		Identify Code:<input type="text" name="identifyCode" /><br />
		<input type="submit" value="Get Password" />
	</form>
		<hr />
	<h2>Login Identify</h2>
	<form action="/CheckoutServer/user/identify" method="post">
		Phone Number:<input type="text" name="phone" /><br /> 
		Password:<input type="text" name="password" /><br />
		<input type="submit" value="Login Identify" />
	</form>
</body>
</html>