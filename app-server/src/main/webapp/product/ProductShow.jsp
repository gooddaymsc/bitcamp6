<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<style>
#product_top_detail{
 float:right;
 margin-right: 700px;

}

#product_title{
font-size: 30px; 
font-weight: bold; 
font-family: Cafe24Oneprettynight;

}

#product_type{
font-size: 25px; 
font-family: Cafe24Oneprettynight;
color: #777777;
}


#product_detail2{
font-size: 23px; 
font-family: Cafe24Oneprettynight;
}


a {
    text-decoration: none;
    color: black;
}
button {
  float: right;
}



</style>

<img id="f-photo-image" src="../upload/product/${product.photo}_1000x1000.jpg" 
        align="left" width="300px" height="500px">
<form action='update' method='post' enctype="multipart/form-data">
    <input type='hidden' id='f-productNumber' type='text' name='productNumber' class="form-control" value='${product.productNumber}' readonly>
    
<div id="product_top_detail" >        
 <div id="product_type">
  <div id='f-name'> ${product.productType.type} > ${product.productType.subType} </div>
 </div>
  <br>
  <div  id="product_title">
    <div id='f-name'> ${product.productName} </div>
  </div>
 

  <div id="product_detail2">
   <div id='f-rate'> ${product.rate}</div>
   <div id='f-rate'> 당도> ${product.sugarLevel}</div>
   <div id='f-rate'> 산도> ${product.acidity}</div>
   <div id='f-rate'> 바디감> ${product.weight}</div>
  </div>
</div>

<div class="mb-3 row">
<label for='f-countryOrigin' class="col-sm-2 col-form-label">원산지</label>
  <div class="col-sm-6">
    <input id='f-countryOrigin' type='text' name='countryOrigin' class="form-control" value='${product.countryOrigin}'readonly>
  </div>
</div>
<c:choose> 
  <c:when test="${product.productType.type eq '와인'}">
    <div class="mb-3 row">
      <label for='f-variety' class="col-sm-2 col-form-label">품종</label>
      <div class="col-sm-6">
        <input id='f-variety' type='text' name='variety' class="form-control" value='${product.variety}' readonly>
      </div>
    </div>
  </c:when>
</c:choose>

<div class="mb-3 row">
  <label for='f-volume' class="col-sm-2 col-form-label">용량</label>
  <div class="col-sm-6">
    <input id='f-volume' type='text' name='volume' class="form-control" value='${product.volume}' readonly>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-alcoholLevel' class="col-sm-2 col-form-label">도수</label>
  <div class="col-sm-6">
    <input id='f-alcoholLevel' type='text' name='alcoholLevel' class="form-control" value='${product.alcoholLevel}' readonly>
  </div>
</div>



<button type="button" onclick="location.href='listType?type=${product.productType.type}'" class="btn btn-outline-success">목록</button>
<c:choose> 
  <c:when test="${loginUser.authority eq 8}">
    <button type="button" onclick="location.href='detail?no=${product.productNumber}'" class="btn btn-outline-success">상품정보수정</button>
  </c:when>
  <c:when test="${loginUser.authority eq 4}">
    <button type="button" onclick="location.href='../stock/form?productNumber=${product.productNumber}'" class="btn btn-outline-success">재고등록</button>
    <button type="button" onclick="location.href='detail?no=${product.productNumber}'" class="btn btn-outline-success">상품정보수정</button>
  </c:when>
  <c:when test="${loginUser.authority eq 2}">
    <button type="button" onclick="location.href='../stock/sellerList?no=${product.productNumber}'" class="btn btn-outline-success">장바구니등록</button>
  </c:when>
</c:choose>
</form><br>

<h4><i class="far fa-star"></i>최근 리뷰<i class="far fa-star"></i></h4>

<c:choose> 
  <c:when test="${loginUser.authority eq 2}">
  <i class="far fa-hand-point-right"></i>
    <a href='review/form?no=${product.productNumber}'> 리뷰남기기</a><br>
  </c:when>
</c:choose>
<hr />


<c:forEach items="${reviewList}" var="review">
<fieldset>
<h4><i class="far fa-id-badge"></i> ${review.member.id}><a href='review/detail?no=${review.no}'>${review.comment}</a></h4>
     <i class="far fa-thumbs-up"></i> ${review.score} / 
     <i class="far fa-calendar-alt"></i> ${review.registeredDate}
     <hr />
</fieldset>
</c:forEach>