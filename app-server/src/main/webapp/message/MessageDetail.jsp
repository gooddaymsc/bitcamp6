<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>대확목록</title>
  <style>

    label {
      border: 1px solid red;
      padding: 5px;
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
  </style>
</head>
<body>
<h1>대화목록</h1>

<c:forEach items="${messages}" var="message">
<tr> 
    <td>${message.content}</td> 
    <td>${message.theOtherId}</td> 
    <td>${message.registrationDate}</td>  
</tr>
</c:forEach>

<form action='update'>
  <label for='f-content'></label> 
  <input id='f-content' type='text' name='content'><br>
  

</form>
</body>
</html>
