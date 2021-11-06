<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>아이디찾기</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>
    .container {
        xborder: 1px solid red;
        width: 640px;
    }
    
     #state {
    border: 1px solid red;
    font-family: myfont
    position: relative;
    top: 3px;
    display: inline-block;
    width: 16px;
    height: 16px;
    margin-left: 2px;
    font-size: 20px;
  }

  </style>
</head>
<body>
<div class="container">
<h1>아이디찾기</h1>
<form>
 <label for='f-rate' class="col-sm-2 col-form-label">이름</label>
<!--    <div id="state"></div> -->
 <input type='text' name='name'> 
 <button class="btn btn-primary">확인</button><br> 
 <label for='f-rate' class="col-sm-2 col-form-label">전화번호/이메일</label>
 <input type='text' name='phoneOrEmail'><br><br>
 <button class="btn btn-primary">아이디찾기</button><br> 
</form>

<script>
var name = document.getElementById('name');
var phoneOrEmail = document.getElementById('phoneOrEmail');

btn.addEventListener('click', function() {
	  if(name.value == ${name})
		  state.innerHTML = "\uf00d";
	    state.style["color"] = "#0000ff";
} else {
    state.innerHTML = "\uf00c";
    state.style.color = "#ff0000";
  }
  
</script>
</div><!-- .container -->
</html>