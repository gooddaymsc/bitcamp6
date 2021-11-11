<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
tr a {
    text-decoration: none;
    color: black;
}
tr a:visited {
    color: black;
}
tr:hover {
    cursor: pointer;
}
</style>

<h1>상품 목록</h1>
<c:choose> 
  <c:when  test="${loginUser.authority eq 4}">
<a href='form' class="btn btn-outline-primary btn-sm">새상품</a>
  </c:when>
</c:choose>

<form action='search' method='post'>  
<div class="mb-3 row">
  <label for='f-search' class="col-sm-1 col-form-label">검색</label>
    <div class="col-sm-2">
    <input id='f-search' type='text' name='search' class="form-control">
  </div>
</div>
</form>
<table class="table table-hover">
<thead>
  <tr>
    <th>번호</th>
    <th>상품명</th>
    <th>평점</th>
    <th>주종</th>
    <th>상세주종</th>
    <th>원산지</th>
    <th>용량</th>
    <th>당도</th>
    <th>산도</th>
    <th>바디감</th>
    <th>도수</th>
  </tr>
</thead>
<tbody>
<!-- 검색 기능 구현해야함 -->
<c:forEach items="${productList}" var="product">
<tr>
    <td><a href='detail?no=${product.productNumber}'>${product.productNumber}</a></td>
    <td>${product.productName}</td> 
    <td>${product.rate}</td> 
    <td>${product.productType.type}</td> 
    <td>${product.productType.subType}</td> 
    <td>${product.countryOrigin}</td> 
    <td>${product.volume}</td> 
    <td>${product.sugarLevel}</td> 
    <td>${product.acidity}</td> 
    <td>${product.weight}</td> 
    <td>${product.alcoholLevel}</td> 
</tr>
</c:forEach>
</tbody>
</table>

<script>
document.querySelectorAll("tbody a").forEach((aTag) => {
  aTag.onclick = () => false;
});

var trList = document.querySelectorAll("tbody tr"); // 리턴 객체는 HTMLCollection 타입 객체이다.
trList.forEach(function(trTag) {
  trTag.onclick = (e) => {
    //console.log(e.currentTarget.querySelector("a").href);
    //e.currentTarget.querySelector("a").click();
    window.location.href = e.currentTarget.querySelector("a").href;
    //window.location.href = "detail?no=" + e.currentTarget.getAttribute("data-no");
  };
});
</script>








