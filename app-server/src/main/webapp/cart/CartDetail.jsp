<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<style>
a {
    text-decoration: none;
    color: black;
}
button {
  position: relative;
  top: 100px;
  float: right;
}

</style>
<h1>장바구니 상세</h1>
<img id="f-photo-image" src="../upload/product/${cart.stock.product.photo}_1000x1000.jpg" 
        align="left" width="300px" height="450px" >
        
  <form action='update' method='post'>
    <div class="mb-3 row">
     <div class="col-sm-6">
      <input type='hidden' id='f-cartNumber' type='text' name='cartNumber' class="form-control" value='${cart.cartNumber}' readonly>
      </div>
    </div>
    
    <div class="mb-3 row">
      <div class="col-sm-6">
      <input type='hidden' id='f-buyerId' type='text' name='buyerId' class="form-control" value='${cart.id}' readonly>
     </div>
    </div>
    
    <div class="mb-3 row">
      <label for='f-businessName' class="col-sm-3 col-form-label">가게명</label>
      <div class="col-sm-6">
      <input id='f-businessName' type='text' name='businessName' class="form-control" value='${cart.stock.seller.businessName}' readonly>
     </div>
    </div>
    
    <div class="mb-3 row">
      <label for='f-sellerid' class="col-sm-3 col-form-label">판매자</label>
      <div class="col-sm-6">
      <input id='f-sellerid' type='text' name='sellerid' class="form-control" value='${cart.stock.seller.member.id}' readonly>
     </div>
    </div>
    
    <div class="mb-3 row">
      <label for='f-productName' class="col-sm-3 col-form-label">상품명</label>
       <div class="col-sm-6">
       <input id='f-productName' type='text' name='productName' class="form-control" value='${cart.stock.product.productName}' readonly>
      </div>
    </div>
    <div class="mb-3 row">
      <label for='f-price' class="col-sm-3 col-form-label">개당금액</label>
      <div class="col-sm-6">
      <input id='f-price' type='text' name='price' class="form-control" value='${cart.stock.price}'readonly>
     </div>
  </div>

    <div class="mb-3 row">
      <label for='f-stocks' class="col-sm-3 col-form-label">수량</label>
      <div class="col-sm-6">
      <input id='f-stocks' type='text' name='stocks' class="form-control" value='${cart.cartStocks}'><br>
      </div>
    </div>
    

<button class="btn btn-outline-secondary btn-sm">변경</button>
<button type="button" onclick="location.href='delete?no=${cart.cartNumber}'" class="btn btn-outline-secondary btn-sm">삭제</button>
<button type="button" onclick="location.href='list'" class="btn btn-outline-secondary btn-sm">목록</button>
<button type="button" onclick="location.href='../booking/form?no=${cart.cartNumber}&id=${cart.id}'" class="btn btn-outline-secondary btn-sm">예약 등록</button>
</form>
