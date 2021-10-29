<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>회원상세(판매자)</title>
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
<h1>회원 상세(판매자)</h1>
<form action='update'>
    <label for='f-number'>번호</label> 
    <input id='f-number' type='text' name='number' value='${seller.member.number}' readonly><br>
    
    <label for='f-id'>아이디</label>
    <input id='f-id' type='text' name='id' value='${seller.member.id}' readonly><br>
    
    <label for='f-password'>암호</label> 
    <input id='f-password' type='password' name='password'><br>
    
    <label for='f-name'>이름</label>
    <input id='f-name' type='text' name='name' value='${seller.member.name}' readonly><br>
    
    <label for='f-nickname'>닉네임</label>
    <input id='f-nickname' type='text' name='nickname' value='${seller.member.nickname}'><br>
 
    <label for='f-level'>레벨</label>
    <input id='f-level' type='text' name='level' value='${seller.member.level}' readonly><br>
    
    <label for='f-email'>이메일</label> 
    <input id='f-email' type='email' name='email' value='${seller.member.email}'><br>
    
    <label for='f-birthday'>생일</label> 
    <input id='f-birthday' type='date' name='birthday' value='${seller.member.birthday}' readonly><br>
    
    <label for='f-photo'>사진</label> 
    <input id='f-photo' type='text' name='photo' value='${seller.member.photo}'><br>
    
    <label for='f-phoneNumber'>전화</label> 
    <input id='f-phoneNumber' type='tel' name='phoneNumber' value='${seller.member.phoneNumber}'><br>
    
    <label for='f-businessName'>가게명</label> 
    <input id='f-businessName' type='text' name='businessName' value='${seller.businessName}'><br>
    
    <label for='f-businessNumber'>사업자번호</label> 
    <input id='f-businessNumber' type='text' name='businessNumber' value='${seller.businessNumber}'><br>
    
    <label for='f-businessAddress'>사업장주소</label> 
    <input id='f-businessAddress' type='text' name='businessAddress' value='${seller.businessAddress}'><br>
    
    <label for='f-businessPlaceNumber'>사업장번호</label> 
    <input id='f-businessPlaceNumber' type='text' name='businessPlaceNumber' value='${seller.businessPlaceNumber}'><br>
    
    <label for='f-businessOpeningTime'>오픈시간</label> 
    <input id='f-businessOpeningTime' type='text' name='businessOpeningTime' value='${seller.businessOpeningTime}'><br>
    
    <label for='f-businessClosingTime'>마감시간</label> 
    <input id='f-businessClosingTime' type='text' name='businessClosingTime' value='${seller.businessClosingTime}'><br>
    
    <label for='f-authority'>권한</label> 
    <input id='f-authority' type='text' name='authority' value='${seller.member.authority}' readonly><br>
    
    <label for='f-registeredDate'>등록일</label> 
    <span id='f-registeredDate'>${seller.member.registeredDate}</span><br>
<button>변경</button>
 <a href='delete?id=${seller.member.id}'>[삭제]</a> <a href='list'>[목록]</a><br>
</form>

</body>
</html>
