<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<style>
  @import url('https://fonts.googleapis.com/css2?family=Praise&display=swap');

  #ranking_container {
    xborder: 1px dashed red;
    xmargin-top: 30px;
  }

  h1 {
    margin-top: 30px;
    text-decoration: none;
    color: black;
    font-family: 'Praise', cursive;
    text-align: center;
      font-family: Cafe24Oneprettynight;
  font-size: 23px; 
  font-weight: bold; 
  color: #fb8b12;
  }
  
  .ranking {
  text-align: center;
  width:150px;
  padding: 0;
/*   border: 2px solid blue;
 */  }  
 
    .productName {
  }
  
 .ranking a {
    font-family: Cafe24Oneprettynight;
    font-size: 18px; 
    font-weight: bold; 
  color: #fb8b12;
    text-decoration: none;
    text-align: center;
}
 .ranking a:visited {
    color: black;
}
 .ranking :hover {
    cursor: pointer;
}

  .col-sm {
    font-family: Cafe24Oneprettynight;
  font-size: 23px; 
  font-weight: bold; 
  color: #fb8b12;
  }
  
#product_info{
    font-size: 16px; 
    font-family: Cafe24Oneprettynight;
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
    padding: 10px;  
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
  padding: 5px; 
}

  
   .image {
  width:300px;
  height:auto;
      margin-bottom: 10px;
  
  }
  
</style>

<h1> Ranking </h1>
<body>
<br>
  <div class="ranking_container row">
    <c:forEach items="${productList}" var="product">
      <div class="ranking col-sm">
      <h2 id ="best_label">Best${product.productNumber}</h2>
        <img align="middle" class="image" src="../upload/product/${product.photo}_300x300.jpg">
        <p class="productName">
        <a href="show?no=${product.productNumber}">${product.productName}</a>
        </p>
        <p class="rate" id="product_rate">${product.rate}</p>
        <p class="type" id="product_info"> 주종: ${product.productType.type} </p>
        <p class="level" id="product_info"> 도수: ${product.alcoholLevel}%</p>
        <p class="volume" id="product_info"> 용량: ${product.volume}ml</p>
        <span role="button" id="product_find">판매처찾기 <i class="fas fa-search"></i></span>
      </div>
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
