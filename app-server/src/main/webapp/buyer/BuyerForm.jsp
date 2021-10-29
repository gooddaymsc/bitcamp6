<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>새회원</title>
  <style>
  label {
    margin-right: 5px;
    text-align: right;
    display: inline-block;
    width: 60px;
  }
  </style>
</head>
<body>
<h1>새회원(MVC)</h1>
<form action='add'>
<label for='f-id'>아이디</label> <input id='f-id' type='text' name='id'><br>
<label for='f-name'>이름</label> <input id='f-name' type='text' name='name'><br>
<label for='f-nickname'>닉네임</label> <input id='f-nickname' type='text' name='nickname'><br>
<label for='f-email'>이메일</label> <input id='f-email' type='email' name='email'><br>
<label for='f-birthday'>생일</label> <input id='f-birthday' type='date' name='birthday'><br>
<label for='f-password'>암호</label> <input id='f-password' type='password' name='password'><br>
<label for='f-photo'>사진</label> <input id='f-photo' type='text' name='photo'><br>
<label for='f-tel'>전화</label> <input id='f-tel' type='tel' name='tel'><br>
<label for='f-zipcode'>우편번호</label> <input id='f-zipcode' type='text' name='zipcode'><br>
<label for='f-address'>주소</label> <input id='f-address' type='text' name='address'><br>
<label for='f-detailAddress'>상세주소</label> <input id='f-detailAddress' type='text' name='detailAddress'><br>
<button>등록</button><br>
</form>
</body>
</html>









