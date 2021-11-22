<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<style>
  @import url('https://fonts.googleapis.com/css2?family=Praise&display=swap');

.ranking_title {
  margin-top: 160px;
   color: black;
   font-family: 'Praise', cursive;
   text-align: center;
  font-size: 23px; 
  font-weight: bold; 
}

	.ranking_image{
	width:50px;
	height:50px;
	}

  #ranking_container {
    xborder: 1px dashed red;
    xmargin-top: 30px;
  }

.ranking_detail { 
  margin-left: 170px;
  border: 2px solid gray;
  color: white; 
  background-color: black; 
  
  }
  
 .ranking_detail2 {
  font-size: 20px; 
  text-align: center;
  width:150px;
  padding: 0;
  float: right;
  margin-right: 600px;
   }  

 .ranking_detail a:visited {
    color: black;
    float:right;
}
 .ranking_detail :hover {
    cursor: pointer;
}

  
#product_info{
    font-size: 16px; 
    text-align: center;
}

#product_rate{
    font-size: 25px; 
    font-family: Cafe24Oneprettynight;
    text-align: center;
}

#product_find{
    font-size: 14px; 
    font-family: Cafe24Oneprettynight;
    color: white; 
    background-color: black; 
    cursor: pointer; 
    outline: none; 
    border-radius: 20px; 
}

#best_label{
  font-family: Cafe24Oneprettynight;
  font-size: 23px; 
  font-weight: bold; 
  color: #fb8b12;
} 


 .image {
width:250px;
height:auto;
margin-bottom: 10px;

}
.best {
float:left;
text-align:center;
}
</style>

<div class="ranking_title">
<h1> Ranking<img class="ranking_image" src="../../image/icons/ranking.png"></h1>
</div>

<body>
<br>
<!--   <div class="best row">
	  <div class="col-sm">
	  Best1
	  </div>
	  <div class="col-sm">
	  Best2
	  </div>
	  <div class="col-sm">
	  Best3
	  </div>
	  <div class="col-sm">
	  Best4
	  </div>
	  <div class="col-sm">
	  Best5
	  </div> -->

  <div class="ranking_container">
    <c:forEach items="${productList}" var="product">
      <div class="ranking_detail">
        <img align="middle" class="image" src="../../upload/product/${product.photo}_300x300.jpg">
        <div class="ranking_detail2">
        <p class="productName"> <a href="show?no=${product.productNumber}">${product.productName}</a> </p>
        <p class="rate" id="product_rate">${product.rate}</p>
        <p class="type" id="product_info"> 주종: ${product.productType.type} </p>
          <p class="type" id="product_info"> 상세주종: ${product.productType.subType} </p>
        <p class="level" id="product_info"> 도수: ${product.alcoholLevel}%</p>
        <p class="volume" id="product_info"> 용량: ${product.volume}ml</p>
        </div>
        <br>
        <br>
      </div>
     </c:forEach>   
     
 <%--      <c:forEach items="${reviewList}"var="review">
      <fieldset>
  <h4><i class="far fa-id-badge"></i> ${review.member.id}><a href='review/detail?no=${review.no}'>${review.comment}</a></h4>
     <i class="far fa-thumbs-up"></i> ${review.score} 
     <hr />
     </fieldset>
   </c:forEach>  --%>
     
      </div>

<script>
  document.querySelectorAll(".productName").forEach((aTag) => {
    aTag.onclick = () => false;
  });

  var trList = document.querySelectorAll(".ranking");
  trList.forEach(function (trTag) {
    trTag.onclick = (e) => {
      window.location.href = e.currentTarget.querySelector("a").href;
    };
  });
</script>

<script>
var rvList = document.querySelectorAll(".review);


</script>
</body>
