<%--
  Created by IntelliJ IDEA.
  User: vitaliy.doronin
  Date: 10.03.2020
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<title>Flower Shop Registration</title>

<head>
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css"/>
    <title>Create an account</title>
</head>
<body>
<form:form action="addUser" method="post" modelAttribute="user">
    <table>
        <tr>
            <td>FIO</td>
            <td>
                <input path="fio" required/> <br/>
                <errors path="fio" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td>ADRESS</td>
            <td>
                <input path="address" required/> <br/>
                <errors path="address" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td>BALANCE</td>
            <td>
                <input path="balance" required type="number" min="0" /> <br/>
                <errors path="balance" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td>LOGIN</td>
            <td>
                <input path="login" placeHolder="Username" required/> <br/>
                <errors path="login" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td>PASSWORD</td>
            <td>
                <form:password path="password" placeHolder="Password"/> <br/>
                <form:errors path="password" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="submit">Submit</button>
            </td>
        </tr>
    </table>
    <c:if test="${not empty errMsg}">
        <h4 class="error message" style="width: 900px">${errMsg}</h4>
    </c:if>
</form:form>

</body>
</html>
