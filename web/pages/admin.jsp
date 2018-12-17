<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin page</title>
    <script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
    <!--<link href="C:\Users\я\Documents\pretty_image\web\css\style.css" rel="stylesheet" type="text/css">-->
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <script src="/js/inputFile.js" ></script>
</head>
<body style="height: 10px; background: linear-gradient(45deg, #ddc8ee, #C5DDE8);">
<%@include file="navbar.html"%>
<div class="container" align="center">
    <div class="col col-md-6" align="center">
        <!-- <div align="center" style="margin-bottom: 20px; margin-top: 10px"> -->
        <h3 style="margin-top: 10px">Загрузить изображениe</h3>
        <form action="${pageContext.request.contextPath}/image/upload" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <div class="input-group input-file">
                    <input type="text" class="form-control" placeholder='Choose a file...' />
                    <span class="input-group-btn">
        		        <button class="btn btn-default btn-choose" type="button">Choose</button>
                    </span>
                </div>
            </div>
            <input placeholder="Enter some tags" style="width: 525px; margin-bottom: 15px" class="form-control" id="tags" type="text" name="tags">
            <div class="form-group">
                <button type="submit" class="btn btn-primary pull-right">Submit</button>
                <button type="reset" class="btn btn-danger">Reset</button>
            </div>
        </form>
        <c:if test="${sessionScope.get('uploadErrorMsg') != null}">
            <div class="alert alert-danger" role="alert">
                    ${sessionScope.get('uploadErrorMsg')}
            </div>
        </c:if>
    </div>
</div>
<hr>
<div >
    <div align="center" style="margin-bottom: 20px">
        <h3>Зарегестрированные пользователи</h3>
    </div>
    <table class="table table-light table-bordered table-hover">
        <thead class="table-info">
            <tr >
                <th>ID</th>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Email</th>
                <th>Пароль</th>
                <th>Тип</th>
            </tr>
        </thead>
        <tbody>
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
        </tbody>
    </table>
    <div align="center">
        <form action="${pageContext.request.contextPath}/accounts/delete" method="post">
            <label for="selectid">Удалить профиль с ID: </label>
            <select id="selectid" class="form-control" style="width: 80px; margin-bottom: 10px" name="idForDelete">
                <c:forEach items="${accountList}" var="account">
                    <c:if test="${account.fname ne sessionScope.get('account').fname
                              and account.lname ne sessionScope.get('account').lname}">
                        <option>${account.id}</option>
                    </c:if>

                </c:forEach>
            </select>
            <input type="submit" class="btn btn-primary" value="Delete user">
        </form>
    </div>
</div>
<hr>
<div>
    <div align="center" style="margin-bottom: 20px">
        <h3>Изображения</h3>
    </div>
    <div class="container">
        <div class="card-columns">
            <c:forEach items="${imagesList}" var="image">
                <div class="card bg-light">
                    <img class="card-img-top" src="${image.webPath}" alt="null">
                    <div class="card-body">
                        <ul class="list-group">
                            <c:if test="${image.tags != ''}">
                                <li class="list-group-item">Тeги: ${image.tags}</li>
                            </c:if>
                            <li class="list-group-item">
                                <label class="likes">
                                    Лайков:
                                    <input type="hidden" value="${image.id}">
                                    <span>${image.likes}</span>
                                </label>
                            </li>
                            <li class="list-group-item">Дата создания: ${image.date}</li>
                            <li class="list-group-item">Добавил пользователь: ${image.accountName}</li>
                        </ul>
                        <div class="btn-group" role="group" aria-label="Basic example">
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#img${image.id}">View</button>
                            <c:if test="${image.deleteFlag == 'true' or sessionScope.get('account').accountType == 'administration'}">
                                <form style="height: 75%;" action="/image/delete" method="post">
                                    <button class="btn btn-danger" type="submit" value="${image.id}" name="imageForDeleteId">Delete</button>
                                </form>
                            </c:if>
                            <div aria-hidden="true" class="modal fade" id="img${image.id}" role="dialog" tabindex="-1">
                                <div class="modal-dialog modal-lg" role="document">
                                    <div class="modal-content">
                                        <div class="modal-body mb-0 p-0" align="center">
                                            <img src="${image.webPath}" alt="null" style="max-height: 530px; max-width: 100%;">
                                        </div>
                                        <div class="modal-footer">
                                            <div><a href="${image.webPath}" target="_blank">Download</a></div>
                                            <button class="btn btn-outline-primary btn-rounded btn-md ml-4 text-center" data-dismiss="modal" type="button">Close</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<script  src="${pageContext.request.contextPath}/js/changeLike.js"></script>

</body>
</html>