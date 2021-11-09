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
  <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
  <link rel="icon" href="/favicon.ico" type="image/x-icon">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  <script src="../node_modules/sweetalert2/dist/sweetalert2.js"></script>
  <script src="https://kit.fontawesome.com/26add2f61b.js" crossorigin="anonymous"></script>
  
  <style> 
    .intro_bg{
      background-image:url("../image/alcohol5.jpg");
      width:100%;
      height:618px;
      background-repeat: no-repeat;
      background-position: center;
      background-size:contain;
    } 
  </style>
</head>
<body>
<div class="header">
<nav class="navbar navbar-expand-lg navbar-light ">
  <div class="container-fluid">
    <div class="logo" id='logo'>
      <a class="navbar-brand" href="../main/Menu.jsp"><img src="../image/logo.jpeg"></a>
      </div>
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
      <!--   <li class="nav-item">
          <a class="nav-link" href="../board/list">Link</a>
        </li>  -->
      <!--   <li class="nav-item">
          <a class="nav-link disabled">Finder</a>
        </li>  -->
      </ul>
      <!-- <form class="d-flex">
        <input class="form-control me-2" type="search" placeholder="상품검색" aria-label="Search">
       <span><button class="btn btn-outline-success" type="submit">Search</button></span>
      </form> -->
    </div>
  </div>
  </nav>
</div>

  <div class="intro_bg"><div></div>

 <div id="content">
<c:choose> 
  <c:when  test="${loginUser eq null}">
  <a href='./loginMenu' class="btn btn-primary">로그인</a>
  <a href='../buyer/form' class="btn btn-primary">회원가입</a><br>
  <a href='../board/list' class="btn btn-primary">게시판</a>
  <a href='../product/ranking' class="btn btn-primary">오늘의 술</a>
  <a href='../product/list' class="btn btn-primary">상품</a>
  </c:when>
  <c:otherwise>
  <p>아이디 : ${loginUser.id}</p>
  <br>
  <a href='logout' class="btn btn-primary">로그아웃</a>
  </c:otherwise>
</c:choose> 

<%-- <a href='../board/list' class="btn btn-primary">게시판</a>
<a href='../product/ranking' class="btn btn-primary">오늘의 술</a>
<a href='../product/list' class="btn btn-primary">상품</a>
<c:set var="name" value="코요" /> --%>

<c:choose> 
  <c:when test="${loginUser.authority eq 2}">
      <a href='../board/list' class="btn btn-primary">게시판</a>
      <a href='../product/ranking' class="btn btn-primary">오늘의 술</a>
      <a href='../product/list' class="btn btn-primary">상품</a>
      <a href='../product/review/find' class="btn btn-primary">내가남긴리뷰</a>
      <a href='../cart/list' class="btn btn-primary" >장바구니</a>
      <a href='../booking/list' class="btn btn-primary">예약</a>
      <a href='../message/list' class="btn btn-primary">메세지</a>
      <a href='../buyer/detail?id=${loginUser.id}' class="btn btn-primary">개인정보변경</a>
  </c:when>
  <c:when test="${loginUser.authority eq 4}">
      <a href='../board/list' class="btn btn-primary">게시판</a>
      <a href='../product/ranking' class="btn btn-primary">오늘의 술</a>
      <a href='../product/list' class="btn btn-primary">상품</a>
      <a href='../stock/list?id=${loginUser.id}' class="btn btn-primary">재고</a>
      <a href='../message/list' class="btn btn-primary">메세지</a>
      <a href='../seller/detail?id=${loginUser.id}' class="btn btn-primary">개인정보변경</a>
  </c:when>
  <c:when test="${loginUser.authority eq 8}">
      <a href='../board/list' class="btn btn-primary">게시판</a>
      <a href='../product/list' class="btn btn-primary">상품</a>
      <a href='../buyer/list' class="btn btn-primary">회원(구매자)관리</a>
      <a href='../seller/list' class="btn btn-primary">회원(판매자)관리</a>
      <a href='../message/list' class="btn btn-primary">메세지</a>
  </c:when>
</c:choose> 
</div> content
<jsp:include page="../footer.jsp"></jsp:include>
</div><!-- .container -->
</body>
</html>