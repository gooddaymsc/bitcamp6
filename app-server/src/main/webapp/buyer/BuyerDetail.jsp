<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    
<!DOCTYPE html>
<html>
<head>
  <title>회원상세</title>
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
<h1>회원 상세(MVC + EL)</h1>

<form action='update'>
    <label for='f-no'>번호</label> 
    <input id='f-no' type='text' name='no' value='${buyer.member.number}' readonly><br>
    
    <label for='f-id'>아이디</label> 
    <input id='f-id' type='text' name='id' value='${buyer.member.id}'><br>
    
    <label for='f-name'>이름</label>
    <input id='f-name' type='text' name='name' value='${buyer.member.name}'><br>
        
    <label for='f-nickname'>닉네임</label> 
    <input id='f-nickname' type='text' name='nickname' value='${buyer.member.nickname}'><br>
    
    <label for='f-level'>레벨</label> 
    <input id='f-level' type='text' name='level' value='${buyer.member.level}'><br>
    
		<label for='f-email'>이메일</label> 
		<input id='f-email' type='email' name='email' value='${buyer.member.email}'><br>
		
		<label for='f-birthday'>생일</label> 
		<input id='f-birthday' type='date' name='birthday' value='${buyer.member.birthday}'><br>
		
		<label for='f-password'>암호</label> 
		<input id='f-password' type='password' name='password' value='${buyer.member.password}'><br>
		
		<label for='f-photo'>사진</label> 
		<input id='f-photo' type='text' name='photo' value='${buyer.member.photo}'><br>
		
		<label for='f-tel'>전화</label> 
		<input id='f-tel' type='tel' name='tel' value='${buyer.member.tel}'><br>
		
		<label for='f-zipcode'>우편번호</label> 
		<input id='f-zipcode' type='text' name='zipcode' value='${buyer.member.zipcode}'><br>
		
		<label for='f-address'>주소</label> 
		<input id='f-address' type='text' name='address' value='${buyer.member.address}'><br>
		
		<label for='f-detailAddress'>상세주소</label> 
		<input id='f-detailAddress' type='text' name='detailAddress' value='${buyer.member.detailAddress}'><br>
    
    <label for='f-registeredDate'>등록일</label> 
    <span id='f-registeredDate'>${buyer.member.registeredDate}</span><br>
<button>변경</button>
 <a href='delete?no=${buyer.member.number}'>[삭제]</a> <a href='list'>[목록]</a><br>
 
</form>

</body>
</html>
