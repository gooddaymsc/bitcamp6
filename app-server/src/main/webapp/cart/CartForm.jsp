<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>새장바구니</title>
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
<h1>새장바구니(MVC)</h1>
<form action='add'>

<label for='f-stockNumber'>재고번호</label> 
<input id='f-stockNumber' type='text' name='stockNumber'><br>

<label for='f-stocks'>수량</label> 
<input id='f-stocks' type='text' name='stocks'><br>

<button>등록</button><br>
</form>
</body>
</html>









