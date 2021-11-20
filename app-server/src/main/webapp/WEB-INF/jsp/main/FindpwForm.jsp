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
 <button class="btn btn-primary" onclick="btn_pw()" id="findpw-submit" >비밀번호찾기</button><br>
</form>
 </div>
 </main>
 <script>
function btn_pw(){
  var name = document.getElementById('f-name').value;
  var id = document.getElementById('f-id').value;
  var phoneOrEmail = document.getElementById('f-phoneOrEmail').value;
  var xhr = new XMLHttpRequest();
  xhr.addEventListener("load", function() {
        if (this.responseText == "false") {
            return false;
        } else {
            alert("일치하는 회원정보가 존재하지 않습니다.\n다시 입력해 주세요.");
            return location.href='findpwMenu';
        }
      })
  xhr.open("get", "./checkPw?name=" + name+"&id=" +id+"&phoneOrEmail="+phoneOrEmail);
  xhr.send();
}
</script>
</body>
</html>