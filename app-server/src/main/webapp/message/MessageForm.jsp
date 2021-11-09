<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<h1>새 메세지</h1>
<a href='list' class="btn btn-outline-primary btn-sm">이전</a><br>

<form action='add' method='post'>
<div class="mb-3 row">
  <label for='f-theOtherId' class="col-sm-2 col-form-label">대화할 상대(id)</label> 
  <div class="col-sm-6">
    <input id='f-theOtherId' type='text' name='theOtherId' value='${theOtherId}'><br>
  </div>
</div>

<div class="mb-3 row">
  <label for='f-content' class="col-sm-2 col-form-label">내용</label> 
  <div class="col-sm-6">
    <input id='f-content' type='text' name='content'><br>
  </div>
</div>

<button class="btn btn-primary btn-sm">보내기</button><br>
</form>









