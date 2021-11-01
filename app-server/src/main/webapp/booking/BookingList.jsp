<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>예약목록</title>
</head>
<body>
<h1>예약 목록(구매자)</h1>
<!-- <a href='form'>새회원</a><br>
 -->
 <table border='1'>
<thead>
  <tr>
    <th>번호</th>
    <th>가게명</th>
    <th>상품명</th>
    <th>주문일시</th>
    <th>픽업날짜</th>
    <th>픽업시간</th>
    <th>결제상태</th>
    <th>픽업상태</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${bookingList}" var="booking">
<tr>
    <td>${booking.bookingNumber}</td>
    <td><a href='detail?no=${booking.bookingNumber}'>${booking.cart.stock.seller.businessName}</a></td> 
    <td>${booking.cart.stock.product.productName}</td> 
    <td>${booking.registeredDate}</td> 
    <td>${booking.bookingDate}</td> 
    <td>${booking.bookingTime}</td> 
    <td>${booking.paymentType}</td> 
    <td>${booking.confirm}</td> 
</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>








