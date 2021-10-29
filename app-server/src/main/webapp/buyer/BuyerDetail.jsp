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
<!--  
 action='update'
 -->
<form>
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
    
    <label for='f-registeredDate'>등록일</label> 
    <span id='f-registeredDate'>${buyer.member.registeredDate}</span><br>
<button>변경</button>
 <!--
 <a href='delete?no=${buyer.member.number}'>[삭제]</a> <a href='list'>[목록]</a><br>
   -->
 
</form>

</body>
</html>
