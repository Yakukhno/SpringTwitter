<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Page</title>
</head>
<body>
    <h1>User</h1>
    <form method="post" action="/web/user/createUpdate">
        <%--<c:if test="${not empty user}">--%>
            <input type="hidden" name="id" value="${user.id}"/>
        <%--</c:if>--%>
        Name <input type="text" name="name" value="${user.name}"/>
        <input type="submit" value="Ok"/>
    </form>
</body>
</html>
