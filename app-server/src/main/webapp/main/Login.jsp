<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>로그인</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css?">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>
 html { 
  height: 100%; 
 } 
 
 body {
  height: 100%
  margin: 0; 
  font-family: Arial, Helvetica, sans-serif; 
  display: grid; justify-items: center; 
  align-items: center; 
  background-color: #3a3a3a; 
  } 

  html, body {
       height: 100%; 
    } 

  #main-holder {
       width: 50%; 
       height: 70%; 
    }

  #main-holder {
  width: 50%;
  height: 60%; 
  display: grid; 
  justify-items: center; 
  align-items: center; 
  background-color: white; 
  border-radius: 7px; 
  box-shadow: 0px 0px 5px 2px black; } 
  
  #loginLogo{
      margin-left:200px;
      align-self: flex-start; 
      width: 40%;
      height: 50%; 
      display:grid;
      justify-items: center; 
      align-items: center; 
  }
  
  #login-error-msg-holder { 
  width: 100%; 
  height: 100%;
  display: grid; 
  ustify-items: center; 
  align-items: center; 
  } 
  
  #login-error-msg { 
  width: 23%; 
  text-align: center; 
  margin: 0; 
  padding: 5px; 
  font-size: 12px; 
  font-weight: bold; 
  color: #8a0000; 
  border: 1px solid #8a0000; 
  background-color: #e58f8f; 
  opacity: 0; } 
  
  #error-msg-second-line { 
   display: block;
  }
  
  #login-form { 
      align-self: flex-start; 
      display: grid; 
      justify-items: center; 
      align-items: center; 
    }

  .login-form-field::placeholder { 
  color: #3a3a3a; 
  }

  .login-form-field { 
  width: 100%; 
  border: none; 
  border-bottom: 1px solid #3a3a3a; 
  margin-bottom: 10px; 
  border-radius: 3px; 
  outline: none; 
  padding: 0px 0px 5px 5px; 
  }
  
  #login-form-submit { 
  width: 100%; 
  padding: 7px; 
  border: none; 
  border-radius: 5px; 
  color: white; 
  font-weight: bold;
  bold; background-color: #3a3a3a; 
  cursor: pointer; 
  outline: none; 
  }
  </style>
  
</head>
<body>

<main id="main-holder">
<a class="loginLogo" href="../main/Menu.jsp"><img src="../image/loginLogo.jpeg" id="loginLogo"></a>
    
<div id="login-error-msg-holder"> 
<p id="login-error-msg">잘못된 아이디 <span id="error-msg-second-line">비밀번호 입니다.</span></p> </div>


<form action='login' method="post" id="login-form">

<div class='input_row' id="id_line">
<img src="../image/icon/001-user.png">
<input type='text' name='id' placeholder="아이디" maxlength="41">
<span role='button' class='btn btn-outline-primary' id='id_clear' style='display:none;'></span>
</div><!--id_line-->
<br>
<div class='input_row' id="pw_line">
<img src="../image/icon/007-key.png">
<input type='password' name='password' placeholder="비밀번호" maxlength="16">
<span role='button' class='btn_delete' id='pw_clear' style='display:none;'></span>
<br></div><!--pw_line-->
<br>
<br>
<button class="btn btn-primary" id="login-form-submit">LOGIN</button><br>

<div>
<a href="./findidMenu">아이디 </a>
<a href="./findpwMenu">비밀번호찾기 </a>
<a href='../buyer/form' >회원가입</a>
</div>
</form>
</main>
</body>
<jsp:include page="../footer.jsp"></jsp:include>
</html>