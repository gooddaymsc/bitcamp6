<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>댓글</title>
</head>
<body>
<h1>댓글 목록(MVC + EL + JSTL)</h1>
<!-- <a href='form'>이전</a><br> -->
<a href='form'>댓글 작성</a><br>
<table border='1'>
<thead>
  <tr>
    <th>댓글번호</th>
    <th>게시글번호</th>
    <th>아이디</th>
    <th>내용</th>
    <th>등록일</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${commentList}" var="comment">
<tr>
    <td><a href='detail?no=${comment.commentNumber}'>${comment.commentNumber}</a></td>  
    <td>${comment.boardNumber}</td> 
    <td>${comment.writer.id}</td> 
    <td>${comment.content}</td> 
    <td>${comment.registrationDate}</td> 
</tr>
</c:forEach>

</tbody>
</table>
</body>
</html>

