<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>재고목록</title>
</head>
<body>
<h1>재고 목록</h1>
<a href='form'>새 재고</a><br>
<table border='1'>
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
    <td><a href='detail?no=${stock.stockNumber}'>${stock.stockNumber}</a></td>
    <td>${stock.product.productName}</td> 
    <td>${stock.price}</td> 
    <td>${stock.stocks}</td> 
</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>








