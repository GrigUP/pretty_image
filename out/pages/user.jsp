<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>User page</title>
    <script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
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
    <script src="/js/inputFile.js" ></script>
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">--%>
</head>
<body style="height: 10px; background: linear-gradient(45deg, #EECFBA, #C5DDE8);">
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
    </div>
</div>
<hr>
<div>
    <div align="center">
        <h3>Изображения</h3>
    </div>
    <div class="container" >
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
                        <c:if test="${image.deleteFlag == 'true' or sessionScope.get('account').accountType == 'administration'}">
                            <form action="/image/delete" method="post">
                                <button class="btn" type="submit" value="${image.id}" name="imageForDeleteId">Delete</button>
                            </form>
                        </c:if>
                        <!--
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                            Посмотреть
                        </button>
                        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <img src="${image.webPath}" alt="null">
                                    </div>
                                </div>
                            </div>
                        </div>
                         -->
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<script  src="${pageContext.request.contextPath}/js/changeLike.js"></script>
</body>
</html>
