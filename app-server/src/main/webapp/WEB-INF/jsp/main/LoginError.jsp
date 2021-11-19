<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
  <title>로그인실패</title>
 
  <style>

  #loginLogo{
   margin-left:40px;
   width: 270px;
   height: 150px; 
   display:grid;
   justify-items: center; 
   align-items: center; 
  }

  </style>

</head>
<body>

<main id="main-holder">
<a class="loginLogo" href="../main/menu"><img src="../../image/loginLogo.jpeg" id="loginLogo"></a>
    
<div id="login-error-msg-holder"> 
<p id="login-error-msg">잘못된 아이디/ 비밀번호 입니다.</p></div>

<form action='login' method="post" id="login-form">

<div class='input_row' id="id_line">
<img src="../../image/icons/001-user.png">
<input type='text' name='id' placeholder="아이디" maxlength="41">
<span role='button' class='btn btn-outline-primary' id='id_clear' style='display:none;'></span>
</div><!--id_line-->
<br>
<div class='input_row' id="pw_line">
<img src="../../image/icons/007-key.png">
<input type='password' name='password' placeholder="비밀번호" maxlength="16">
<span role='button' class='btn_delete' id='pw_clear' style='display:none;'></span>
<br></div><!--pw_line-->
<br>
<br>
<button class="btn btn-primary" id="login-form-submit">LOGIN</button><br>

<div>
<a href="./findidMenu">아이디 |</a>
<a href="./findpwMenu">비밀번호찾기 |</a>
<a href='../buyer/form' >회원가입</a>
</div>
</form>
</main>
</body>
</html>

