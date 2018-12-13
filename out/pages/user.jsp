<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>User page</title>
    <script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <title>Sign-Up/Login Form</title>
    <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">--%>
</head>
<body>
    <h3>${sessionScope.get("account").fname} ${sessionScope.get("account").lname}</h3>
    <div>
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <input class="btn btn-primary" type="submit" value="Выйти">
        </form>
    </div>
<hr>
<div class="jumbotron">
        <h3>Загрузить изображение</h3>
    <div class="custom-file">
        <form action="${pageContext.request.contextPath}/image/upload" method="post" enctype="multipart/form-data">
            <input type="file" name="file" id="customFile" class="custom-file-input" accept="image/*">
            <label class="custom-file-label" style="width: 20rem" for="customFile">Choose file</label>
            <input placeholder="Enter some tags" type="text" name="tags">
            <input class="btn btn-primary" type="submit">
        </form>
    </div>
</div>

<hr>
<div>
    <div align="center">
        <h3>Изображения</h3>
    </div>
    <div class="container ">
        <div class="row">
            <c:forEach items="${imagesList}" var="image">
                <div class="card" style="width: 20rem;">
                    <img class="card-img-top" src="${image.webPath}" alt="null">
                    <div class="card-body">
                        <c:if test="${image.tags != ''}">
                            <label>Теги:</label> ${image.tags}
                            <br>
                        </c:if>
                        <label class="likes">
                            Лайков:
                            <input type="hidden" value="${image.id}">
                            <span>${image.likes}</span>
                        </label>
                        <br>
                        <label>Дата создания:</label> ${image.date}
                        <label>Добавил пользователь:</label> ${image.accountName}
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<script  src="${pageContext.request.contextPath}/js/changeLike.js"></script>
</body>
</html>
