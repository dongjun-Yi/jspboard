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
<h2 style="text-align: center; margin-top: 4em;">Welcome ${sessionScope.username}!</h2>
<div class="container" style="margin-top: 4em;">
    <div class="row justify-content-between align-items-center">
        <div class="col-md-10 text-md-end d-flex justify-content-end">
            <form action="post-add" method="get" class="mr-2">
                <button type="submit" class="btn btn-primary">게시글 작성</button>
            </form>
            <form action="logout" method="post">
                <button class="btn btn-primary">로그아웃</button>
            </form>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-md-8">
            <ol class="list-group">
                <c:choose>
                    <c:when test="${empty posts}">
                        <div>
                            <h3>게시글이 존재하지 않습니다.</h3>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="item" items="${posts}">
                            <c:choose>
                                <c:when test="${username eq item.writer}">
                                    <a href="/v2/post-detail?id=${item.id}&title=${item.title}&content=${item.content}"
                                       class="text-decoration-none">
                                        <li class="list-group-item">${item.id}. ${item.title}
                                            <div style="float:right;">작성자 : ${item.writer}</div>
                                        </li>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <li class="list-group-item">${item.id}. ${item.title} | ${item.content}
                                    </li>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </ol>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
