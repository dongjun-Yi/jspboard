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
<div class="container" style="margin-top: 8em;">
    <div class="row justify-content-between align-items-center">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="mb-3">
                    제목 : <label class="form-label">${title}</label>
                </div>
                <hr>
                <div class="mb-3">
                    내용 : <label class="form-label">${content}</label>
                </div>
                <button class="btn btn-primary"
                        onclick="location.href='/post-detail-update?id=${id}&title=${title}&content=${content}'">수정
                </button>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
