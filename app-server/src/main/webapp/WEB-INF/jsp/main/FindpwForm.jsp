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
    margin-top:0;
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
    
  .btn-sm {
  width:50px;
  margin-top:50px;
  margin-right:400px;
  }
  
  </style>
  
</head>
<body>
<main id="main-holder">
<a href='loginForm' class="btn btn-outline-secondary btn-sm">이전</a>
  <h1>비밀번호찾기 <img src="../../image/icons/004-magnifying-glass.png"></h1>
 <div class="pwform">
<form action=findpwResult id='form' method='post'>
<label for='f-name' class="col-sm-2 col-form-label">
  이름</label><br>
 <input id='f-name'  type='text' name='name'><br><br>
 
 <label for='f-id' class="col-sm-2 col-form-label">아이디</label><br>
 <input id='f-id'  type='text' name='id'><br><br>
 
 <label for='f-phoneOrEmail' class="col-sm-2 col-form-label" id='tel'>전화번호/이메일</label>
 <input id='f-phoneOrEmail' type='text' name='phoneOrEmail'>
 <br>
 <br>
 <button class="btn btn-primary"  id="findpw-submit" >비밀번호찾기</button><br>
</form>
 </div>
 </main>
</body>
</html>