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

<h1>내가남긴리뷰목록</h1>

<table class="table table-hover">
<thead>
  <tr>
    <th>상품명</th>
    <th>평점</th>
    <th>한줄평</th>
    <th>등록일</th>
  </tr>
</thead>
<tbody>
<c:forEach items="${reviewList}" var="review">
<tr>
    <td><a href='detail?no=${review.no}'>${review.reviewProduct}</a></td> 
    <td>${review.score}</td> 
    <td>${review.comment}</td> 
    <td>${review.registeredDate}</td> 
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
