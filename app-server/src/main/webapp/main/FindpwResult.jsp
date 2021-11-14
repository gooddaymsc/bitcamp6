<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>

<html>
<head>
 <title>비밀번호 변경</title>
</head>
<style>
#findpw-submit { 
    width: 40%;
    xmargin-top:80px;
    xmargin-left:35px;
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
<body>
<main id="main-holder">
<h1>비밀번호 변경</h1>
<form action=findpwForm method='post' id=findpwResult>
  <input type='hidden' name='member_no' id='member_no' value="${member_no}"/>

  <label class='form-control-plaintext'>변경할 새 비밀번호를 입력해주세요.</label><br>

<div>비밀번호  
  <input type='password' name='password' id='pw' onchange="isSame()"/></div>
  
<br>
  <br><div> 비밀번호 확인 
  <input type='password' name='passwordConfirm' id='pwCheck' onchange="isSame()"/>&nbsp;&nbsp;<span id="same"></span></div>
  
<br><br>
<button class="btn btn-primary btn-sm" id="findpw-submit" > 비밀번호 변경 </button>
 <a href="./loginMenu">로그인</a>
 <a href="./findidMenu">아이디찾기</a>

</form>


<script type="text/javascript">
function isSame() {
    var pw = document.getElementById('pw');
    var confirmPW = document.getElementById('pwCheck');
    var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi');
    var num = pw.search(/[0-9]/g);
    var eng = pw.search(/[a-z]/ig);
    if (pw.value.length < 8 || pw.value.length > 12) {
        document.getElementById('pw').value=document.getElementById('pwCheck').value='';
        document.getElementById('same').innerHTML='8 ~ 12자리 이내의 비밀번호를 입력해주세요';
        document.getElementById('same').style.color='red';
    } else if( (num < 0 && eng < 0) || (eng < 0 && spe < 0) || (spe < 0 && num < 0) )
        document.getElementById('same').innerHTML='알파벳, 숫자 및 특수문자를 포함하세요.';
        document.getElementById('same').style.color='red';
          } else if(pw.search(/\s/) != -1){
            document.getElementById('same').innerHTML='공백없이 입력해주세요.';
            document.getElementById('same').style.color='red';
           }
    if(document.getElementById('pw').value!='' && document.getElementById('pwCheck').value!='') {
        if(document.getElementById('pw').value==document.getElementById('pwCheck').value) {
            document.getElementById('same').innerHTML='비밀번호 일치';
            document.getElementById('same').style.color='blue';
        }
        else {
            document.getElementById('same').innerHTML='비밀번호 불일치';
            document.getElementById('same').style.color='red';
      }
    }
  }
</script>
</main>
</body>
</html>