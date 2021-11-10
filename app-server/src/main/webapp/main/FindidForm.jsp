<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>

<html>
<head>
 <title>아이디 찾기</title>
</head>
<body>
<main id="main-holder">
<br><h1>아이디찾기 <img src="../image/icon/004-magnifying-glass.png"></h1>
<form action=findidResult method='post' id='form'>
 이름<br>
 <input id='f-name'  type='text' name='name'><br>
<br>
  전화번호 또는 이메일<br>
 <input id='f-phoneOrEmail' type='text' name='phoneOrEmail'  ><br>
</form>
<button class="btn btn-primary" id="login-form-submit">아이디찾기</button><br>
</main>
</body>
</html>

