<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>예약목록</title>
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
<h1>예약 목록</h1>
<a href='../main/Menu.jsp' class="btn btn-outline-primary btn-sm">메인</a><br><br>
<table class="table table-hover">
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
</div><!-- .container -->
<footer>
<a href='../main/logout' class="btn btn-primary">로그아웃</a>
</footer>
</body>
</html>








