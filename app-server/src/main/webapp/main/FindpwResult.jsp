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
<h1>비밀번호 변경</h1>
<form action=login>
  <label class='form-control-plaintext'>변경할 새 비밀번호를 입력해주세요.</label><br> 
  비밀번호 <input type='password' name='password'><br>
  비밀번호 확인 <input type='password' name='password'><br><br>

<button class="btn btn-primary btn-sm"> 비밀번호 변경 </button><br>
 <a href="Login.jsp">로그인</a>
 <a href="FindidForm.jsp">아이디찾기</a>

</form>
</div><!-- .container -->
</html>
