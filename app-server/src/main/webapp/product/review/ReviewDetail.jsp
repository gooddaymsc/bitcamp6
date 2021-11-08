<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
  <title>리뷰상세</title>
  <link rel="stylesheet" href="../../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>
    .container {
        width: 640px;
    }
  </style>
</head>
<body>
<div class="container">
<h1>리뷰 변경</h1>
<form action='update'>
    <input type='hidden' id='f-reviewNo' type='text' name='reviewNo' class="form-control" value='${review.no}' readonly>
<div class="mb-3 row">
  <label for='f-score' class="col-sm-2 col-form-label">평점</label>
  <div class="col-sm-6">
    <input id='f-score' type='text' name='score' class="form-control" value='${review.score}' >
  </div>
</div>
<div class="mb-3 row">
  <label for='f-comment' class="col-sm-2 col-form-label">코멘트</label>
  <div class="col-sm-6">
    <input id='f-comment' type='text' name='comment' class="form-control" value='${review.comment}'>
  </div>
    <input type='hidden' id='f-writer' type='text' name='writer' class="form-control" value='${review.member.id}' readonly>
</div>
    <input type='hidden' id='f-registeredDate' type='text' name='registeredDate' class="form-control" value='${review.registeredDate}' readonly>
<c:choose> 
  <c:when test="${loginUser.authority eq 8}">
    <a href='delete?no=${review.no}' class="btn btn-primary">삭제</a> 
  </c:when>
  <c:when test="${loginUser.authority eq 2}">
  <button class="btn btn-primary">변경</button>
    <a href='delete?no=${review.no}' class="btn btn-primary">삭제</a> 
  </c:when>
</c:choose>
<a href='../detail?no=${review.productNo}' class="btn btn-primary">목록</a><br>

</form>
</div> <!-- container -->
</body>
</html>
