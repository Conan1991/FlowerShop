<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Spring5 MVC Hibernate Demo</title>
    <style type="text/css">
        .error {
            color: red;
        }

        table {
            width: 50%;
            border-collapse: collapse;
            border-spacing: 0px;
        }

        table td {
            border: 1px solid #565454;
            padding: 20px;
        }
    </style>
</head>
<body>
<h1>Input Form</h1>
<form:form action="addUser" method="post" modelAttribute="user">
    <table>
        <tr>
            <td>Name</td>
            <td>
                <form:input path="fio"/> <br/>
                <form:errors path="fio" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td>Email</td>
            <td>
                <form:input path="balance"/> <br/>
                <form:errors path="balance" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="submit">Submit</button>
            </td>
        </tr>
    </table>
</form:form>

<h2>Users List</h2>
<table>
    <tr>
        <td><strong>Name</strong></td>
        <td><strong>Email</strong></td>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.fio}</td>
            <td>${user.address}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>