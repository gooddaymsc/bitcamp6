<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
 
<h1>새리뷰</h1>
<form action='add' method='post'>  
    <input type='hidden' id='f-productNumber' type='text' name='productNumber' class="form-control" value='${productNumber}' readOnly>
<div class="mb-3 row">
  <label for='f-score' class="col-sm-2 col-form-label">평점</label>
  <div class="col-sm-6">
    <select id='f-score' class="form-select" name='score' aria-label="Default select example">
      <option value="1" selected>1</option>
      <option value="2">2</option>
      <option value="3">3</option>
      <option value="4">4</option>
      <option value="5">5</option>
    </select>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-comment' class="col-sm-2 col-form-label">코멘트</label>
  <div class="col-sm-6">
    <input id='f-comment' type='text' name='comment' class="form-control">
  </div>
</div>
<button class="btn btn-primary btn-sm">등록</button><br>
</form>











