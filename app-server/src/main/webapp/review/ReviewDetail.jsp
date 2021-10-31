<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>리뷰상세</title>
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
<h1>리뷰 변경</h1>
<form action='update'>
  <label for='f-reviewNo'>리뷰번호</label> 
    <input id='f-reviewNo' type='text' name='reviewNo' value='${review.no}' readonly><br>
    
    <label for='f-score'>평점</label> 
    <input id='f-score' type='text' name='score' value='${review.score}'><br>
    
    <label for='f-comment'>코멘트</label>
    <input id='f-comment' type='text' name='comment' value='${review.comment}'><br>

    <label for='f-writer'>작성자</label>
    <input id='f-writer' type='text' name='writer' value='${review.member.id}' readOnly><br>
    
    <label for='f-registeredDate' >등록일</label> 
    <input id='f-registeredDate' type='date' name='registeredDate' value='${review.registeredDate}' readOnly><br>
    
<button>변경</button>
 <a href='delete?no=${review.no}'>[삭제]</a> <a href='../detail?no=${review.productNo}'>[목록]</a><br>
</form>

</body>
</html>
