<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>상품목록</title>
</head>
<body>
<h1>상품 목록(MVC + EL + JSTL)</h1>
<a href='form'>새상품</a><br>
<table border='1'>
<thead>
  <tr>
    <th>번호</th>
    <th>상품명</th>
    <th>주종-상세주종</th>
    <th>원산지</th>
    <th>용량</th>
    <th>당도</th>
    <th>산도</th>
    <th>바디감</th>
    <th>도수</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${productList}" var="product">
<tr>
    <td>${product.productNumber}</td>
    <td><a href='detail?no=${product.productNumber}'>${product.productName}</a></td> 
    <td>${product.productType.type}</td> 
    <td>${product.productType.subType}</td> 
    <td>${product.countryOrigin}</td> 
    <td>${product.volume}</td> 
    <td>${product.sugarLevel}</td> 
    <td>${product.acidity}</td> 
    <td>${product.weight}</td> 
    <td>${product.alcoholLevel}</td> 
</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>








