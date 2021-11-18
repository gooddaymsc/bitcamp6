<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<h1>게시글 수정</h1>
<form action='update' method='post'>
    <input type='hidden' id='f-number' type='text' name='boardNumber' value='${board.boardNumber}'  ><br>
<div class="mb-3 row">
  <label for='f-title' class="col-sm-2 col-form-label">제목</label>
  <div class="col-sm-6">
    <input id='f-title' type='text' name='title' value='${board.title}' ><br>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-content' class="col-sm-2 col-form-label">내용</label> 
  <div class="col-sm-6">
    <input id='f-content' type='text' name='content' value='${board.content}'><br>
  </div>
</div>
<%--     <input type='hidden' id='f-writer' type='text' name='writer' value='${board.writer.id}'readOnly><br>
 --%><div class="mb-3 row">
	<label for='f-tag' class="col-sm-2 col-form-label">태그</label> 
  <div class="col-sm-6">
		<input id='f-tag' type='text' name='tag' value='${board.tag}'><br>
    <input type='hidden' id='f-tagNo' type='text' name='boardTag.tagNumber' value='${board.boardTag.tagNumber}'><br>
  </div>
</div>
		
    <button class="btn btn-outline-secondary btn-sm">변경</button>
    <a href='list' class="btn btn-outline-secondary btn-sm">목록</a>
</form>

