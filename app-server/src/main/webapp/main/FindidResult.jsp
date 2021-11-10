<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>
    .container {
        width: 640px;
    }
  </style>
</head>
<body>
<div class="container">
<h1>아이디찾기 결과</h1>
<form>
 <label class='form-control-plaintext'>${id}</label><br> 

 <a href="./loginMenu">로그인</a>
 <a href="./findpwMenu">비밀번호찾기</a>

</form>
</div><!-- .container -->
</html>
