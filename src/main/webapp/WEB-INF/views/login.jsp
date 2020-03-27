<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<body class="text-center">
<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/style.css"/>
<h1>Login Form</h1>
<%--    <c:if test="${not empty errorMessage}"><div style="color:red; font-weight: bold; margin: 30px 0px;">${errorMessge}</div></c:if>--%>

<form name='login' action="/login" method='POST'>
    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='username' value='' autofocus="true" required placeholder="Username"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password' required placeholder="Password"/></td>
        </tr>
        <tr>
            <td><input name="submit" type="submit" value="submit" /></td>
        </tr>
    </table>
    <c:if test="${not empty errMsg}">
        <h4 class="error" style="width: 900px">${errMsg}</h4>
    </c:if>
</form>
<h4 class="text-center"><a href="${contextPath}/registration">Create an account</a></h4>
</body>


</html>