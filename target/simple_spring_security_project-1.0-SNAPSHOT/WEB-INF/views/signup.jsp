<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 12/12/2023
  Time: 11:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    signup here
</head>
<body>


<form:form modelAttribute="sinupdto_key" action="/signupprocess">

    username: <form:input path="username"></form:input>
    <br>
    password: <form:input path="password"></form:input>
    <br>
    <input type="submit" value="sbmit">
</form:form>



</body>
</html>
