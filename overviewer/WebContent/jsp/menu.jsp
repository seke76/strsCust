<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="servlet" method="post">
<select name="userSelect">
  <option value="config">Check config</option>
  <option value="MT">Multi-tenant</option>
  <option value="tenants">Tenants</option>
  <option value="app">Applications</option>
  <option value="nodes">Nodes</option>
  <option value="docbroker">DocBroker Viewer</option>
</select>
<input type="submit" value="Check">
</form>
</body>
</html>