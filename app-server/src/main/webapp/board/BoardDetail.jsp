<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>게시글상세</title>
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
<h1>게시글 상세</h1>
<form action='update'>
    <label for='f-title'>제목</label> 
    <input id='f-title' type='text' name='title' value='${board.title}' ><br>
    
    <label for='f-content'>내용</label> 
    <input id='f-content' type='text' name='content' value='${board.content}' readonly><br>
    
    <label for='f-writer'>작성자</label>
    <input id='f-writer' type='text' name='writer' value='${board.writer}' readonly><br>
        
    <label for='f-registrationDate'>등록일</label> 
    <input id='f-registrationDate' type='date' name='registrationDate' value='${board.registrationDate}'><br>
    
    <label for='f-views'>조회수</label> 
    <input id='f-views' type='number' name='views' value='${board.views}' readonly><br>
    
		<label for='f-like'>좋아요 수</label> 
		<input id='f-email' type='email' name='email' value='${buyer.member.email}' readonly><br>
		
		<label for='f-birthday'>태그</label> 
		<input id='f-birthday' type='date' name='birthday' value='${buyer.member.birthday}'><br>
		
		
<button>변경</button>
 <a href='delete?id=${buyer.member.id}'>[삭제]</a> <a href='list'>[목록]</a><br>
 
</form>

</body>
</html>
