<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>새 댓글</title>
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
<h1>댓글작성</h1>
<form action='add'>
<label for='f-number'>게시판번호</label> 
<input id='f-number' type='text' name='boardNumber' value='${boardNo}' readOnly><br>
<label for='f-content'>내용</label> 
<input id='f-content' type='text' name='content'><br>
<label for='f-writer'>댓글작성자</label> 
<input id='f-writer' type='text' name='writer'><br>
<button>등록</button><br>
</form>
</body>
</html>









