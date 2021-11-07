<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>새장바구니</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
    <style>
    .container {
        xborder: 1px solid red;
        width: 640px;
    }
    button{
      margin-left: 300px;
    }
  </style>
</head>
<body>
<div class="container">
<h1>새장바구니</h1>
<form action='add'>
<div class="mb-3 row">
  <label for='f-stockNumbr' class="col-sm-2 col-form-label">재고번호</label>
  <div class="col-sm-2">
    <input id='f-stockNumber' type='text' name='stockNumber' value='${stockNo}' readOnly><br>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-stocks' class="col-sm-2 col-form-label">수량</label>
  <div class="col-sm-2">
    <input id='f-stocks' type='text' name='stocks' class="form-control">
  </div>
</div>

<button class="btn btn-primary btn-sma">등록</button><br>
</form>
</div> <!-- .container -->
</body>
</html>









