<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<h1>새 재고</h1>
<form action='add' method='post'>
<div class="mb-3 row">
  <label for='f-productNumber' class="col-sm-2 col-form-label">상품번호</label>
  <div class="col-sm-6">
    <input id='f-productNumber' type='text' name='product.productNumber' class="form-control" value='${productNo}' readOnly>
  </div>
    <div class="col-auto">
    <button id="x-name-check-btn" type="button" class="btn btn-primary form-control">중복검사</button>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-price' class="col-sm-2 col-form-label">가격</label>
  <div class="col-sm-6">
    <input id='f-price' type='number' name='price' class="form-control">
  </div>
</div>
<div class="mb-3 row">
  <label for='f-stocks' class="col-sm-2 col-form-label">수량</label>
  <div class="col-sm-6">
    <input id='f-stocks' type="number" name='stocks' class="form-control">
  </div>
</div>
<button class="btn btn-outline-secondary btn-sm">등록</button><br>
</form>

