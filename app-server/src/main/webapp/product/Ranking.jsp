<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<style>
@import url('https://fonts.googleapis.com/css2?family=Praise&display=swap');

#ranking_container{
  xborder:1px dashed red;
  margin-top: 30px;
}


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

#product_find{
font-size: 16px; 
    font-family: Cafe24Oneprettynight;
    padding: 10px;
    font-weight: bold; 
    color: #b62e29;
}




</style>


<h1> Ranking </h1>
<br>
<body>
 <table >
    <c:forEach items="${productList}" var="product">
     <td>
      <div id="ranking_container" style="width: 14rem;">
        <p class="rate" id="product_info">BEST1${product.rate}</p>
        <img align="middle" class = "image" src = "../image/${product.photo}.jpg" onError="this.src='../image/logo.jpeg'" style="width:13rem; height:270px;">
        <a href="detail?no=${product.productNumber}" class="productName">${product.productName}</a>
        <p class="type" id="product_info"> 주종: ${product.productType.type} </p>
        <p class="level" id="product_info"> 도수: ${product.alcoholLevel}%</p>
        <p class="volume" id="product_info"> 용량: ${product.volume}ml</p>
       <label id="product_find"> 주변 찾기 <i class="fas fa-search"></i></label>
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
</script>
