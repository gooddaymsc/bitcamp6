  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>재고상세</h1>
<form action='update' method='post'>
  <input type='hidden' id='btn-seller' type='text' name='seller.member.id' value='${stock.seller.member.id}'readOnly><br>
<div class="mb-3 row">
  <label for='f-no' class="col-sm-2 col-form-label">재고번호</label>
  <div class="col-sm-6">
    <input id='f-no' type='text' name='stockNumber' class="form-control" value='${stock.stockNumber}' readonly>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-productName' class="col-sm-2 col-form-label">상품</label>
  <div class="col-sm-6">
    <input id='f-productName' type='text' name='productName' class="form-control" value='${stock.product.productName}' readonly>
  </div>
</div>  
<div class="mb-3 row">
  <label for='f-price' class="col-sm-2 col-form-label">가격</label>
  <div class="col-sm-6">
    <input id='f-price' type='text' name='price' class="form-control" value='${stock.price}'>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-stocks' class="col-sm-2 col-form-label">수량</label>
  <div class="col-sm-6">
    <input id='f-stocks' type='text' name='stocks' class="form-control" value='${stock.stocks}'>
  </div>
</div>
 <c:choose> 
    <c:when test="${loginUser.authority eq 4}">
    <button  onclick="btn_update('${loginUser.id}')" class="btn btn-outline-secondary btn-sm">변경</button>
     <a href='delete?no=${stock.stockNumber}'  onclick="btn_delete('${loginUser.id}')" class="btn btn-outline-secondary btn-sm">삭제</a><br>
  </c:when>
  <c:when test="${loginUser.authority eq 2}">
   <a href='../cart/form?no=${stock.stockNumber}' class="btn btn-outline-secondary btn-sm">장바구니 등록</a><br>
  </c:when>
</c:choose>
    <a href='list?id=${stock.seller.member.id}' class="btn btn-outline-secondary btn-sm">목록</a><br>
</form>

<script>
function btn_update(id) {
 var no = document.getElementById('f-no').value;
 const seller_id = document.getElementById('btn-seller').value;
   if (id==seller_id) {
      location.href="${contextRoot}/drinker/app/stock/update";
   } else {
     alert("해당 판매자가 아니므로 수정할 수 없습니다.");
     return false;
   }
 }
</script>
<script>
function btn_delete(id) {
  var no = document.getElementById('f-no').value;
  const seller_id = document.getElementById('btn-seller').value;
    if ((id==seller_id)||(id=="admin")) {
      if (confirm("정말 삭제하시겠습니까?")==true) {
         location.href="${contextRoot}/drinker/app/stock/delete?no="+no;
      } else {
        return false;
      }
    } else {
      alert("해당 판매자가 아니므로 삭제할 수 없습니다.");
      return false;
    }
  }
</script> 

