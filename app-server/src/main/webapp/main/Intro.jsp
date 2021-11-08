<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <title>메인메뉴</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" href="../node_modules/sweetalert2/dist/sweetalert2.css">
  <link rel="stylesheet" href="../css/common.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  <script src="../node_modules/sweetalert2/dist/sweetalert2.js"></script>
  
  <style>
    .intro_bg{
      background-image:url("../image/alcohol.jpg");
      width:100%;
      height:718px;
    }

  </style>
</head>
<body>
<div class="container">

<nav class="navbar navbar-expand-lg navbar-light ">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Drinker</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="../product/list">Alcohol</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="../product/ranking">Ranking</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="../board/list">Board</a>
        </li>
        <!-- <li class="nav-item">
          <a class="nav-link" href="#">Link</a>
        </li> -->
        <!-- <li class="nav-item">
          <a class="nav-link disabled">Finder</a>
        </li> -->
      </ul>
      <form class="d-flex">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>

<div id="content">

</div><!-- #content -->
<jsp:include page="../footer.jsp"></jsp:include>
</div><!-- .container -->
</body>
</html>