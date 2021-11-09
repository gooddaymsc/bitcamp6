<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>새 채팅</title>
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
<h1>새 메세지</h1>
<a href='list' class="btn btn-outline-primary btn-sm">이전</a><br>

<form action='add' method='post'>
<div class="mb-3 row">
  <label for='f-theOtherId' class="col-sm-2 col-form-label">대화할 상대(id)</label> 
  <div class="col-sm-6">
    <input id='f-theOtherId' type='text' name='theOtherId' value='${theOtherId}'><br>
  </div>
</div>

<div class="mb-3 row">
  <label for='f-content' class="col-sm-2 col-form-label">내용</label> 
  <div class="col-sm-6">
    <input id='f-content' type='text' name='content'><br>
  </div>
</div>

<button class="btn btn-primary btn-sm">보내기</button><br>
</form>
</div><!-- .container -->
</body>
</html>









