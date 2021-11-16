<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>예약 목록</h1>
<table class="table table-hover">
<thead>
  <tr>
    <th>번호</th>
    <th>예약자</th>
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
    <td>${booking.cart.id}</td> 
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

<script>
document.querySelectorAll("tbody a").forEach((aTag) => {
  aTag.onclick = () => false;
});

var trList = document.querySelectorAll("tbody tr"); // 리턴 객체는 HTMLCollection 타입 객체이다.
trList.forEach(function(trTag) {
  trTag.onclick = (e) => {
    //console.log(e.currentTarget.querySelector("a").href);
    //e.currentTarget.querySelector("a").click();
    window.location.href = e.currentTarget.querySelector("a").href;
    //window.location.href = "detail?no=" + e.currentTarget.getAttribute("data-no");
  };
});
</script>








