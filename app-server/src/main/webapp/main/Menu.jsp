<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>


<style>
tr a {
    text-decoration: none;
    color: black;
    font-family: 'Cafe24Oneprettynight';
    text-align: center;
}
tr a:visited {
    color: black;
}
tr:hover {
    cursor: pointer;
}

h1{
  font-size: 25px; 
  font-weight: bold; 
  font-family: 'Cafe24Oneprettynight';
  text-align: center;
  margin-right:30px;
}

.b{
  margin-top:30px;
  
	font-size: 38px; 
	color:#3a3a3a;
	text-decoration: none;
	font-family: 'Praise', cursive;
	text-align: center;
}
.c{
	font-size: 23px; 
	font-weight: bold; 
	color:#3a3a3a;
	font-family: 'Cafe24Oneprettynight';
	text-align: center;
}

#next-icon{
	xmargin-left:300px;
}

#prev-icon{
	xmargin-right:500px;
}

#carousel-indicators{
	margin-top:400px;
}

#border{
  margin-left: 115px;
  margin-top: 30px;
}


</style>

</head>
<body>


<aside>
 <c:choose> 
  <c:when  test="${loginUser eq null}">
  <a href='./loginMenu' class="btn btn-primary">로그인</a>
  <a href='../buyer/form' class="btn btn-primary">회원가입</a><br>
  <a href='../board/list' class="btn btn-primary">게시판</a>
  <a href='../product/ranking' class="btn btn-primary">오늘의 술</a>
  <a href='../product/list' class="btn btn-primary">상품</a>
  </c:when>
  <c:otherwise>
  <p>아이디 : ${loginUser.id}</p>
  <br>
  <a href='logout' class="btn btn-primary">로그아웃</a>
  </c:otherwise>
</c:choose> 
<c:choose> 
  <c:when test="${loginUser.authority eq 2}">
      <a href='../board/list' class="btn btn-primary">게시판</a>
      <a href='../product/ranking' class="btn btn-primary">오늘의 술</a>
      <a href='../product/list' class="btn btn-primary">상품</a>
      <a href='../product/review/find' class="btn btn-primary">내가남긴리뷰</a>
      <a href='../cart/list' class="btn btn-primary" >장바구니</a>
      <a href='../booking/list' class="btn btn-primary">예약</a>
      <a href='../message/list' class="btn btn-primary">메세지</a>
      <a href='../buyer/detail?id=${loginUser.id}' class="btn btn-primary">개인정보변경</a>
  </c:when>
  <c:when test="${loginUser.authority eq 4}">
      <a href='../board/list' class="btn btn-primary">게시판</a>
      <a href='../product/ranking' class="btn btn-primary">오늘의 술</a>
      <a href='../product/list' class="btn btn-primary">상품</a>
      <a href='../stock/list?id=${loginUser.id}' class="btn btn-primary">재고</a>
      <a href='../message/list' class="btn btn-primary">메세지</a>
      <a href='../seller/detail?id=${loginUser.id}' class="btn btn-primary">개인정보변경</a>
  </c:when>
  <c:when test="${loginUser.authority eq 8}">
      <a href='../board/list' class="btn btn-primary">게시판</a>
      <a href='../product/list' class="btn btn-primary">상품</a>
      <a href='../buyer/list' class="btn btn-primary">회원(구매자)관리</a>
      <a href='../seller/list' class="btn btn-primary">회원(판매자)관리</a>
      <a href='../message/list' class="btn btn-primary">메세지</a>
  </c:when>
</c:choose> 
</aside>


