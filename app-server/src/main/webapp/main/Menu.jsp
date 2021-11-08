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
        <!--font-family-->
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">

        <!-- For favicon png -->
    <link rel="shortcut icon" type="image/icon" href="../logo/favicon.png"/>
       
        <!--font-awesome.min.css-->
        <link rel="stylesheet" href="../css/font-awesome.min.css">

        <!--linear icon css-->
    <link rel="stylesheet" href="../css/linearicons.css">

    <!--animate.css-->
        <link rel="stylesheet" href="../css/animate.css">

        <!--owl.carousel.css-->
        <link rel="stylesheet" href="../css/owl.carousel.min.css">
    <link rel="stylesheet" href="../css/owl.theme.default.min.css">
    
        <!--bootstrap.min.css-->
        <link rel="stylesheet" href="../css/bootstrap.min.css">
    
    <!-- bootsnav -->
    <link rel="stylesheet" href="../css/bootsnav.css" > 
        
        <!--style.css-->
        <link rel="stylesheet" href="../css/style.css">
        
        <!--responsive.css-->
        <link rel="stylesheet" href="../css/responsive.css">

  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  <script src="../node_modules/sweetalert2/dist/sweetalert2.js"></script>
  
  <style>
    .container {
        xborder: 1px solid red;
        width: 640px;
    }
    tr a {
        text-decoration: none;
        color: black;
    }
    tr a:visited {
        color: black;
    }
    tr:hover {
        cursor: pointer;
    }
  </style>
</head>
<body>
<div class="container">

<jsp:include page="../header.jsp"></jsp:include>
<jsp:include page="../sidebar.jsp"></jsp:include>

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
      <a href='../booking/list' class="btn btn-primary">예약</a>
      
  </c:when>
  <c:when test="${loginUser.authority eq 8}">
      <a href='../board/list' class="btn btn-primary">게시판</a>
      <a href='../product/list' class="btn btn-primary">상품</a>
      <a href='../buyer/list' class="btn btn-primary">회원(구매자)관리</a>
      <a href='../seller/list' class="btn btn-primary">회원(판매자)관리</a>
      <a href='../message/list' class="btn btn-primary">메세지</a>
  </c:when>
</c:choose>
</div><!-- #content -->
<jsp:include page="../footer.jsp"></jsp:include>
</div><!-- .container -->
</body>
</html>