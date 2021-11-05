<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>새 댓글</title>
  <link rel="stylesheet" href="../../node_modules/bootstrap/dist/css/bootstrap.css">
  
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
<h1>댓글작성</h1>
<form action='add'>
<div class="mb-3 row">
  <label for='f-number'>게시판번호</label> 
  <div class="col-sm-6">
    <input id='f-number' type='text' name='boardNumber' value='${boardNo}' readOnly><br>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-content'>내용</label> 
  <div class="col-sm-6">
    <input id='f-content' type='text' name='content'><br>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-writer'>댓글작성자</label> 
  <div class="col-sm-6">
    <input id='f-writer' type='text' name='writer' value='${member.Id}' readonly><br>
  </div>
</div>
<button class="btn btn-primary">등록</button><br>
</form>
</div><!-- .container -->
</body>
</html>









