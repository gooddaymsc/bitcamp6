<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>댓글상세</title>
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
<h1>댓글 상세</h1>
<form action='update'>
    <label for='f-number'>번호</label> 
    <input id='f-number' type='text' name='commentNumber' value='${comment.commentNumber}' readOnly ><br>

    <label for='f-content'>내용</label> 
    <input id='f-content' type='text' name='content' value='${comment.content}'><br>
    
    <label for='f-writer'>작성자</label>
    <input id='f-writer' type='text' name='writer' value='${comment.writer.id}'readOnly><br>
        
    <label for='f-registrationDate'>등록일</label> 
    <input id='f-registrationDate' type='date' name='registrationDate' value='${comment.registrationDate}'readOnly><br>
  
		
		
<button>변경</button>
 <a href='delete?no=${comment.commentNumber}'>[삭제]</a> <a href='list'>[목록]</a><br>
 
</form>
</body>
</html>
