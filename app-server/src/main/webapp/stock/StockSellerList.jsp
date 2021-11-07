<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>재고판매자목록</title>
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
<h1>재고판매자목록</h1>
<table class="table table-hover">
<thead>
  <tr>
    <th>가게명</th>
    <th>판매자</th>
    <th>판매주소</th>
    <th>판매장번호</th>
    <th>재고</th>
    <th>금액</th>
    <th>장바구니담기</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${stockSellerList}" var="stock">
<tr>
    <td>${stock.seller.businessName}</td> 
    <td>${stock.seller.member.id}</td> 
    <td>${stock.seller.businessAddress}</td> 
    <td>${stock.seller.businessPlaceNumber}</td> 
    <td>${stock.stocks}</td> 
    <td>${stock.price}</td> 
    <td><a href='../cart/form?no=${stock.stockNumber}'>
    <img src="https://media.istockphoto.com/vectors/add-to-cart-icon-shopping-cart-icon-vector-illustration-vector-id1179275901?k=20&m=1179275901&s=170667a&w=0&h=YN3VNlg_HuhSrcscbxZAdXIaYDF5Mr1CjOFAix7VLaU=" alt="장바구니" height="30" width="30" ></a></td>
    
</tr>
</c:forEach>
</tbody>
</table>
  <a href='../product/detail?no=${stock.product.productNumber}' class="btn btn-primary">이전</a><br>
</div> <!-- .container -->
</body>
</html>








