<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>예약상세보기</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
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
<h1>예약 상세보기(구매자)</h1>
<form action='update'>
<div class="mb-3 row">
  <label for='f-bookingNumber'>예약번호</label> 
  <div class="col-sm-6">
    <input id='f-bookingNumber' type='text' name='bookingNumber' value='${booking.bookingNumber}' readOnly><br>
  </div>
</div>    
<div class="mb-3 row">
  <label for='f-businessName'>가게명</label> 
  <div class="col-sm-6">
    <input id='f-businessName' type='text' name='businessName' value='${booking.cart.stock.seller.businessName}' readonly><br>
  </div>
</div>    
<div class="mb-3 row">
  <label for='f-id'>판매자</label>
  <div class="col-sm-6">
    <input id='f-id' type='text' name='id' value='${booking.cart.stock.seller.member.id}' readonly><br>
  </div>
</div>    
<div class="mb-3 row">
  <label for='f-bookingDate'>예약날짜</label>
  <div class="col-sm-6">
    <input id='f-bookingDate' type='date' name='bookingDate' value='${booking.bookingDate}' ><br>
  </div>
</div>    
<div class="mb-3 row">
  <label for='f-bookingTime'>예약시간</label>
  <div class="col-sm-6">
    <input id='f-bookingTime' type="time" name='bookingTime' value='${booking.bookingTime}' ><br>
  </div>
</div>    
<div class="mb-3 row">
  <label for='f-businessAddress'>가게주소</label> 
  <div class="col-sm-6">
    <input id='f-businessAddress' type='text' name='businessAddress' value='${booking.cart.stock.seller.businessAddress}'readonly><br>
  </div>
</div>    
<div class="mb-3 row">
  <label for='f-businessPlaceNumber' >가게번호</label> 
  <div class="col-sm-6">
    <input id='f-businessPlaceNumber' type='text' name='businessPlaceNumber' value='${booking.cart.stock.seller.businessPlaceNumber}'readonly><br>
  </div>
</div>    
<div class="mb-3 row">
  <label for='f-productName'>상품명</label> 
  <div class="col-sm-6">
    <input id='f-productName' type='text' name='productName' value='${booking.cart.stock.product.productName}'readonly><br>
  </div>
</div>    
<div class="mb-3 row">
  <label for='f-stocks'>수량</label>
  <div class="col-sm-6">
    <input id='f-stocks' type='text' name='stocks' value='${booking.bookingStocks}' readonly><br>
  </div>
</div>    
<div class="mb-3 row">
  <label for='f-price'>금액</label>
  <div class="col-sm-6">
    <input id='f-price' type='text' name='price' value='${booking.bookingPrice}' readonly><br>
  </div>
</div>  
<div class="mb-3 row">
  <label for='f-id2'>구매자</label>
  <div class="col-sm-6">
    <input id='f-id2' type='text' name='id2' value='${booking.cart.id}' readonly><br>
  </div>
</div>    
<button class="btn btn-primary">변경</button>
 <a href='delete?no=${booking.bookingNumber}' class="btn btn-primary">삭제</a> 
 <a href='list' class="btn btn-primary">목록</a><br>
</form>
</div><!-- .container -->

</body>
</html>
