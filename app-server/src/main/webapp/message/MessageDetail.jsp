<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>대화목록</title>
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
    display: inline-block;
    width: 60px;
  }
    #f-content{
    width: 300px;
    height: 20px;
    background-size: 16px;
    background-repeat: no-repeat;
    background-position: top right;
    padding-right: 50px;
    }
    button{
    text-align: right;
    width: 40px;
    height: 30px;
    position : relative; 
        left: 380px;
        bottom: 25px;
    }
    
  </style>
</head>
<body>
<h1>대화목록</h1>
<a href='delete?no=${roomNo}'>[나가기]</a> 
<table border='1'>
<c:set var="theOtherId" value="${newLoginId}"/>
<c:forEach items="${messages}" var="message">
<c:choose>
<c:when test="${theOtherId eq newLoginId}">
    <tr> 
    <td>${message.content}</td> 
    <td>${message.theOtherId}</td> 
    <td>${message.registrationDate}</td>  
    </tr>   
</c:when>
    <c:otherwise>  
    <tr>
    <td>${message.content}</td> 
    <td>${nowLogintId}</td> 
    <td>${message.registrationDate}</td>   
    </tr>
   </c:otherwise>
</c:choose>
</c:forEach>
</table>

<form action='update'>
<div><input id='f-content' type='text' name='content'><br></div>
<button>전송</button>
</form>

</body>
</html>
