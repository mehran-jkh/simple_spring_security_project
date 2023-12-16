<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 12/16/2023
  Time: 6:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <h1> change password page  </h1>
</head>
<body>

<c:if  test="${param.invalidpassword != null}">
    <i style="color: crimson"> invalid password </i>
</c:if>

<c:if  test="${param.notMatched != null}">
    <i style="color: crimson"> the old and new password doesnot match </i>
</c:if>


<form:form modelAttribute="changepasswordDTO_key" action="/savechange">

    <label>Old Password</label>
    <form:input path="oldpassword"/>
    <br>
    <label>New Password</label>
    <form:input path="newpassword"/>
    <br>
    <label>Confirm Password</label>
    <form:input path="confirmtpassword"/>
    <br>
    <input type="submit" value="change password"/>

</form:form>


</body>
</html>
