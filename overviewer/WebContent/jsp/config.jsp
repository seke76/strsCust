<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Config</title>
</head>
<body>

<h1>Config</h1>
<jsp:include page="menu.jsp"/>

<%
out.println("<br/>Your IP- address is " + request.getRemoteAddr());
String userAgent = request.getHeader("user-agent");
String browser = "unknown";
out.print("<br/>and your browser is ");

if (userAgent != null) {
	if (userAgent.indexOf("MSIE") > -1) {
		browser = "MS Internet Explorer";
	}
	else if (userAgent.indexOf("Firefox") > -1) {
		browser = "Mozilla Firefox";
	}
}
out.println(browser+"<br/>");
out.println("<br/> Database connection: ");

ServletContext context = getServletContext();
out.println( context.getAttribute("connection") );
out.println("<br/>");
%>



</body>
</html>

