<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
td a {
    text-decoration: none;
    color: black;
}
td a:visited {
    color: black;
}
div.card:hover {
    cursor: pointer;
}
</style>
<h1> 오늘의 술 </h1>
 <table class="table table-hover">
    <c:forEach items="${productList}" var="product">
     <td>
      <div class="card" style="width: 15rem;">
        <img align="middle" class = "image" src = "../image/${product.photo}.jpg" onError="this.src='../image/logo.jpeg'" style="width:14rem; height:300px;">
        <a href="detail?no=${product.productNumber}" class="list-group-item">${product.productName}</a>
        <p class="card-text"> - 평점: ${product.rate}점</p>
        <p class="card-text"> - 주종: ${product.productType.type} </p>
        <p class="card-text"> - 도수: ${product.alcoholLevel}</p>
        <p class="card-text"> - 용량: ${product.volume} </p>
      </div> 
    </td>
    </c:forEach>
 </table>
 <script>
 <!--td.card a  td.card div.card-->
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


