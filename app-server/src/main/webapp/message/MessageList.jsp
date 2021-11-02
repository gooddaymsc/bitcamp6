<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>메세지</title>
</head>
<body>
<h1>메세지 목록</h1>
<table border='1'>
<thead>
  <tr>
    <th>번호</th>
    <th>보낸사람</th>
    <th>내용</th>
    <th>등록일</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${messages}" var="message">
<tr>
    <td><a href='detail?no=${message.roomNumber}'>${message.roomNumber}</a></td>  
    <td>${message.theOtherId}</td> 
    <td>${message.content}</td> 
    <td>${message.registrationDate}</td> 
</tr>
</c:forEach>

</tbody>
</table>
</body>
</html>

