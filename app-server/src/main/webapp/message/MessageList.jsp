<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>메세지</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>

<style>
    .container {
        xborder: 1px solid red;
        width: 440px;
    }
       
  </style>
  
</head>
<body>
<div class="contaienr">
<h1>메세지 목록</h1>
 <a href='form' class="btn btn-outline-primary btn-sm">새 채팅</a><br>
<table class="table table-hover">
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
</div><!-- .container -->
</body>
</html>

