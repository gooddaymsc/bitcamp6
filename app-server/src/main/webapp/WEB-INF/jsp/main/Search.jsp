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

<h1>검색 결과</h1>
<h5>상품</h5>
<table class="table table-hover">
<thead>
  <tr>
    <th>상품명</th>
    <th>평점</th>
    <th>주종</th>
    <th>용량</th>
    <th>도수</th>
  </tr>
</thead>
<tbody>
<!-- 검색 기능 구현해야함 -->
<c:forEach items="${productList}" var="product">
<tr>
    <td><a href='../product/detail?no=${product.productNumber}'>${product.productName}</a></td> 
    <td>${product.rate}</td> 
    <td>${product.productType.type}</td> 
    <td>${product.volume}</td> 
    <td>${product.alcoholLevel}</td> 
</tr>
</c:forEach>
</tbody>
</table>
<h5>판매점</h5>
<table class="table table-hover">
      <thead>
        <tr>
          <th>가게명</th>
          <th>가게주소</th>
          <th>가게번호</th>
        </tr>
      </thead>
      <tbody>

        <c:forEach items="${sellerList}" var="seller">
          <tr>
            <td><a href='../stock/detail?id=${seller.member.id}'>${seller.businessName}</a></td>
            <td>${seller.businessName}</td>
            
            <td>${seller.businessName}</td>
            
          </tr>
        </c:forEach>
      </tbody>
    </table>
<h5>게시판</h5>
<table class="table table-hover">
<thead>
  <tr>
    <th>제목</th>
    <th>태그</th>
    <th>내용</th>
    <th>등록일</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${boardList}" var="board">
<tr>
    <td><a href='../board/detail?no=${board.boardNumber}'>${board.title}</a></td> 
    <td>${board.boardTag.tag}</td> 
    <td>${board.content}</td> 
    <td>${board.registrationDate}</td> 
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








