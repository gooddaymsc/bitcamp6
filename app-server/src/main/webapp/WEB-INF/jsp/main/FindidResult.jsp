<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<html>
<head>
 <title>아이디찾기 결과</title>
</head>

<style>
  #find-id-msg-holder { 
  width: 100%; 
  height: 100%;
  display: grid; 
  justify-items: center; 
  align-items: center; 
  margin-bottom:170px;
  } 
  
  #find-id-msg { 
  width: 400px; 
  height: 100px;
  text-align: center; 
  margin-top: 0px;
  padding: 30px; 
  font-size: 20px; 
  font-weight: bold; 
  background-color: #F1EFDC; 
  opacity: 70%
  }
</style>

<body>
<main id="main-holder">
<h1>아이디찾기 결과</h1>
<form method='post' id='findidResult'>

<div id="find-id-msg-holder"> 
<p id="find-id-msg">아이디는 ${id}  입니다 </p></div>

<br><br>
 <a href="./loginForm">로그인</a>
 <a href="./findpwMenu">비밀번호찾기</a>

</form>
</main>
</body>
</html>
