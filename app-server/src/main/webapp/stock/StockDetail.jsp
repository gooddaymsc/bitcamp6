  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>재고상세</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>
    .container {
        width: 640px;
    }
  </style>
</head>
<body>
<div class="container">
<h1>재고상세</h1>
<form action='update'>
<div class="mb-3 row">
  <label for='f-no' class="col-sm-2 col-form-label">재고번호</label>
  <div class="col-sm-6">
    <input id='f-no' type='text' name='no' class="form-control" value='${stock.stockNumber}' readonly>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-productName' class="col-sm-2 col-form-label">상품</label>
  <div class="col-sm-6">
    <input id='f-productName' type='text' name='productName' class="form-control" value='${stock.product.productName}' readonly>
  </div>
</div>  
<div class="mb-3 row">
  <label for='f-price' class="col-sm-2 col-form-label">가격</label>
  <div class="col-sm-6">
    <input id='f-price' type='text' name='price' class="form-control" value='${stock.price}'>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-stocks' class="col-sm-2 col-form-label">수량</label>
  <div class="col-sm-6">
    <input id='f-stocks' type='text' name='stocks' class="form-control" value='${stock.stocks}'>
  </div>
</div>
<button class="btn btn-primary">변경</button>
 <c:choose> 
    <c:when test="${loginUser.authority eq 4}">
    <button class="btn btn-primary">변경</button>
     <a href='delete?no=${stock.stockNumber}' class="btn btn-primary">삭제</a> <a href='list?id=${stock.seller.member.id}' class="btn btn-primary">목록</a><br>
  </c:when>
  <c:when test="${loginUser.authority eq 2}">
   <a href='../cart/form?no=${stock.stockNumber}' class="btn btn-primary">장바구니 등록</a><br>
   <a href='list?id=${stock.seller.member.id}' class="btn btn-primary">목록</a><br>
  </c:when>
    <c:otherwise>
    <a href='list?id=${stock.seller.member.id}' class="btn btn-primary">목록</a><br>
    </c:otherwise>
</c:choose>
</form>
</div> <!-- container -->
</body>
</html>
