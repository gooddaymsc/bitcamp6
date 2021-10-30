<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>게시판</title>
</head>
<body>
<h1>게시글 목록(MVC + EL + JSTL)</h1>
<!-- <a href='form'>이전</a><br> -->
<a href='form'>게시글 작성</a><br>
<table border='1'>
<thead>
  <tr>
    <th>번호</th>
    <th>제목</th>
    <th>태그</th>
    <th>내용</th>
    <th>작성자</th>
    <th>조회수</th>
    <th>등록일</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${boardList}" var="board">
<tr>
    <td><a href='detail?no=${board.boardNumber}'>${board.boardNumber}</a></td>  
    <td>${board.title}</td> 
    <td>${board.tag}</td> 
    <td>${board.content}</td> 
    <td>${board.writer}</td> 
    <td>${board.views}</td> 
    <td>${board.registrationDate}</td> 
</tr>
</c:forEach>

</tbody>
</table>
</body>
</html>








