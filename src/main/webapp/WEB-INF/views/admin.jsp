<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Admin Page</title>
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/home.js"/>"></script>
</head>
<body>
<h1>Hello , <span id="username">${username}</span>! , there you can do with orders</h1>
<c:if test="${empty username}"> <c:redirect url="http://localhost:8080/login">Redirecting to login</c:redirect></c:if>
<h2>Flowers Cart</h2>


<%--<form:form action="/doOrder" method="post" modelAttribute="username">--%>
<table name="Orders" class="sortable">
    <tr>
        <td><strong>Order Name</strong></td>
        <td><strong>Open Date</strong></td>
        <td><strong>Close Date</strong></td>
        <td><strong>Summary Price</strong></td>
    </tr>
    <c:forEach items="${orders}" var="orderItem">
        <tr>
            <td>${cartItem.name}</td>
            <td>${cartItem.ordered}</td>
            <td>${cartItem.sumPrice}</td>
        </tr>
    </c:forEach>
    <tr>
        <td>Overall with discount:</td>
        <td>${total}</td>
        <td><a href="<c:url value="/doOrder/${username}"/>"> Do order </a></td>
    </tr>
</table>
<%--</form:form>--%>
<%--<display:table name="${flowers}" sort="list">--%>
</body>
</html>
