<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<h1>댓글 상세</h1>
<form action='update' method='post'>
<div class="mb-3 row">
  <label for='f-number' class="col-sm-2 col-form-label">번호</label> 
  <div class="col-sm-6">
    <input id='f-number' type='text' name='commentNumber' class="form-control" value='${comment.commentNumber}' readOnly ><br>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-content' class="col-sm-2 col-form-label">내용</label> 
  <div class="col-sm-6">
    <input id='f-content' type='text' name='content' class="form-control" value='${comment.content}'><br>
  </div>
</div>   
<div class="mb-3 row">
  <label for='f-writer' class="col-sm-2 col-form-label">작성자</label>
  <div class="col-sm-6">
    <input id='f-writer' type='text' name='writer' class="form-control" value='${comment.writer.id}'readOnly><br>
  </div>
</div>       
<div class="mb-3 row"> 
  <label for='f-registrationDate' class="col-sm-2 col-form-label">등록일</label> 
  <div class="col-sm-6">
    <input id='f-registrationDate' type='text' name='registrationDate' class="form-control-plaintext" value='${comment.registrationDate}'readOnly><br>
  </div>
</div>  
		
		
<button class="btn btn-primary">변경</button>
 <a href='delete?no=${comment.commentNumber}' class="btn btn-primary">삭제</a> 
 <a href='../detail?no=${comment.boardNumber}' class="btn btn-primary">목록</a><br>
 
</form>
