<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Tweets</title>
</head>
<body>
    <h1>Tweets:</h1>
    <c:forEach var="tweet" items="${tweets}">
        ${tweet.text}<br/>
    </c:forEach>
</body>
</html>
