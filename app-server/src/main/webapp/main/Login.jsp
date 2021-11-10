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
    .container {
        border: 0.8px solid;
        border-radius: 14px;
        width: 40%;
        height: 70%;
        margin-top: 100px;
        background-color:#f7f7f7;
     
    } 

    .loginLogo img{
        border: 1px solid orange;
        margin-left: 75px; 
        padding:20px;
        width: 300px;
    }
    
    .inner{
        border: 4px solid #a7adba;
        border-radius: 17px;
        height:450px;
        width:600px;
        margin-left: 10%;    
    }

    /* .inner pw_line{
       position:center;
       height: 200px;  
       margin-left: 10%;      
    }
     */
    div a {
    margin-left: 50px;   
    margin-right: 50px;
    padding-left: 55px;
    font-size:18px;
    font-weight:120;
    position:center;
    
        }
    div a:visited {
        padding-left: 30px;
        color:darkgray;
    }
    div:hover {
        cursor: pointer;
    }

  </style>
</head>
<body>
<div class="container">
<br>
<br>
<form action='login' method="post">
<div class='inner' id='inner'>
<br>
<br>
<div class='input_row' id="id_line">
<input type='text' name='id' placeholder="아이디" maxlength="41">
<span role='button' class='btn btn-outline-primary' id='id_clear' style='display:none;'></span>
</div><!--id_line-->
<br>
<div class='input_row' id="pw_line">
<input type='password' name='password' placeholder="비밀번호" maxlength="16">
<br>
<br>
<span role='button' class='btn_delete' id='pw_clear' style='display:none;'></span>
<br></div><!--pw_line-->
<button class="btn btn-primary">LOGIN</button><br>
</div> 
<br>
<div>
<a href="./findidMenu">아이디 </a>
<a href="./findpwMenu">비밀번호찾기 </a>
<a href='../buyer/form' >회원가입</a>
</div>
</form>
</div><!-- .container -->
</body>
<jsp:include page="../footer.jsp"></jsp:include>
</html>