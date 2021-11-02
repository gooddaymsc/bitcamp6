<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>장바구니목록</title>
</head>
<body>
<h1>장바구니 목록(MVC + EL + JSTL)</h1>
<a href='form'>새장바구니</a><br>
<table border='1'>
<thead>
  <tr>
    <th>번호</th>
    <th>구매자</th>
    <th>가게명</th>
    <th>판매자</th>
    <th>상품명</th>
    <th>수량</th>
    <th>개당금액</th>
    <th>등록일</th>
  </tr>
</thead>
<tbody>

<%-- <c:forEach items="${cartList}" var="cart" items="${sellerList}" var="sellerList"> --%>
<c:forEach items="${cartList}" var="cart" varStatus="status">
<tr>
    <td><a href='detail?no=${cart.cartNumber}'>${cart.cartNumber}</a></td>
    <td>${cart.id}</td> 
    <td>${sellerList.businessName}</td> 
    <td>${cart.stock.seller.member.id}</td> 
    <td>${cart.stock.product.productName}</td> 
    <td>${cart.cartStocks}</td> 
    <td>${cart.stock.price}</td> 
    <td>${cart.registrationDate}</td> 
</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>








