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
<h1> 오늘의 술 </h1>

<tbody>
<tr>
<c:forEach items="${productList}" var="product">
<div class="card" style="width: 14rem;">
  <img src="..." class="card-img-top" alt="...">
  <div class="card-body">
    <h5><a href="detail?no=${product.productNumber}" class="card-title">${product.productName}</a></h5>
    <p class="card-text">평점: ${product.rate}점 </p> 
  </div>
  <ul class="list-group list-group-flush">
    <li class="list-group-item">주종:${product.productType.type} - ${product.productType.subType}</li>
    <li class="list-group-item">도수:${product.alcoholLevel}</li>
    <li class="list-group-item">원산지:${product.countryOrigin}</li>
  </ul>
</div>
</c:forEach>

</tbody>
</div> <!-- .container -->
</body>
</html>







