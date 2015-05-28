<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Iterator"%>
<%@page import="seklund.Cow"%>
<%@page import="java.util.ArrayList"%>    
<%@ page import="java.sql.*" %>
<jsp:useBean id="dataManager" scope="application"
  class="seklund.DataManager"/>

<%
String con= (String) request.getAttribute("connection");

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method="GET" action='Controller' name="showall">
<table>
  <tr>
    <td>Database connection:</td>
    <td><%=con%></td>
    <td></td>
  </tr> 
  
</table>

<p><input type="submit" name="Show youngest" value="Show youngest" />&nbsp; 
   <input type="submit" name="edit" value="Show oldest" />&nbsp; 
   <input type="reset" value="Show all cows" /></p>
</form>

<h2>Cows</h2>
<table border=1>
  <tr>
    <th>Name</th>
    <th>Age</th>
    </tr>
<%
	ArrayList<Cow> cows = dataManager.getCows(dataManager);
	Iterator<Cow> iterator = cows.iterator();
	while (iterator.hasNext()) {
		Cow cow = (Cow)iterator.next();
  %>

     <tr>
       <td><%=cow.getName()%></td>
       <td><%=cow.getAge()%></td>
     </tr>
<%
     }
%>

</table>

</body>
</html>