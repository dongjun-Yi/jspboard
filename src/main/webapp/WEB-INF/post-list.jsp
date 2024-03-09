<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<ol class="list-group list-group-numbered">
    <c:choose>
        <c:when test="${empty posts}">
            게시글이 존재하지 않습니다.
        </c:when>
        <c:otherwise>
            <c:forEach var="item" items="${posts}">
                <a href="/post-detail?id=${item.id}&title=${item.title}&content=${item.content}">
                    <li class="list-group-item">${item.title} | ${item.content}</li>
                </a>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</ol>

<form action="post-add" method="get">
    <button type="submit">게시글 작성</button>
</form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
