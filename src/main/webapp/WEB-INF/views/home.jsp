<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<%--<style type="text/css"> <%@include file="css/style.css"%></style>--%>
<%--<script type="text/javascript"><%@include file="js/sorttable.js"%></script>--%>


<head>
    <title>Spring 5 Web MVC Example</title>
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<c:url value="/resources/js/sorttable.js"/>"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/home.js"/>"></script>
</head>
<body>
<h1>Hello , <span id="username">${username}</span>! , your balance is ${balance}0</h1>

<h2>Flowers List</h2>


<%--<form:form action="/addToCart" method="post" modelAttribute="carts">--%>
<table name="Flowers" class="sortable">
    <tr>
        <td><strong>Name</strong></td>
        <td><strong>Price</strong></td>
        <td><strong>Amount</strong></td>
        <td><strong>Order</strong></td>
        <td><strong>Add To Cart</strong></td>
    </tr>
    <c:forEach items="${flowers}" var="flower">
        <tr>
            <td>${flower.name}</td>
            <td>${flower.price}</td>
            <td>${flower.amount}</td>
            <td><input type="number" id="${flower.name}" min="0" max="${flower.amount}" class="text_input"></td>
            <td><button value="add" type="button" id="btn${flower.name}" class="flower_button" onclick="fire_ajax_submit('${username}', '${flower.name}')">add</button></td>
        </tr>
    </c:forEach>
</table>

<%--</form:form>--%>

<span id="test_value"></span>
<%--<display:table name="${flowers}" sort="list">--%>
<%--&lt;%&ndash;    <display:setProperty name="basic.empty.showtable" value="true"/>&ndash;%&gt;--%>
<%--    <display:column property="name" title="Name" sortable="true" headerClass="sortable"/>--%>
<%--    <display:column property="price" title="Price" sortable="true" headerClass="sortable"/>--%>
<%--    <display:column property="amount"  title="Amount" sortable="true" headerClass="sortable"/>--%>
<%--</display:table>--%>
</body>
</html>
