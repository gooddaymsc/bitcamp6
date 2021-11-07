<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>새예약</title>
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
<h1>새예약</h1>
<form action='add'>
<div class="mb-3 row">
  <label for='f-cartNumber'>장바구니번호</label> 
  <div class="col-sm-6">
    <input id='f-cartNumber' type='text' name='cartNumber' value='${cartNo}'><br>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-id'>구매자</label> 
  <div class="col-sm-6">
    <input id='f-id' type='text' name='id' value='${id}'><br>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-bookingDate'>예약날짜</label>
  <div class="col-sm-6">
    <input id='f-bookingDate' type='date' name='bookingDate'><br>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-bookingTime'>예약시간</label>
  <div class="col-sm-6">
    <input id='f-bookingTime' type="time" name='bookingTime'><br>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-paymentType'>결제방법</label>
  <div class="col-sm-6">
    <select id='f-paymentType' onchange="categoryChange(this)" class="form-select" name='paymentType' aria-label="Default select example">
      <option selected>결제방법선택</option>
      <option value="1" >카드결제</option>
      <option value="2">실시간 계좌이체</option>
      <option value="3">무통장입금</option>
      <option value="4">휴대폰 결제</option>
      <option value="5">현장결제</option>
    </select>
  </div>
</div>
<button class="btn btn-primary btn-sm">등록</button><br>
</form>
</div><!-- .container -->
</body>
</html>
