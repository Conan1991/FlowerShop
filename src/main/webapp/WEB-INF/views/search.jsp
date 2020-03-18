<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Search Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

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
    <script type="text/javascript" src="<c:url value="/resources/js/home.js"/>"></script>
</head>
<body>
<h1>Hello , <span id="username">${username}</span>! this is search page</h1>
<c:if test="${empty username}"> <c:redirect url="http://localhost:8080/login">Redirecting to login</c:redirect></c:if>

<h2>SEARCH PAGE</h2>

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
    <c:forEach items="${searchedFlowers}" var="flower">
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

<div class="row">
    <div class="col-sm">
        <div style="background-color: darkcyan"><a href="<c:url value="/home"> </c:url> " class="btn btn-info">Go
            Back</a></div>
    </div>
    <div class="col-sm">
        <div class="text-center" style="background-color: bisque"><a href="<c:url value="/cart/${username}"/>"
                                                                     class="btn btn-info">Go To Cart</a>
        </div>
    </div>
</div>


</body>
</html>