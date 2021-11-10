<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>

<html>
<head>
 <title>비밀번호 찾기</title>
 
  <style>
	#form{
	  margin-left: 100px;
	  }
	  
	 #tel{
	  width:40%;
	  }
	  
	 #findpw-submit { 
    width: 56%;
    margin-top:80px;
    margin-left:10px;
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
  
</head>
<body>
<main id="main-holder">
<br><h1>비밀번호찾기 <img src="../image/icon/004-magnifying-glass.png"></h1>

<form action=findpwResult method='post'id='form'>
<label for='f-name' class="col-sm-2 col-form-label">
  이름</label><br>
 <input id='f-name'  type='text' name='name'><br><br>
 
 <label for='f-id' class="col-sm-2 col-form-label">아이디</label><br>
 <input id='f-id'  type='text' name='id'><br><br>
 
 <div><label for='f-phoneOrEmail' class="col-sm-2 col-form-label" id='tel'>전화번호/이메일</label>
 <input id='f-phoneOrEmail' type='text' name='phoneOrEmail'></div><br><br>
 <button class="btn btn-primary"  id="findpw-submit" >비밀번호찾기</button><br>
</form>
 </main>
</body>
</html>
