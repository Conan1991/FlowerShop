<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<%--<style type="text/css"> <%@include file="css/style.css"%></style>--%>
<%--<script type="text/javascript"><%@include file="js/sorttable.js"%></script>--%>


<head>
    <title>Spring 5 Web MVC Example</title>
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<c:url value="/resources/js/sorttable.js"/>"></script>
</head>
<body>
<h1>Hello , ${username}! your balance is ${balance}0</h1>

<h2>Flowers List</h2>
<table name="Flowers" class="sortable">
    <tr>
        <td><strong>Name</strong></td>
        <td><strong>Price</strong></td>
        <td><strong>Amount</strong></td>
        <td><strong>Order</strong></td>
    </tr>
    <c:forEach items="${flowers}" var="flower">
        <tr>
            <td>${flower.name}</td>
            <td>${flower.price}</td>
            <td>${flower.amount}</td>

        </tr>
    </c:forEach>
</table>

<%--<display:table name="${flowers}" sort="list">--%>
<%--&lt;%&ndash;    <display:setProperty name="basic.empty.showtable" value="true"/>&ndash;%&gt;--%>
<%--    <display:column property="name" title="Name" sortable="true" headerClass="sortable"/>--%>
<%--    <display:column property="price" title="Price" sortable="true" headerClass="sortable"/>--%>
<%--    <display:column property="amount"  title="Amount" sortable="true" headerClass="sortable"/>--%>
<%--</display:table>--%>
</body>
</html>
