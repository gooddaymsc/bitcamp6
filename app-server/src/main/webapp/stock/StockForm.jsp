<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>새 재고</title>
  <style>
  label {
    margin-right: 5px;
    text-align: right;
    display: inline-block;
    width: 60px;
  }
  </style>
</head>
<body>
<h1>새 재고</h1>
<form action='add'>

<label for='f-productName'>상품</label> 
<input id='f-productName' type='text' name='productName'><br>

<label for='f-price'>가격</label> 
<input id='f-price' type='text' name='price'><br>

<label for='f-stocks'>수량</label> 
<input id='f-stocks' type='text' name='stocks'><br>

<button>등록</button><br>
</form>
</body>
</html>









