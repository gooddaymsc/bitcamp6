<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>새 재고</title>
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
<h1>새 재고</h1>
<form action='add' method='post'>
<div class="mb-3 row">
  <label for='f-productNumber' class="col-sm-2 col-form-label">상품명</label>
  <div class="col-sm-6">
    <input id='f-productNumber' type='text' name='productNumber' class="form-control" value='${productNo}' readOnly>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-price' class="col-sm-2 col-form-label">가격</label>
  <div class="col-sm-6">
    <input id='f-price' type='number' name='price' class="form-control">
  </div>
</div>
<div class="mb-3 row">
  <label for='f-stocks' class="col-sm-2 col-form-label">수량</label>
  <div class="col-sm-6">
    <input id='f-stocks' type="number" name='stocks' class="form-control">
  </div>
</div>
<button class="btn btn-primary btn-sm">등록</button><br>
</form>
</div><!-- .container -->
</body>
</html>









