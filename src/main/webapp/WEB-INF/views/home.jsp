<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="en">
<head>
    <title>Flower Shop Home</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css"/>

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

    <%--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>--%>

    <script type="text/javascript" src="<c:url value="/resources/js/sorttable.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/home.js"/>"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="column" style="background-color: darkcyan"><p>Hello , <span id="username">${username}</span>! ,
            your balance is ${balance} , and your
            discount is ${discount}%</p></div>
        <c:if test="${empty username}"> <c:redirect url="http://localhost:8080/login">Redirecting to login</c:redirect></c:if>
        <div class="column" style="background-color: bisque"><a href="<c:url value="/login"/>">
            class="btn btn-info">logout</a>
        </div>
    </div>
</div>

<h2>Flowers List</h2>

<%--<form:form action="/addToCart" method="post" modelAttribute="carts">--%>
<div class="row">
    <div class="column">
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
                    <td><span class="amount_value" itemprop="${flower.name}">${flower.amount}</span></td>
                    <td><input type="number" id="${flower.name}" min="1" max="${flower.amount}" class="text_input"></td>
                    <td>
                        <c:choose> <c:when test="${flower.amount eq 0}">
                            <button value="add" type="button" id="btn${flower.name}" class="flower_button"
                                    onclick="fire_ajax_submit('${username}', '${flower.name}')" disabled>add
                            </button>
                        </c:when>
                            <c:otherwise>
                                <button value="add" type="button" id="btn${flower.name}" class="flower_button"
                                        onclick="fire_ajax_submit('${username}', '${flower.name}')">add
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>

    <c:if test="${not empty orders}">
        <div class="column">
            <table name="Order" class="sortable">
                <tr>
                    <td><strong>Order ID</strong></td>
                    <td><strong>Order Open Date</strong></td>
                    <td><strong>Order Status</strong></td>
                    <td><strong>Total Price with Discount</strong></td>
                    <td><strong>Pay For Order</strong></td>
                </tr>
                <c:forEach items="${orders}" var="orderItem">
                    <tr>
                        <td>${orderItem.id}</td>
                        <td>${orderItem.opendate}</td>
                        <td>${orderItem.status}</td>
                        <td>${orderItem.total}</td>
                        <td>
                            <c:choose> <c:when test="${orderItem.status eq 'CREATED'}">
                                <button value="pay" type="button" id="btn${orderItem.id}" class="pay_button"
                                        onclick="pay_for_order('${username}', '${orderItem.id}')">PAY
                                </button>
                            </c:when>
                                <c:otherwise>
                                    <button value="pay" type="button" id="btn${orderItem.id}" class="pay_button"
                                            onclick="pay_for_order('${username}', '${orderItem.id}')" disabled>PAY
                                    </button>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
</div>
<div class="row">
    <a href="<c:url value="/cart/${username}"/>" class="btn btn-info">Go To Cart</a>
</div>
</body>
</html>
