<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
    <h1>Users:</h1>
    <c:forEach var="user" items="${users}">
        ${user.name} <a href="/web/user/update?id=${user.id}">Edit</a><br/>
    </c:forEach>
    <a href="/web/user/create">Create</a><br/>
</body>
</html>
