<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<h1>새장바구니</h1>
<form action='add' method='post'>
<input type='hidden' id='f-stockNumber' type='text' name='stockNumber' value='${stockNo}' readOnly><br>
<div class="mb-3 row">
  <label for='f-stocks' class="col-sm-2 col-form-label">수량</label>
  <div class="col-sm-2">
    <input id='f-stocks' type='text' name='stocks' class="form-control">
  </div>
</div>
<button class="btn btn-outline-secondary btn-sm">등록</button><br>
</form>









