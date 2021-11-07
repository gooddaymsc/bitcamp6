<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>내가남긴리뷰목록</title>
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
<h1>내가남긴리뷰목록</h1>
<table class="table table-hover">
<thead>
  <tr>
    <th>상품명</th>
    <th>평점</th>
    <th>한줄평</th>
    <th>등록일</th>
  </tr>
</thead>
<tbody>
<c:forEach items="${reviewList}" var="review">
<tr>
    <td><a href='detail?no=${review.no}'>${review.reviewProduct}</a></td> 
    <td>${review.score}</td> 
    <td>${review.comment}</td> 
    <td>${review.registeredDate}</td> 
</tr>
</c:forEach>
</tbody>
</table>
</div> <!-- .container -->
</body>
</html>
