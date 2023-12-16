<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>
    hello world page
</h1>
<h2>
    hello ${username_key}
    <br>
    your roles are ${authorities_key}
</h2>
<br/>

<sec:authorize access="hasAuthority('CODER')">
    <a href="/coder">coders dashboard </a>
</sec:authorize>

<br>

<sec:authorize access="hasAuthority('TRAINER')">
    <a href="/trainer">trainers dashboard </a>
</sec:authorize>

<br>

<a href="/deleteuser?username=${username_key}">Delete Acount</a>

<br>

<a href="/chagepassword">changepassword </a>


<form:form action="logout" method="post">
    <input type="submit" value="logout">
</form:form>


</body>
</html>