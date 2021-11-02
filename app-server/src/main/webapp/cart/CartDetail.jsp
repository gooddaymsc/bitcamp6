<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>장바구니상세</title>
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
<h1>장바구니 상세(MVC + EL)</h1>
<form action='update'>
    <label for='f-cartNumber'>번호</label> 
    <input id='f-cartNumber' type='text' name='cartNumber' value='${cart.cartNumber}' readonly><br>
    
    <label for='f-buyerId'>구매자</label> 
    <input id='f-buyerId' type='text' name='buyerId' value='${cart.id}' readonly><br>
    
    <label for='f-businessName'>가게명</label>
    <input id='f-businessName' type='text' name='businessName' value='${sellerList.businessName}' readonly><br>
        
    <label for='f-sellerid'>판매자</label> 
    <input id='f-sellerid' type='text' name='sellerid' value='${cart.stock.seller.member.id}' readonly><br>
    
    <label for='f-productName'>상품명</label> 
    <input id='f-productName' type='text' name='productName' value='${cart.stock.product.productName}' readonly><br>
    
		<label for='f-stocks'>수량</label> 
		<input id='f-stocks' type='text' name='stocks' value='${cart.cartStocks}'><br>
		
		<label for='f-price'>개당금액</label> 
		<input id='f-price' type='text' name='price' value='${cart.stock.price}' readonly><br>
       
<button>변경</button>
 <a href='delete?no=${cart.cartNumber}'>[삭제]</a> <a href='list'>[목록]</a><br>
 <a href='../booking/form?no=${cart.cartNumber}&id=${cart.id}'>[예약 등록]</a><br>
</form>

</body>
</html>
