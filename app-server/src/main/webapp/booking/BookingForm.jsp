<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>새예약</title>
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
<h1>새예약</h1>
<form action='add'>
<label for='f-cartNumber'>장바구니번호</label> <input id='f-cartNumber' type='text' name='cartNumber' value='${cartNo}'><br>
<label for='f-id'>구매자</label> <input id='f-id' type='text' name='id' value='${id}'><br>
<label for='f-bookingDate'>예약날짜</label><input id='f-bookingDate' type='date' name='bookingDate'><br>
<label for='f-bookingTime'>예약시간</label><input id='f-bookingTime' type="time" name='bookingTime'><br>
<p>1.카드결제 / 2.실시간 계좌이체 / 3.무통장입금 / 4.휴대폰 결제 / 5.현장결제</p>
<label for='f-paymentType'>결제방법</label><input id='f-paymentType' type="text" name='paymentType'><br>
<button>등록</button><br>
</form>
</body>
</html>
