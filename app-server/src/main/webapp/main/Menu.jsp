<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <title>메인메뉴</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>
    .container {
        xborder: 1px solid red;
        width: 640px;
    }
  </style>
</head>
<body>

<c:choose> 
  <c:when  test="${loginUser eq null}">
  <a href='./loginMenu' class="btn btn-primary">로그인</a>
  <a href='../buyer/form' class="btn btn-primary">회원가입</a><br>
  </c:when>
  <c:otherwise>
  <p>아이디 : ${loginUser.id}</p>
  <br>
  <a href='logout' class="btn btn-primary">로그아웃</a>
  </c:otherwise>
</c:choose>

<a href='../board/list' class="btn btn-primary">게시판</a>
<a href='../product/list' class="btn btn-primary">상품</a>
<c:set var="name" value="코요" />
 
<c:choose> 
  <c:when test="${loginUser.authority eq 4}">
      <a href='../stock/list?id=${loginUser.id}' class="btn btn-primary">재고</a>
      <a href='../seller/detail?id=${loginUser.id}' class="btn btn-primary">개인정보변경</a>
  </c:when>
  <c:when test="${loginUser.authority eq 2}">
      <a href='../buyer/detail?id=${loginUser.id}' class="btn btn-primary">개인정보변경</a>
      <a href='../product/review/find' class="btn btn-primary">내가남긴리뷰</a>
      <a href='../cart/list' class="btn btn-primary" >장바구니</a>
      <a href='../booking/list' class="btn btn-primary">예약</a>
      <a href='../message/list' class="btn btn-primary">메세지</a>
  </c:when>
</c:choose>
</body>
</html>