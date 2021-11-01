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
<h1>새회원(판매자)</h1>
<form action='add'>
<label for='f-id'>아이디</label> <input id='f-id' type='text' name='id'><br>
<label for='f-name'>이름</label> <input id='f-name' type='text' name='name'><br>
<label for='f-nickname'>닉네임</label> <input id='f-nickname' type='text' name='nickname'><br>

<label for='f-email'>이메일</label> <input id='f-email' type='email' name='email'><br>
<label for='f-birthday'>생일</label> <input id='f-birthday' type='date' name='birthday'><br>

<label for='f-password'>암호</label> <input id='f-password' type='password' name='password'><br>
<label for='f-photo'>사진</label> <input id='f-photo' type='text' name='photo'><br>
<label for='f-phoneNumber'>전화</label> <input id='f-phoneNumber' type='tel' name='phoneNumber'><br>

<label for='f-businessName'>가게명</label> <input id='f-businessName' type='text' name='businessName'><br>
<label for='f-businessNumber'>사업자번호</label> <input id='f-businessNumber' type='text' name='businessNumber'><br>
<label for='f-businessAddress'>사업장주소</label> <input id='f-businessAddress' type='text' name='businessAddress'><br>
<label for='f-businessPlaceNumber'>사업장번호</label> <input id='f-businessPlaceNumber' type='text' name='businessPlaceNumber'><br>
<label for='f-businessOpeningTime'>오픈시간</label> <input id='f-businessOpeningTime' type='text' name='businessOpeningTime'><br>
<label for='f-businessClosingTime'>마감시간</label> <input id='f-businessClosingTime' type='text' name='businessClosingTime'><br>
<button>등록</button><br>
</form>
</body>
</html>









