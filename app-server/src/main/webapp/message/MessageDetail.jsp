<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>대화목록</title>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>
    body {
    font-family: '맑은 고딕', 굴림체, 'Apple SD Gothic Neo', sans-serif;
    font-size: medium;
    }
    tr{
      border-style: dotted;
        border-width: 5px;
        border-color: wheat;
    }
    label {
    margin-right: 5px;
    text-align: right;
    width: 80px;
  }
    button{
    text-align: right;
    position : relative; 
        left: 300px;
        bottom: 60px;
    }
    
  </style>
</head>
<body>
<div class="container">
<h1>대화목록</h1>
<form action='update?=${message.roomNumber}'>
<a href='delete?no=${message.roomNumber}'>[나가기]</a> 
<table border='1'>
<c:set var="id" value="${nowLoginId}"/>
<c:forEach items="${messages}" var="message">
<c:choose>
<c:when test="${theOtherId eq nowLoginId}">
    <tr> 
    <td>${message.content}</td> 
    <td>${message.theOtherId}</td> 
    <td>${message.registrationDate}</td>  
    </tr>   
</c:when>
    <c:otherwise>  
    <tr>
    <td>${message.content}</td> 
    <td>${nowLoginId}</td> 
    <td>${message.registrationDate}</td>   
    </tr>
   </c:otherwise>
</c:choose>
</c:forEach>
</table>

 <input id='f-content' type='text' name='content'><br><br>
 <!--  <button class="btn btn-primary" >전송</button> -->
  <button class="btn btn-primary ">전송</button> 
</form>
</div><!-- .container -->
</body>
</html>