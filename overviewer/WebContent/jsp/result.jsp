<%@ page import="java.util.*"%>
<%@ page import="seklund.model.Tenant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Result.jsp i jsp katalogen
<br />


<%
ArrayList<Tenant> tenants = (ArrayList) request.getAttribute("tenants");

if (tenants != null) {
	Iterator<Tenant> it = tenants.iterator();
	while (it.hasNext() ){
		Tenant tenant = (Tenant) it.next();
		out.print("<br>Tenant: " + tenant.getName() );
		
	}
} %>

<br /><a href="<%= request.getRequestURI() %>">BACK</a>
</body>
</html>