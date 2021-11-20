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
  
  .btn-sm {
  width:50px;
  margin-right:400px;
  }
  
</style>
<body>
<main id="main-holder">
<a href='loginForm' class="btn btn-outline-secondary btn-sm">이전</a>
  
<h1>아이디찾기 <img src="../../image/icons/004-magnifying-glass.png"></h1>

<form action='findidResult' method="post">
 이름<br>
 <input id='f-name'  type='text' name='name'><br>
<br>
  전화번호 또는 이메일<br>
 <input id='f-phoneOrEmail' type='text' name='phoneOrEmail' ><br>
 
<button class="btn btn-primary" onclick="btn_id()" id="findid-submit">아이디 찾기</button><br>
</form>
</main>
<script>
function btn_id(){
	var name = document.getElementById('f-name').value;
	var phoneOrEmail = document.getElementById('f-phoneOrEmail').value;
  var xhr = new XMLHttpRequest();
  xhr.addEventListener("load", function() {
        if (this.responseText == "false") {
        	  return false;
        } else {
            alert("일치하는 회원정보가 존재하지 않습니다.\n다시 입력해 주세요.");
            return location.href='findidMenu';
        }
      })
  xhr.open("get", "./checkId?name=" + name+"&phoneOrEmail="+phoneOrEmail);
  xhr.send();
}
</script>
</body>

</html>

