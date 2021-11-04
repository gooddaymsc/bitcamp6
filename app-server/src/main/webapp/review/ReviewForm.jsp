<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>새리뷰</title>
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
<h1>새리뷰</h1>
<form action='add'>  
<div class="mb-3 row">
  <label for='f-productNumber' class="col-sm-2 col-form-label">상품번호</label>
  <div class="col-sm-6">
    <input id='f-productNumber' type='text' name='productNumber' class="form-control" value='${productNumber}' readOnly>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-score' class="col-sm-2 col-form-label">평점</label>
  <div class="col-sm-6">
    <input id='f-score' type='text' name='score' class="form-control">
  </div>
</div>
<div class="mb-3 row">
  <label for='f-comment' class="col-sm-2 col-form-label">코멘트</label>
  <div class="col-sm-6">
    <input id='f-comment' type='text' name='comment' class="form-control">
  </div>
</div>
<div class="mb-3 row">
  <label for='f-writer' class="col-sm-2 col-form-label">작성자</label>
  <div class="col-sm-6">
    <input id='f-writer' type='text' name='writer' class="form-control">
  </div>
</div>
<button class="btn btn-primary btn-sm">등록</button><br>
</form>
</div><!-- .container -->
</body>
</html>









