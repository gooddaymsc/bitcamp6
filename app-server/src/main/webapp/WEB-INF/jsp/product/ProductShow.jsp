<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    
<style>/* 

/* @font-face {
  font-family: "NotoSansKR";
  src: url("../fonts/notoSansKR/NotoSans-Bold.woff") format("woff");
  font-style: normal;
}

@font-face {
  font-family: "NotoSansKR";
  src: url("../fonts/openSans/OpenSans-SemiBold.woff") format("woff");
  unicode-range: U+0020-007E;
  font-style: normal;
} */



#product_top_detail{
 float:right;
 margin-right: 50%;
 font-family: 'Praise', cursive;
}

#product_bottom_detail{
font-size: 17px; 
margin-top:10%;
clear:both;
}


#product_title{
font-size: 27px; 
font-weight: bold; 
}

#product_type{
font-size: 22px; 
color: #777777;
}


#product_detail2{
position: absolute; 
font-size: 20px; 
}

#product_detail_label{
font-weight: bold; 
font-size: 19px; 
color: #777777;
}

a {
    text-decoration: none;
    color: black;
}
<<<<<<< HEAD
=======
button {
  position: relative; 
}
>>>>>>> 41feeb9e26b7d2a1a42fe2913063f1bb12033468

h2 
.star-rating {width:205px;}
.star-rating,.star-rating span {display:inline-block; height:39px; overflow:hidden; background:url(../image/icon/star.png)no-repeat; }
.star-rating span{background-position:left bottom; line-height:0; vertical-align:top; }

f-rate{
font-size: 30px; 
display:inline-block;
}

#find-product { 
    float:right;
    margin-right: 25%;
    margin-top: 350px;
    width: 130px;
    height: 42px; 
    padding: 7px; 
    border: none; 
    border-radius: 4px; 
    color: white; 
    font-weight: bold;
    background-color: #3a3a3a; 
    cursor: pointer; 
    outline: none; 
  } 


</style>

<img id="f-photo-image" src="../../upload/product/${product.photo}_1000x1000.jpg" 
        align="left" width="300px" height="500px">
<form action='update' method='post' enctype="multipart/form-data">
    <input type='hidden' id='f-productNumber' type='text' name='productNumber' class="form-control" value='${product.productNumber}' readonly>
    
<div id="product_top_detail" >        
 <div id="product_type">
  <div id='f-name'> ${product.productType.type} > ${product.productType.subType} </div>
 </div>
  <br>
  <div id="product_title">
    <div id='f-name'> ${product.productName} </div>
  </div>
 
  <div id="product_detail2">
<div class="wrap-star">
    <div class='star-rating'>
        <span style="width:40%"></span>
    </div>
</div>

<div id='f-rat2' onchange="isSame()" > &nbsp;&nbsp;<span id="same"></span></div>

   <div id='f-sugarLevel'> 당도> ${product.sugarLevel}</div>
   <div id='f-acidity'> 산도> ${product.acidity}</div>
   <div id='f-weight'> 바디감> ${product.weight}</div>
  </div>  
</div>
<br>
<br>
<br>
<button class="btn btn-primary" id="find-product">상품찾아보기</button><br>

<div id="product_bottom_detail" > 
<br/>
<hr/>
<label id="product_detail_label">상품상세정보</label>
<br>
<label for='f-countryOrigin' class="col-sm-2 col-form-label">원산지 : ${product.countryOrigin}</label><br>
<c:choose> 
  <c:when test="${product.productType.type eq '와인'}">
      <label for='f-variety' class="col-sm-2 col-form-label">품종 : ${product.variety}</label><br>
  </c:when>
</c:choose>

 <label for='f-volume' class="col-sm-2 col-form-label">용량 : ${product.volume}</label><br>
 <label for='f-alcoholLevel' class="col-sm-2 col-form-label">도수 : ${product.alcoholLevel}</label><br>
</div>


<%-- <button type="button" onclick="location.href='listType?type=${product.productType.type}'" class="btn btn-outline-success">목록</button> --%>
<button type="button" onclick="location.href='list'" class="btn btn-outline-secondary btn-sm">목록</button>

<c:choose> 
  <c:when test="${loginUser.authority eq 8}">
  <div>
    <button type="button" onclick="location.href='detail?no=${product.productNumber}'" class="btn btn-outline-secondary btn-sm">상품정보수정</button>
  </div>
  </c:when>
  <c:when test="${loginUser.authority eq 4}">
  <div>
    <a href='#' onclick="btn_add('${product.productNumber}')" class="btn btn-outline-secondary btn-sm">재고등록</a>
  
<%--     <button type="button" onclick="location.href='../stock/form?no=${product.productNumber}'" class="btn btn-outline-secondary btn-sm">재고등록</button>
 --%>    <button type="button" onclick="location.href='detail?no=${product.productNumber}'" class="btn btn-outline-secondary btn-sm">상품정보수정</button>
  </div>
  </c:when>
  <c:when test="${loginUser.authority eq 2}">
  <div>
    <button type="button" onclick="location.href='../stock/sellerList?no=${product.productNumber}'" class="btn btn-outline-secondary btn-sm">장바구니등록</button>
  </div>
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
<hr/>


<c:forEach items="${reviewList}" var="review">
<fieldset>
<h4><i class="far fa-id-badge"></i> ${review.member.id}><a href='review/detail?no=${review.no}'>${review.comment}</a></h4>
     <i class="far fa-thumbs-up"></i> ${review.score} / 
     <i class="far fa-calendar-alt"></i> ${review.registeredDate}
     <hr />
</fieldset>
</c:forEach>

<script>
function isSame(){
var rate=(${product.rate}/5)*100;
document.ex_form.target_name.value = "100";
return rate;
}
</script>
<script>
function btn_add(no) {
 var no = document.getElementById('f-number').value;
 const board_id = document.getElementById('btn-writer').value;
   if (id==board_id) {
      location.href="../stock/form?no="+no;
   } else {
     alert("작성자가 아니므로 수정할 수 없습니다.");
     return false;
   }
 }
</script>
