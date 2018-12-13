<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" >

<head>
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

   <!-- /form -->

  <div class="container">
    <div class="row">
      <div class="col-4">
        <div class="jumbotron text-center">
            <div id="signup">
              <h1>Sign Up</h1>
              <form action="${pageContext.request.contextPath}/signup" method="post">

                <input class="form-control" placeholder="First Name" type="text" name="fname" required autocomplete="off" />

                <input class="form-control" placeholder="Last Name" type="text" name="lname" required autocomplete="off"/>

                <input class="form-control" placeholder="Email Address" type="email" name="email" required autocomplete="off"/>

                <input class="form-control" placeholder="Set A Password" type="password" name="password" required autocomplete="off"/>

                <button type="submit" class="btn btn-primary"/>Get Started</button>

              </form>
            </div>
            <div id="login">
              <h1>Welcome Back!</h1>
              <form action="${pageContext.request.contextPath}/login" method="post">

                <input class="form-control" placeholder="Email Address" type="email" name="email" required autocomplete="off"/>

                <input class="form-control" placeholder="Password" type="password" name="password" required autocomplete="off"/>

                <button class="btn btn-primary"/>Log In</button>
              </form>
          </div>
        </div>
      </div>

      <div class="col">
        <div class="container" align="center">
          <c:forEach items="${imagesList}" var="image">
            <div class="card" style="width: 25rem;">
              <img class="card-img-top" src="${image.webPath}" alt="null">
              <div class="card-body">
                <p class="card-text">
                  <c:if test="${image.tags != ''}">
                    <label>Тэги:</label> ${image.tags}
                    <br>
                  </c:if>
                  <label class="likes">
                    Лайков:
                    <input type="hidden" value="${image.id}">
                    <span>${image.likes}</span>
                  </label>
                  <br>
                  <label>Дата создания:</label> ${image.date}
                  <br>
                  <label>Добавил пользователь:</label> ${image.accountName}
                  <br>
                </p>
              </div>
            </div>
          </c:forEach>
        </div>

      </div>
    </div>
  </div>

  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
  <script  src="${pageContext.request.contextPath}/js/index.js"></script>


</body>

</html>
