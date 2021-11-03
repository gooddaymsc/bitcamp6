<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>재고상세</title>
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
<h1>재고상세</h1>
<form action='update?id=${stock.seller.member.id}'>


<label for='f-no'>번호</label> 
<input id='f-no' type='text' name='no' value='${stock.stockNumber}' readonly><br>
  
<label for='f-productName'>상품</label> 
<input id='f-productName' type='text' name='productName' value='${stock.product.productName} 'readonly><br>

<label for='f-price'>가격</label> 
<input id='f-price' type='text' name='price' value='${stock.price}'><br>

<label for='f-stocks'>수량</label> 
<input id='f-stocks' type='text' name='stocks' value='${stock.stocks}'><br>

<button>변경</button>
 <a href='delete?no=${stock.stockNumber}&id=${id}'>[삭제]</a> <a href='list?id=${id}'>[목록]</a><br>
 
</form>

</body>
</html>
