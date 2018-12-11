<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>admin</title>
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
        <h3>Изображения</h3>
    </div>

    <div>
        <c:forEach items="${imagesList}" var="image">
            <div>

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
                <div>
                    <label>Лайков: </label>${image.likes}
                </div>

                <div>
                    <label>Дата создания: </label>${image.date}
                </div>

                <div>
                    <label>Добавил пользователь: </label>${image.accountName}
                </div>
            </div>
            <hr>
        </c:forEach>
    </div>
</div>

</body>
</html>