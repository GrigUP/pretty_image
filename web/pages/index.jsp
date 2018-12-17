<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" >

<head>
  <!--<link href="C:\Users\я\Documents\pretty_image\web\css\style.css" rel="stylesheet" type="text/css">-->
  <meta charset="UTF-8">
  <meta charset="utf-8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <!-- jQuery library -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <!-- Popper JS -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <!-- Latest compiled JavaScript -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
  <title>Pretty images</title>
  <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
</head>
<body style="height: 10px; background: linear-gradient(45deg, #ccc4ee, #C5DDE8);">
<%@include file="navbar.html"%>
  <main class="container" style="margin-top: 10px">
    <div class="row">
      <div class="col-3">
        <div class="form-group" align="center">
          <h3 style="margin-top: 20px;">Sign Up</h3>
            <form action="${pageContext.request.contextPath}/signup" method="post">
              <input class="form-control" style="margin: 5px;" placeholder="First Name" type="text" name="fname" required autocomplete="off" />
              <input class="form-control" style="margin: 5px;" placeholder="Last Name" type="text" name="lname" required autocomplete="off"/>
              <input class="form-control" style="margin: 5px;" placeholder="Email Address" type="email" name="email" required autocomplete="off"/>
              <input class="form-control" style="margin: 5px;" placeholder="Set A Password" type="password" name="password" required autocomplete="off"/>
              <button type="submit" class="btn btn-primary" style="margin: 5px;"/>Get Started</button>

              <c:if test="${sessionScope.get('accountError') != null}">
                <div class="alert alert-danger" role="alert">
                    ${sessionScope.get('accountError')}
                </div>
              </c:if>

            </form>
          <h3 style="margin-top: 20px;">Welcome Back!</h3>
            <form action="${pageContext.request.contextPath}/login" method="post">
              <input class="form-control" style="margin: 5px;" placeholder="Email Address" type="email" name="email" required autocomplete="off"/>
              <input class="form-control" style="margin: 5px;" placeholder="Password" type="password" name="password" required autocomplete="off"/>
              <button class="btn btn-primary" style="margin: 5px;"/>Log In</button>

              <c:if test="${sessionScope.get('loginError') != null}">
                <div class="alert alert-danger" role="alert">
                    ${sessionScope.get('loginError')}
                </div>
              </c:if>

            </form>
        </div>
      </div>
      <div class="col-9">
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
              </div>
            </div>
          </c:forEach>
        </div>
      </div>
    </div>
  </main>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
  <script  src="${pageContext.request.contextPath}/js/index.js"></script>


</body>

</html>
