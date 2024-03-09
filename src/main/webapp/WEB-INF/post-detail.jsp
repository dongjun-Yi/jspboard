<%--
  Created by IntelliJ IDEA.
  User: dongjun
  Date: 3/8/24
  Time: 1:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="post-add" method="post">
    <input hidden="hidden" name="id" value="${id}"/>
    title: <input type="text" name="title" value="${title}"/>
    content: <input type="text" name="content" value="${content}"/>
    <button type="submit">전송</button>
</form>
</body>
</html>
