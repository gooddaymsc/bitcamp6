<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
 <title>아이디 찾기</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>
  
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
  height: 60%; 
  display: grid; 
  justify-items: center; 
  align-items: center; 
  background-color: white; 
  border-radius: 7px; 
  box-shadow: 0px 0px 5px 2px black; 
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
  width: 30%; 
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
<br><h1>아이디찾기 <img src="../image/icon/004-magnifying-glass.png"></h1>
<form action=findidResult method='post' id='form'>
 이름<br>
 <input id='f-name'  type='text' name='name'><br>
 <span role='button' class='btn_delete' id='pw_clear' style='display:none;'></span>
<br>
  전화번호 또는 이메일<br>
 <input id='f-phoneOrEmail' type='text' name='phoneOrEmail'  ><br>
<span role='button' class='btn_delete' id='pw_clear' style='display:none;'></span> 
</form>
<button class="btn btn-primary" id="login-form-submit">아이디찾기</button><br>
</main>
</body>
<jsp:include page="../footer.jsp"></jsp:include>
</html>

