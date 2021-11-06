<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<a href='../board/list' class="btn btn-primary">게시판</a>
<a href='../product/list' class="btn btn-primary">상품</a>
<a href='../stock/list?id=${loginUser.id}' class="btn btn-primary">재고</a>
<a href='../cart/list' class="btn btn-primary" >장바구니</a>
<a href='../booking/list' class="btn btn-primary">예약</a>
<a href='../message/list' class="btn btn-primary">메세지</a>
<a href='logout' class="btn btn-primary">로그아웃</a>
</body>
</html>