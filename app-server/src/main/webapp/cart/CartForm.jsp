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
<label for='f-cartNumber'>번호</label> <input id='f-cartNumber' type='text' name='cartNumber'><br>
<label for='f-buyerId'>구매자</label> <input id='f-buyerId' type='text' name='buyerId'><br>
<label for='f-businessName'>가게명</label> <input id='f-businessName' type='text' name='businessName'><br>
<label for='f-sellerid'>판매자</label> <input id='f-sellerid' type='text' name='sellerid'><br>
<label for='f-productName'>상품명</label> <input id='f-productName' type='text' name='productName'><br>
<label for='f-stocks'>수량</label> <input id='f-stocks' type='text' name='stocks'><br>
<label for='f-price'>금액</label> <input id='f-price' type='text' name='price'><br>

<button>등록</button><br>
</form>
</body>
</html>









