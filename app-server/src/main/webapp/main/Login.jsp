<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>로그인</title>
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
<div class="container">
<h1>로그인</h1>
<form action='login' method="post">
아이디: <input type='text' name='id'><br>
비밀번호: <input type='password' name='password'><br>
<button class="btn btn-primary">로그인</button>
<a href="FindidForm.jsp">아이디</a>
<a href="FindpwForm.jsp">비밀번호찾기</a>
</form>
<a href='../buyer/form' class="btn btn-primary">회원가입(구매자 전용)</a>
<a href='../seller/form' class="btn btn-primary">회원가입(판매자 전용)</a>

</div><!-- .container -->
</html>