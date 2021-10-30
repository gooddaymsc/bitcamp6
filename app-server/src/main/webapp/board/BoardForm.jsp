<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>새 게시글</title>
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
<h1>게시글 작성</h1>
<form action='add'>
<label for='f-title'>제목</label> 
<input id='f-title' type='text' name='title'><br>

<label for='f-content'>내용</label> 
<input id='f-content' type='text' name='content'><br>

<label for='f-tag'>태그</label> 
<input id='f-tag' type='text' name='tag'><br>

<button>등록</button><br>
</form>
</body>
</html>









