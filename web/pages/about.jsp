<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta charset="utf-8">
        <script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <!-- Popper JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

        <title>About</title>
    </head>
    <body style="height: 10px; background: linear-gradient(45deg, #ddc8ee, #C5DDE8);">
        <%@include file="navbar.html"%>
        <div class="alert alert-info" role="alert">
            <h3 class="alert-heading">О сайте Pretty images</h3>
            <p>
                На данном сайте после регистрации Вы можете делиться красивыми картинками с другими пользователями,
                при этом размер изображения не должен превышать 2 Мб, а формат должен быть PNG или JPEG. Более того, Вы сможете
                просматривать уже загруженные картинки в постраничном режиме.
                Также у Вас есть возможность оценивать картинки других пользователей лайками.
            </p>
        </div>
    </body>
</html>
