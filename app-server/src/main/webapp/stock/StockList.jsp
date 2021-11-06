<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>재고목록</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>
    .container {
        xborder: 1px solid red;
        width: 1000px;
    }
  </style>
</head>
<body>
<div class="container">
<h1>재고 목록</h1>
<table class="table table-hover">
<thead>
  <tr>
    <th>번호</th>
    <th>상품</th>
    <th>가격</th>
    <th>수량</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${stockList}" var="stock">
<tr>
    <td><a href='detail?no=${stock.stockNumber}&id=${id}'>${stock.stockNumber}</a></td>
    <td>${stock.product.productName}</td> 
    <td>${stock.price}</td> 
    <td>${stock.stocks}</td> 
</tr>
</c:forEach>
</tbody>
</table>
</div> <!-- .container -->
</body>
</html>








