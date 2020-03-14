<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>



<head>
    <title>Flower Shop Cart</title>
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/home.js"/>"></script>
</head>
<body>
<h1>Hello , <span id="username">${username}</span>! , your balance is ${balance}0</h1>

<h2>Flowers Cart</h2>


<%--<form:form action="/doOrder" method="post" modelAttribute="username">--%>
<table name="Cart" class="sortable">
    <tr>
        <td><strong>Flower Name</strong></td>
        <td><strong>Amount</strong></td>
        <td><strong>Summary Price</strong></td>
    </tr>
    <c:forEach items="${cart}" var="cartItem">
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
