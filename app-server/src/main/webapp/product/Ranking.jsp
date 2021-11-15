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

  /* 

h1 {
    margin-top:30px;
    text-decoration: none;
    color: black;
    font-family: 'Praise', cursive;
    text-align: center;
}

tr a {
    font-size: 18px; 
    font-weight: bold; 
    text-decoration: none;
    color: black;
    font-family: Cafe24Oneprettynight;
    text-align: center;
}
tr a:visited {
    color: black;
}
tr:hover {
    cursor: pointer;
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
    margin-left:55px;
}

#best_label{
  font-family: Cafe24Oneprettynight;
  font-size: 23px; 
  font-weight: bold; 
  color: #fb8b12;
  padding: 39px; 
  word-spacing:146px;
  margin-left:58px;
}
 */

  /*  수정중 */


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

 .ranking a {
    font-size: 18px; 
    font-weight: bold; 
    text-decoration: none;
    color: black;
    font-family: Cafe24Oneprettynight;
    text-align: center;
}
 .ranking a:visited {
    color: black;
}
 .ranking :hover {
    cursor: pointer;
}

  .col-sm {
    float:left;
  padding:0 60px;
    font-family: Cafe24Oneprettynight;
  font-size: 23px; 
  font-weight: bold; 
  color: #fb8b12;
  }
  .ranking_container {
  float:left;
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
    margin-left:55px;
}

#best_label{
  font-family: Cafe24Oneprettynight;
  font-size: 23px; 
  font-weight: bold; 
  color: #fb8b12;
  padding: 39px; 
  word-spacing:146px;
  margin-left:58px;
}
</style>

<h1> Ranking </h1>
<body>
<Br>
<label id="best_label">
    <div class="best_container">
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
      </div>
    </div>
</label>
    <c:forEach items="${productList}" var="product">
      <div class="ranking" style="width: 13rem;">
        <img align="middle" class="image" src="../upload/product/${product.photo}_300x300.jpg"
          onError="this.src='../image/logo.jpeg'" style="width:13rem; height:270px;">
        <a href="show?no=${product.productNumber}" class="productName">${product.productName}</a>
        <p class="rate" id="product_rate">${product.rate}</p>
        <p class="type" id="product_info"> 주종: ${product.productType.type} </p>
        <p class="level" id="product_info"> 도수: ${product.alcoholLevel}%</p>
        <p class="volume" id="product_info"> 용량: ${product.volume}ml</p>
        <span role="button" id="product_find">판매처찾기 <i class="fas fa-search"></i></span>
      </div>
    </c:forEach>


<script>
  document.querySelectorAll("div a").forEach((aTag) => {
    aTag.onclick = () => false;
  });

  var trList = document.querySelectorAll("td div"); // 리턴 객체는 HTMLCollection 타입 객체이다.
  trList.forEach(function (trTag) {
    trTag.onclick = (e) => {
      //console.log(e.currentTarget.querySelector("a").href);
      //e.currentTarget.querySelector("a").click();
      window.location.href = e.currentTarget.querySelector("a").href;
      //window.location.href = "detail?no=" + e.currentTarget.getAttribute("data-no");
    };
  });
</script>
</body>
<%-- 
<h1> Ranking </h1>
<Br>
<body>
<label id="best_label">
 Best1 Best2 Best3 Best4 Best5 
</label>
 <table>
    <c:forEach items="${productList}" var="product">
     <td>
      <div id="ranking_container" style="width: 13rem;">
        <img align="middle" class = "image" src = "../upload/product/${product.photo}_300x300.jpg" onError="this.src='../image/logo.jpeg'" style="width:13rem; height:270px;">
        <a href="show?no=${product.productNumber}" class="productName">${product.productName}</a>
        <p class="rate" id="product_rate">${product.rate}</p>
        <p class="type" id="product_info">  주종: ${product.productType.type} </p>
        <p class="level" id="product_info"> 도수: ${product.alcoholLevel}%</p>
        <p class="volume" id="product_info"> 용량: ${product.volume}ml</p>
       <span role="button" id="product_find">판매처찾기 <i class="fas fa-search"></i></span>
      </div>
    </td>
    </c:forEach>
 </table>
</body> 

<script>
document.querySelectorAll("div a").forEach((aTag) => {
  aTag.onclick = () => false;
});

var trList = document.querySelectorAll("td div"); // 리턴 객체는 HTMLCollection 타입 객체이다.
trList.forEach(function(trTag) {
  trTag.onclick = (e) => {
    //console.log(e.currentTarget.querySelector("a").href);
    //e.currentTarget.querySelector("a").click();
    window.location.href = e.currentTarget.querySelector("a").href;
    //window.location.href = "detail?no=" + e.currentTarget.getAttribute("data-no");
  };
});
</script> --%>