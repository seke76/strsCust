<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Show All Users</title>
</head>

<body>

<jsp:include page="menu.jsp"/>


    <table border=1>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${tenants}" var="tenant">
                <tr>
                <td><c:out value="${tenant.name}" /></td>
                	<td><c:out value="${tenant.tenantid}" /></td>
                    <td><c:out value="${tenant.description}" /></td>
                    <td><a href="servlet?action=tenant&tenantId=<c:out value="${tenant.tenantid}"/>">Details</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="UserController?action=insert">Add User</a></p>
</body>
</html>