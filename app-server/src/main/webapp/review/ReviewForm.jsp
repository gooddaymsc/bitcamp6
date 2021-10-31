<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>새리뷰</title>
  <style>
  label {
    margin-right: 5px;
    text-align: right;
    display: inline-block;
    width: 60px;
  }
  </style>
</head>
<body>
<h1>새리뷰</h1>
<form action='add'>  
<label for='f-productNumber'>상품번호</label><input id='f-productNumber' type='text' name='productNumber' value='${productNumber}' readOnly><br>
  <label for='f-score'>평점</label><input id='f-score' type='text' name='score'><br>
  <label for='f-comment'>코멘트</label><input id='f-comment' type='text' name='comment'><br>
  <label for='f-writer'>작성자</label><input id='f-writer' type='text' name='writer'><br>
<button>등록</button><br>
</form>
</body>
</html>









