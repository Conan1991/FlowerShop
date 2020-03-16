<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Admin Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <%--    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css"/>--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>


    <script type="text/javascript" src="<c:url value="/resources/js/sorttable.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/orders.js"/>"></script>
</head>
<body>
<h1>Hello , <span id="username">${username}</span>! , there you can do with orders</h1>
<c:if test="${empty username}"> <c:redirect url="http://localhost:8080/login">Redirecting to login</c:redirect></c:if>
<h2>ADMIN PAGE</h2>

<%--<form:form action="/doOrder" method="post" modelAttribute="username">--%>
<table name="Orders" class="table table-hover table-bordered sortable">
    <thead class="thead-dark">
    <tr>
        <td><strong>Order ID</strong></td>
        <td><strong>Order Name</strong></td>
        <td><strong>Open Date</strong></td>
        <td><strong>Close Date</strong></td>
        <td><strong>Summary Price</strong></td>
        <td><strong>Status</strong></td>
    </tr>
    </thead>
    <c:forEach items="${orders}" var="orderItem">
        <tr>
            <td>${orderItem.id}</td>
            <td>${orderItem.login}</td>
            <td>${orderItem.opendate}</td>
            <td>${orderItem.closedate}</td>
            <td>${orderItem.total}</td>
                <%--            <td>${orderItem.status}</td>--%>
            <td align="center">
                <select class="status" name="orderStatus" id="${orderItem.id}"
                        onchange="changeOrderStatus('${orderItem.id}', this.value)">
                    <option value="CREATED" ${orderItem.status == 'CREATED' ? 'selected' : ''}>CREATED</option>
                    <option value="CLOSED" ${orderItem.status == 'CLOSED' ? 'selected' : ''}>CLOSED</option>
                    <option value="PAID" ${orderItem.status == 'PAID' ? 'selected' : ''}>PAID</option>
                </select>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>