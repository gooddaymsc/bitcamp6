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
<h1>비밀번호찾기</h1>
<form action=findpwResult>
<label for='f-name' class="col-sm-2 col-form-label">이름</label>
 <input id='f-name'  type='text' name='name'><br>
 <label for='f-id' class="col-sm-2 col-form-label">아이디</label>
 <input id='f-id'  type='text' name='id'><br>
 <label for='f-phoneOrEmail' class="col-sm-2 col-form-label">전화번호/이메일</label>
 <input id='f-phoneOrEmail' type='text' name='phoneOrEmail'><br><br>
 <button class="btn btn-primary btn-sm">비밀번호찾기</button><br>
 
</form>
</div><!-- .container -->
</html>
