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
    <script type="text/javascript" src="<c:url value="/resources/js/home.js"/>"></script>
</head>
<body>
<div class="container">
    <%--    <div class="row">--%>
    <div class="text-center" style="background-color: darkcyan"><h1>Hello , <span id="username">${username}</span>! ,
        your balance is ${balance} , and your
        discount is ${discount}%</h1></div>
    <c:if test="${empty username}"> <c:redirect
            url="http://localhost:8080/login">Redirecting to login</c:redirect></c:if>
    <div class="text-right" style="background-color: bisque"><a href="<c:url value="/login"/>" class="btn btn-info">logout</a>
    </div>
    <div class="text-center" style="background-color: bisque"><a href="<c:url value="/cart/${username}"/>"
                                                                 class="btn btn-info">Go To Cart</a>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-sm">
            <h2>Flowers List</h2>
            <table name="Flowers" class="table table-hover table-bordered sortable">
                <thead class="thead-dark">
                <tr>
                    <td><strong>Name</strong></td>
                    <td><strong>Price</strong></td>
                    <td><strong>Amount</strong></td>
                    <td><strong>Order</strong></td>
                    <td><strong>Add To Cart</strong></td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${flowers}" var="flower">
                    <tr>
                        <td>${flower.name}</td>
                        <td>${flower.price}</td>
                        <td><span class="amount_value" itemprop="${flower.name}">${flower.amount}</span></td>
                        <td><input type="number" id="${flower.name}" min="1" max="${flower.amount}" class="text_input">
                        </td>
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
                </tbody>
            </table>
        </div>

        <c:if test="${not empty orders}">
            <div class="col-sm">
                <h2>Order List</h2>
                <table name="OrderTable" class="table table-hover table-bordered sortable">
                    <thead class="thead-dark">
                    <tr>
                        <td><strong>Order ID</strong></td>
                        <td><strong>Order Open Date</strong></td>
                        <td><strong>Order Close Date</strong></td>
                        <td><strong>Order Status</strong></td>
                        <td><strong>Total Price with Discount</strong></td>
                        <td><strong>Pay For Order</strong></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${orders}" var="orderItem">
                        <tr>
                            <td>${orderItem.id}</td>
                            <td>${orderItem.opendate}</td>
                            <td>${orderItem.closedate}</td>
                            <td>${orderItem.status}</td>
                            <td>${orderItem.total}</td>
                            <td>
                                <c:choose> <c:when test="${orderItem.status eq 'CREATED'}">
                                    <button value="pay" type="button" id="btn${orderItem.id}" class="pay_button"
                                            onclick="pay_for_order('${username}', '${orderItem.id}')"
                                            style="background-color: cadetblue">PAY
                                    </button>
                                </c:when>
                                    <c:otherwise>
                                        <button value="pay" type="button" id="btn${orderItem.id}" class="pay_button"
                                                disabled style="background-color: grey">PAYED
                                        </button>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