<h1 class='b'> Top Lists </h1>
<div id="carouselExampleDark" class="carousel carousel-dark slide" data-bs-ride="carousel">
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="1" class="active" aria-label="Slide 2"></button>
    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="2" class="active" aria-label="Slide 3"></button>
    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="3" class="active" aria-label="Slide 4"></button>
    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="4" class="active" aria-label="Slide 5"></button> 
  </div>
  <div class="carousel-inner">
    <div class="carousel-item active" data-bs-interval="10000">
     <h1> 와인 </h1>
    <table id="border">
    <c:forEach items="${rankingWine}" var="product">
     <td>
       <div class="wine_ranking" style="width:12rem">
        <img align="middle" class="d-block w-100" src = "../image/${product.photo}.jpg" onError="this.src='../image/alcohol6.jpg'" 
        style="width:10rem; height:220px; padding:3px">
        <h5><a href="../product/detail?no=${product.productNumber}">${product.productName}</a></h5>
         <p class="c">${product.rate}</p>
         <div class="carousel-caption d-none d-md-block">
           </div>
     </div> 
    </td>
   </c:forEach>    
  </table> 
    </div>
    <div class="carousel-item" data-bs-interval="2000">
     <h1> 위스키 </h1>
     <table id="border" >
       <c:forEach items="${rankingWhiskey}" var="product">
     <td>
      <div class="wiskey_ranking" style="width: 12rem">
        <img align="middle" class = "d-block w-100" src = "../image/${product.photo}.jpg" onError="this.src='../image/alcohol5.jpg'" 
        style="width:10rem; height:220px; padding:3px">
        <h5><a href="../product/detail?no=${product.productNumber}">${product.productName}</a></h5>
        <p class="c"> ${product.rate}</p>
         <div class="carousel-caption d-none d-md-block">
      </div> 
     </div>  
    </td>
   </c:forEach>   
  </table>   
      </div>
       <div class="carousel-item" data-bs-interval="2000">
        <h1> 브랜디/꼬냑 </h1>
     <table id="border">
       <c:forEach items="${rankingBrandy}" var="product">
     <td>
      <div class="brandy_ranking" style="width: 12rem">
        <img align="middle" class = "d-block w-100" src = "../image/${product.photo}.jpg" onError="this.src='../image/alcohol7.jpg'" 
        style="width:10rem; height:220px; padding:3px">
        <h5><a href="../product/detail?no=${product.productNumber}">${product.productName}</a></h5>
        <p class="c"> ${product.rate}</p>
         <div class="carousel-caption">
      </div> 
     </div>  
    </td>
   </c:forEach>   
  </table>   
      </div>
       <div class="carousel-item" data-bs-interval="2000">
        <h1> 리큐르/보드카 </h1>
     <table id="border">
       <c:forEach items="${rankingVodka}" var="product">
     <td>
      <div class="vodka_ranking" style="width: 12rem">
        <img align="middle" class = "d-block w-100" src = "../image/ ${product.photo}.jpg" onError="this.src='../image/alcohol9.jpg'" 
        style="width:10rem; height:220px; padding:3px">
        <h5><a href="../product/detail?no=${product.productNumber}">${product.productName}</a></h5>
        <p class="c"> ${product.rate}</p>
         <div class="carousel-caption d-none d-md-block">
      </div> 
     </div>  
    </td>
   </c:forEach>   
  </table>   
      </div>
  <div class="carousel-item">
   <h1> 전통주 </h1>
     <table id="border">
       <c:forEach items="${rankingTrad}" var="product">
     <td>
      <div class="wiskey_ranking" style="width: 12rem">
        <img align="middle" class = "d-block w-100" src = "../image/${product.photo}.jpg" onError="this.src='../image/alcohol8.jpg'" 
        style="width:10rem; height:220px; padding:3px">
        <h5><a href="../product/detail?no=${product.productNumber}">${product.productName}</a></h5>
        <p class="c"> ${product.rate}</p>
         <div class="carousel-caption d-none d-md-block">
       </div> 
      </div>  
     </td>
    </c:forEach>   
  </table>   
      </div>
  </div>
  <br>
  <br>
  <button class="carousel-control-prev" id="prev-icon" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true" ></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" id="next-icon" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>

<script>
  function distplay(){    
	  if($('input:radio[id=btnradio1]').is(':checked')){
		  $('#wine').hide();
    } else{
  	  $('#wine').show();
 }
</script>
 
<script>
<!-- d.card div.card-->
document.querySelectorAll("table a").forEach((aTag) => {
  aTag.onclick = () => false;
});

var trList = document.querySelectorAll("table div"); // 리턴 객체는 HTMLCollection 타입 객체이다.
trList.forEach(function(trTag) {
  trTag.onclick = (e) => {
    //console.log(e.currentTarget.querySelector("a").href);
    //e.currentTarget.querySelector("a").click();
    window.location.href = e.currentTarget.querySelector("a").href;
    //window.location.href = "detail?no=" + e.currentTarget.getAttribute("data-no");
  };
});
</script>

</body> 
</html>