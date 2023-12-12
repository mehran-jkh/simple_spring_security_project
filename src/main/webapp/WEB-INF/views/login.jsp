<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>


<c:if test="${param.error != null}">
    <i style="color: crimson"> invalid login credential </i>
</c:if>


<c:if test="${param.logout != null}">
    <i style="color: crimson"> you are logged out </i>
</c:if>

<br>

<head>
    Simple Custom Login Page
</head>

<body>
<form:form>
    User_Name : <input type="text" name="username">
    <br>
    pass_word : <input type="password" name="password">
    <br>
    <input type="submit" value="Login">
</form:form>
</body>
</html>
