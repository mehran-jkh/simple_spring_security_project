<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<br/>

<form:form action="logout" method="post">
    <input type="submit" value="logout">
</form:form>

</body>
</html>