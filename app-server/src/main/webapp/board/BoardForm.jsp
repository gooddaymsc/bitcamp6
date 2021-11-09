<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<h1>게시글 작성</h1>
<form action='add' method='post'>
<div class="mb-3 row">
  <label for='f-title' class="col-sm-2 col-form-label">제목</label> 
  <div class="col-sm-6">
    <input id='f-title' type='text' name='title'><br>
  </div>
</div>

<div class="mb-3 row">
  <label for='f-content' class="col-sm-2 col-form-label">내용</label> 
  <div class="col-sm-6">
    <input id='f-content' type='text' name='content'><br>
  </div>
</div>

<div class="mb-3 row">
  <label for='f-tag' class="col-sm-2 col-form-label">태그</label> 
  <div class="col-sm-6">
    <input id='f-tag' type='text' name='tag'><br>
  </div>
</div>

<button class="btn btn-primary btn-sm">등록</button><br>
</form>









