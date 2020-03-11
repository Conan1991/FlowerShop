<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<display:table name="Flowers" sort="external" defaultsort="1">
    <display:column property="id" title="ID" sortable="true" sortName="id" />
    <display:column property="firstName" sortable="true" sortName="firstName" title="First Name" />
    <display:column property="lastName" sortable="true" sortName="lastName" title="Last Name" />
    <display:column property="address" sortable="true" sortName="address" title="Email Address"/>
</display:table>

<html>
<head><title>Spring 5 Web MVC Example</title></head>
<body>
<h1>Hello , ${username}! your balance is ${balance}</h1>

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

</body>
</html>
