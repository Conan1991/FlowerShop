<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<body>
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
<h1>Spring Security 5 - Login Form</h1>
<%--    <c:if test="${not empty errorMessage}"><div style="color:red; font-weight: bold; margin: 30px 0px;">${errorMessge}</div></c:if>--%>

<form name='login' action="/submit" method='POST'>
    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='username' value='' autofocus="true"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password'/></td>
        </tr>
        <tr>
            <td><input name="submit" type="submit" value="submit"/></td>
        </tr>
    </table>
</form>
<h4 class="text-center"><a href="${contextPath}/registration">Create an account</a></h4>
</body>


</html>