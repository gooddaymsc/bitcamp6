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
  font-weight: bold; 
}
.ranking_title h1{
  font-size: 52px; 
}

	.ranking_image{
	width:50px;
	height:50px;
	}

.ranking_detail { 
  border: 1px solid white;
  border-radius: 50px; 
  color: #454444; 
  background-color: white;
  height: 270px;
  width:89%;
  box-shadow: 0px 0px 5px 8px #e0a3a3; 
  }
  
 .ranking-num{
  color: black; 
  font-family: 'Praise', cursive;
 
 }
 
 .ranking_detail2 {
  font-size: 20px; 
  text-align: center;
  padding: 0;
  float: right;
  margin-right: 400px;
  margin-top:25px;
  }  

.ranking_detail .image{
  margin-top:10px;
  margin-left:70px;
}

 .ranking_detail2 a:visited {
    color: white;
    float:right;
}
 .ranking_detail2 a:hover {
    cursor: pointer;
}

  
#product_info{
    font-size: 16px; 
    text-align: center;
}

#product_rate{
    font-size: 28px; 
    text-align: center;
}

#product_find{
    font-size: 14px; 
    color: white; 
    background-color: black; 
    cursor: pointer; 
    outline: none; 
}

#best_label{
  font-family: Cafe24Oneprettynight;
  font-size: 23px; 
  font-weight: bold; 
  color: #fb8b12;
} 

 #product_rate img {
   width: 20px;
   height: auto;
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
<!-- <div class="ranking-num">
	  <h3 class="col-sm1">
	  Best1
	  </h3>
	  <h3 class="col-sm">
	  Best2
	  </h3>
	  <h3 class="col-sm">
	  Best3
	  </h3>
	  <h3 class="col-sm">
	  Best4
	  </h3>
	  <h3 class="col-sm">
	  Best5
	  </h3> 
	</div>
 -->
  <div class="ranking_container">
    <c:forEach items="${productList}" var="product">
      <div class="ranking_detail">
        <img align="middle" class="image" src="../../upload/product/${product.photo}_300x300.jpg">
        <div class="ranking_detail2">
        <p class="productName" id="product_name"> <a href="show?no= ${product.productNumber}">${product.productName}</a> </p>
        <p class="rate" id="product_rate"> <img src="../../image/favourite.png">${product.rate}</p>
        <p class="type" id="product_info"> ${product.productType.type} > ${product.productType.subType} </p>
        <p class="level" id="product_info"> 도수: ${product.alcoholLevel}%</p>
        <p class="volume" id="product_info"> 용량: ${product.volume}ml</p>
        </div>
        <br>
        <br>
      </div>
      <br>
      <br>
     </c:forEach> 
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
</body>
