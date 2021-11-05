<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>장바구니목록</title>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
</head>
<body>
<div class="container">
<h1>장바구니 목록</h1>
<a href='form' class="btn btn-outline-primary btn-sm">새장바구니</a><br>
<table class="table table-hover">
<thead>
  <tr>
    <th> 번호</th>
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
      <td> <a href='detail?cartNumber=${cart.cartNumber}&id=${cart.id}'>${cart.cartNumber}</a></td>
      <td>${cart.id}</td> 
      <td>${sellerList.businessName}</td>  
      <td>${cart.stock.seller.member.id}</td> 
      <td>${cart.stock.product.productName}</td> 
      <td>${cart.cartStocks}</td> 
      <td>${cart.stock.price}</td> 
      <td>${cart.registrationDate}</td> 
      <td> <div class="form-check form-inline">
      <input class="form-check-input" type="checkbox"  name='price' value='${cart.stock.price}' id="flexCheckDefault">
  </div></td> 
</tr>
</c:forEach>
</tbody>
</table>
</div><!-- .container -->
</html>








