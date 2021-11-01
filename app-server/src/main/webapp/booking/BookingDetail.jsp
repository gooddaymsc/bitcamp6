<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>예약상세보기</title>
  <style>
  label {
    margin-right: 20px;
    text-align: right;
    display: inline-block;
    width: 60px;
  }
  </style>
</head>
<body>
<h1>예약 상세보기(구매자)</h1>
<form action='update'>
    <label for='f-businessName'>가게명</label> 
    <input id='f-businessName' type='text' name='businessName' value='${booking.cart.stock.seller.businessName}'><br>
    
    <label for='f-id'>판매자</label>
    <input id='f-id' type='text' name='id' value='${booking.cart.stock.seller.member.id}' readonly><br>
    
    <label for='f-businessAddress'>가게주소</label> 
    <input id='f-businessAddress' type='text' name='businessAddress' value='${booking.cart.stock.seller.businessAddress}'><br>
    
    <label for='f-businessPlaceNumber' >가게번호</label> 
    <input id='f-businessPlaceNumber' type='text' name='businessPlaceNumber' value='${booking.cart.stock.seller.businessPlaceNumber}'><br>
    
    <label for='f-productName'>상품명</label> 
    <input id='f-productName' type='text' name='productName' value='${booking.cart.stock.product.productName}'><br>
    
    <label for='f-stocks'>수량</label>
    <input id='f-stocks' type='text' name='stocks' value='${booking.bookingStocks}' readonly><br>
    
    <label for='f-price'>금액</label>
    <input id='f-price' type='text' name='price' value='${booking.bookingPrice}'><br>
<button>변경</button>
<%--  <a href='delete?id=${seller.member.id}'>[삭제]</a> <a href='list'>[목록]</a><br>
 --%></form>

</body>
</html>
