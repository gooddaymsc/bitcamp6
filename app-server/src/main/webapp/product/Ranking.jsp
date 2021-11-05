<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title></title>
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
<h1>Ranking</h1>

<!-- <thead>
  <tr>
    <th>상품번호</th>
    <th>상품명</th>
    <th>평점</th>
    <th>주종</th>
    <th>상세주종</th>
    <th>원산지</th>
    <th>도수</th>
  </tr>
</thead> -->
<tbody>
<c:forEach items="${productList}" var="product">

<div class="card" style="width: 14rem;">
  <img src="..." class="card-img-top" alt="...">
  <div class="card-body">
    <h5 class="card-title">${product.productName}</h5>
    <p class="card-text">평점 - ${product.rate} </p>
  </div>
  <ul class="list-group list-group-flush">
    <li class="list-group-item">${product.productType.type} - ${product.productType.subType}</li>
    <li class="list-group-item"></li>
    <li class="list-group-item">A third item</li>
  </ul>
  <div class="card-body">
    <a href="#" class="card-link">상품정보보기</a>
  </div>
</div>
  <%--   <td><a href='detail?no=${product.productNumber}'>${product.productNumber}</a></td> --%>
<%--     <td>${product.productName}</td> 
    <td>${product.rate}</td> 
    <td>${product.productType.type}</td> 
    <td>${product.productType.subType}</td> 
    <td>${product.countryOrigin}</td> 
    <td>${product.alcoholLevel}</td> 
 --%>
</c:forEach>
</tbody>

</div> <!-- .container -->
</body>
</html>







