<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <title>admin</title>
    <script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
</head>
<body>

<div>
    <div>
        <h3>Данные пользователя</h3>
    </div>

    <div>
        <table border="2pt">
            <tr>
                <td>Имя</td>
                <td>${sessionScope.get("account").fname}</td>
            </tr>
            <tr>
                <td>Фамилия</td>
                <td>${sessionScope.get("account").lname}</td>
            </tr>
            <tr>
                <td>Email</td>
                <td>${sessionScope.get("account").email}</td>
            </tr>
            <tr>
                <td>Пароль</td>
                <td>${sessionScope.get("account").password}</td>
            </tr>
            <tr>
                <td>Тип</td>
                <td>${sessionScope.get("account").accountType}</td>
            </tr>
        </table>
    </div>

    <div>
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <br>
            <input type="submit" value="Выйти">
        </form>
    </div>

</div>

<hr>

<div>
    <div>
        <h3>Загрузить изображение</h3>
    </div>

    <div>
        <form action="${pageContext.request.contextPath}/image/upload" method="post" enctype="multipart/form-data">
            <input type="file" name="file" accept="image/*"><br>
            <label>Теги: </label>
            <input type="text" name="tags">
            <input type="submit">
        </form>
    </div>
</div>

<hr>

<div>
    <details>
        <summary>Пользователи</summary>

        <table border="2pt">
            <tr>
                <td>ID</td>
                <td>Имя</td>
                <td>Фамилия</td>
                <td>Email</td>
                <td>Пароль</td>
                <td>Тип</td>
            </tr>
            <c:forEach items="${accountList}" var="account">
                <tr>
                    <td>${account.id}</td>
                    <td>${account.fname}</td>
                    <td>${account.lname}</td>
                    <td>${account.email}</td>
                    <td>${account.password}</td>
                    <td>${account.accountType}</td>
                </tr>
            </c:forEach>
        </table>

        <form action="${pageContext.request.contextPath}/accounts/delete" method="post">
            <label>Удалить профиль с ID: </label>
            <select name="idForDelete">
                <c:forEach items="${accountList}" var="account">
                    <option>${account.id}</option>
                </c:forEach>
            </select>
            <input type="submit" value="удалить">
        </form>
    </details>
</div>

<hr>

<div>
    <div>
        <h3>Изображения</h3>
    </div>

    <div>
        <c:forEach items="${imagesList}" var="image">
            <form action="/image/delete" method="post">
                <div class="jumbotron text-center">
                    <div>
                        <img src="${image.webPath}" alt="null">
                    </div>

                    <div>
                        <label>ID: </label>${image.id}
                    </div>

                    <c:if test="${image.tags != ''}">
                        <div>
                            <label>Тэги: </label>${image.tags}
                        </div>
                    </c:if>
                    <div >
                        <label class="likes">
                            Лайков:
                            <input type="hidden" value="${image.id}">
                            <span>${image.likes}</span>
                        </label>
                    </div>

                    <div>
                        <label>Дата создания: </label>${image.date}
                    </div>

                    <div>
                        <label>Добавил пользователь: </label>${image.accountName}
                    </div>

                    <div>
                        <button type="submit" value="${image.id}" name="imageForDeleteId">Удалить</button>
                    </div>
                </div>
            </form>
            <hr>
        </c:forEach>
    </div>

</div>

<script  src="${pageContext.request.contextPath}/js/changeLike.js"></script>

</body>
</html>