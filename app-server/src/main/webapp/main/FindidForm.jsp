<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>

<html>
<head>
 <title>아이디 찾기</title>
</head>
<style>
 #findid-submit { 
    width: 56%;
    margin-top:80px;
    margin-left:35px;
    padding: 7px; 
    border: none; 
    border-radius: 5px; 
    color: white; 
    font-weight: bold;
    background-color: #3a3a3a; 
    cursor: pointer; 
    outline: none; 
  }  
</style>
<body>
<main id="main-holder">

<h1>아이디찾기 <img src="../image/icon/004-magnifying-glass.png"></h1>

<form action='findidResult' method='post'>
 이름<br>
 <input id='f-name'  type='text' name='name'><br>
<br>
  전화번호 또는 이메일<br>
 <input id='f-phoneOrEmail' type='text' name='phoneOrEmail' ><br>
 
<button class="btn btn-primary" id="findid-submit">아이디 찾기</button><br>
</form>
</main>
</body>
</html>

