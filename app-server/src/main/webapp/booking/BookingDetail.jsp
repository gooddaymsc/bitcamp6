<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
a {
    text-decoration: none;
    color: black;
}
button {
  xposition: relative;
  top: 0;
  float: right;
}
.booking-all {
position: absolute;
  xborder:2px solid black;

width : 1200px;
height:450px;
}
 .booking-img {
  position: relative;
  xborder:2px solid black;
  left: 100px;
  width: 320px;
  height: 500px;
} 

.booking-text1 {
position: absolute;
top :0;
left:500px;
  xborder:2px solid red;
  width : 300px;
}
.col-sm-6 {
  xborder:2px solid red;
  height:50px;
}
.booking-text2 {
position: absolute;
top :150px;
left:750px;
  border:2px solid #c5c6c2;
  width : 300px;
  padding: 10px 50px;
}
</style>

<h1>예약 상세보기</h1><h5>상품명 : ${booking.cart.stock.product.productName}</h5>
<div class='booking-all'>
<div class='booking-img'>
<img id="f-photo-image" src="../upload/product/${booking.cart.stock.product.photo}_1000x1000.jpg" 
        align="left" width="300px" height="450px" >
</div>   
<form action='update' method='post'>
<div class='booking-text1'>
    <input type='hidden' id='f-productName' type='text' name='productName' value='${booking.cart.stock.product.productName}'readonly><br>
      <div class="col-sm-6">
  <label for='f-bookingNumber'>예약번호 : ${booking.bookingNumber}</label> 
    <input type='hidden' id='f-bookingNumber' type='text' name='bookingNumber' value='${booking.bookingNumber}' readOnly><br>
      </div>
      <div class="col-sm-6">
  <label for='f-stocks'>수량 : ${booking.bookingStocks}</label>
    <input type='hidden' id='f-stocks' type='text' name='stocks' value='${booking.bookingStocks}' readonly><br>
      </div>
      <div class="col-sm-6">
  <label for='f-price'>금액 : ${booking.bookingPrice}</label>
    <input type='hidden' id='f-price' type='text' name='price' value='${booking.bookingPrice}' readonly><br>
      </div>
      <div class="col-sm-6">
  <label for='f-businessName'>가게명 : ${booking.cart.stock.seller.businessName}</label> 
    <input type='hidden' id='f-businessName' type='text' name='businessName' value='${booking.cart.stock.seller.businessName}' readonly><br>
      </div>
      <div class="col-sm-6">
  <label for='f-id'>판매자 : ${booking.cart.stock.seller.member.id}</label>
    <input type='hidden' id='f-id' type='text' name='id' value='${booking.cart.stock.seller.member.id}' readonly><br>
      </div>
      <div class="col-sm-6">
  <label for='f-businessAddress'>가게주소 : ${booking.cart.stock.seller.businessAddress}</label> 
    <input type='hidden' id='f-businessAddress' type='text' name='businessAddress' value='${booking.cart.stock.seller.businessAddress}'readonly><br>
      </div>
      <div class="col-sm-6">
  <label for='f-businessPlaceNumber' >가게번호 : ${booking.cart.stock.seller.businessPlaceNumber}</label> 
    <input type='hidden' id='f-businessPlaceNumber' type='text' name='businessPlaceNumber' value='${booking.cart.stock.seller.businessPlaceNumber}'readonly><br>
</div>
      <div class="col-sm-6">
  <label for='f-id2'>구매자 : ${booking.cart.id}</label>
    <input type='hidden' id='f-id2' type='text' name='id2' value='${booking.cart.id}' readonly><br>
</div>
</div>
<div class='booking-text2'>
<div class="mb-3 row">
  <label for='f-bookingTime'>*예약시간</label>
  <div class="col-sm-4">
    <input id='f-bookingTime' type="time" name='bookingTime' value='${booking.bookingTime}' ><br>
  </div>
</div> 
<div class="mb-3 row">
  <label for='f-bookingDate'>*예약날짜</label>
  <div class="col-sm-4">
    <input id='f-bookingDate' type='date' name='bookingDate' value='${booking.bookingDate}' ><br>
  </div>
</div>    
</div>  
<button type="button" onclick="location.href='../message/form?id=${booking.cart.stock.seller.member.id}'" class="btn btn-outline-success">판매자에게 문의하기</button>
<button class="btn btn-outline-success">변경</button>
<button type="button" onclick="location.href='delete?no=${booking.bookingNumber}'" class="btn btn-outline-success">삭제</button>
<button type="button" onclick="location.href='list'" class="btn btn-outline-success">목록</button>
</form>
</div>
