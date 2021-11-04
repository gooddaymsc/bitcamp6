<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>장바구니상세</title>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>
   .container {
        xborder: 1px solid red;
        width: 640px;
    }
 /*  label {
    margin-right: 5px;
    text-align: right;
    display: inline-block;
    width: 60px;
  } */
  </style>
</head>
<body>
<h1>장바구니 상세</h1>
  <form action='update'>
    <div class="mb-3 row">
     <label for='f-cartNumber' class="col-sm-2 col-form-label">번호</label>
     <div class="col-sm-6">
      <input id='f-cartNumber' type='text' name='cartNumber' class="form-control" value='${cart.cartNumber}' readonly>
      </div>
    </div>
    
    <div class="mb-3 row">
      <label for='f-buyerId' class="col-sm-2 col-form-label">구매자</label>
      <div class="col-sm-6">
      <input id='f-buyerId' type='text' name='buyerId' class="form-control" value='${cart.id}' readonly>
     </div>
    </div>
    
    <div class="mb-3 row">
      <label for='f-businessName' class="col-sm-2 col-form-label">가게명</label>
      <div class="col-sm-6">
      <input id='f-businessName' type='text' name='businessName' class="form-control" value='${cart.}' readonly>
     </div>
    </div>
    
    <div class="mb-3 row">
      <label for='f-sellerid' class="col-sm-2 col-form-label">판매자</label>
      <div class="col-sm-6">
      <input id='f-sellerid' type='text' name='sellerid' class="form-control" value='${cart.stock.seller.member.id}' readonly>
     </div>
    </div>
    
    <div class="mb-3 row">
      <label for='f-productName' class="col-sm-2 col-form-label">상품명</label>
       <div class="col-sm-6">
       <input id='f-productName' type='text' name='productName' class="form-control" value='${cart.stock.product.productName}' readonly>
      </div>
    </div>

    <div class="mb-3 row">
      <label for='f-stocks' class="col-sm-2 col-form-label">수량</label>
      <div class="col-sm-2">
      <input id='f-stocks' type='text' name='stocks' class="form-control" value='${cart.cartStocks}'><br>
      </div>
    </div>
    
    <div class="mb-3 row">
      <label for='f-price' class="col-sm-2 col-form-label">개당금액</label>
      <div class="col-sm-4">
      <input id='f-price' type='text' name='price' class="form-control" value='${cart.stock.price}'readonly>
     </div>
  </div>


<button class="btn btn-primary">변경</button>
 <a href='delete?no=${cart.cartNumber}' class="btn btn-primary">삭제</a> 
 <a href='list' class="btn btn-primary">목록</a><br>
 <br><a href='../booking/form?no=${cart.cartNumber}&id=${cart.id}' class="btn btn-primary">예약 등록</a>
</form>

</body>
</html>
