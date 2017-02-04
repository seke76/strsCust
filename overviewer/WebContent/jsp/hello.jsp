<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Overviewer</title>
</head>
<body>

<h2>StreamServe R16 Environment overview</h2>
<br>


<form action="../servlet" method="post">
<select name="userSelect">
  <option value="config">Check config</option>
  <option value="MT">Multi-tenant</option>
  <option value="T">Tenant</option>
  <option value="app">Applications</option>
</select>
<input type="submit" value="Check">
</form>


</body>
</html>

