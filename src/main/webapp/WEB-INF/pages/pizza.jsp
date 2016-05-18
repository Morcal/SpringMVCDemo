<%--
  Created by IntelliJ IDEA.
  User: lyqdhgo
  Date: 2016/5/18
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Pizza JSP View</title>
</head>
<body>
<table border="1">
    <tr>
        <td>NAME</td>
        <td>Flavor</td>
        <td>Toppings</td>
    </tr>
    <tr>
        <td>${pizza.name}</td>
        <td>${pizza.flavor}</td>
        <td>
            <c:forEach var="item" items="${pizza.toppings}">
                <c:out value="${item}"/>&nbsp;
            </c:forEach>
        </td>
    </tr>
</table>
</body>
</html>
