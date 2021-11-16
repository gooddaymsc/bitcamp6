<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
#aside{ 
  z-index:5;
  top:14em;
  width:10em;
  padding:20px;
  background-color: white;
}
</style>


<aside id="aside" >
 <c:choose> 
  <c:when  test="${loginUser eq null}">
  <a href='${contextRoot}/drinker/main/loginMenu' class="btn btn-light">로그인</a>
  <a href='${contextRoot}/drinker/buyer/form' class="btn btn-light">회원가입</a><br>
  <a href='${contextRoot}/drinker/board/list' class="btn btn-light">게시판</a>
  <a href='${contextRoot}/drinker/product/ranking' class="btn btn-light">오늘의 술</a>
  <a href='${contextRoot}/drinker/product/list' class="btn btn-light">상품</a>
  </c:when>
  <c:otherwise>
  <p class="id">${loginUser.id}님</p>
  <br>
  
  </c:otherwise>
</c:choose> 
<c:choose> 
  <c:when test="${loginUser.authority eq 2}">
  <div class="btn-group-vertical">
      <a href='${contextRoot}/drinker/main/logout' class="btn btn-light" >로그아웃</a>
      <a href='${contextRoot}/drinker/board/list' class="btn btn-light">게시판</a>
      <a href='${contextRoot}/drinker/product/ranking' class="btn btn-light">오늘의 술</a>
      <a href='${contextRoot}/drinker/product/list' class="btn btn-light">상품</a>
      <a href='${contextRoot}/drinker/product/review/find' class="btn btn-light">내가남긴<br>리뷰</a>
      <a href='${contextRoot}/drinker/cart/list' class="btn btn-light" >장바구니</a>
      <a href='${contextRoot}/drinker/booking/list' class="btn btn-light">예약</a>
      <a href='${contextRoot}/drinker/message/list' class="btn btn-light">메세지</a>
      <a href='${contextRoot}/drinker/buyer/detail?id=${loginUser.id}' class="btn btn-light">개인정보<br>변경</a>
  </div>
  </c:when>
  <c:when test="${loginUser.authority eq 4}">
  <div class="btn-group-vertical">
      <a href='${contextRoot}/drinker/main/logout' class="btn btn-light">로그아웃</a>
      <a href='${contextRoot}/drinker/board/list' class="btn btn-light">게시판</a>
      <a href='${contextRoot}/drinker/product/ranking' class="btn btn-light">오늘의 술</a>
      <a href='${contextRoot}/drinker/product/list' class="btn btn-light">상품</a>
      <a href='${contextRoot}/drinker/stock/list?id=${loginUser.id}' class="btn btn-light">재고</a>
      <a href='${contextRoot}/drinker/message/list' class="btn btn-light">메세지</a>
      <a href='${contextRoot}/drinker/seller/detail?id=${loginUser.id}' class="btn btn-light">개인정보<br>변경</a>
  </div>
  </c:when>
  <c:when test="${loginUser.authority eq 8}">
  <div class="btn-group-vertical">
      <a href='${contextRoot}/drinker/main/logout' class="btn btn-light">로그아웃</a>
      <a href='${contextRoot}/drinker/board/list' class="btn btn-light">게시판</a>
      <a href='${contextRoot}/drinker/product/ranking' class="btn btn-light">오늘의 술</a>
      <a href='${contextRoot}/drinker/product/list' class="btn btn-light">상품</a>
      <a href='${contextRoot}/drinker/buyer/list' class="btn btn-light">회원(구매자)<br>관리</a>
      <a href='${contextRoot}/drinker/seller/list' class="btn btn-light">회원(판매자)<br>관리</a>
      <a href='${contextRoot}/drinker/message/list' class="btn btn-light">메세지</a>
  </div>
  </c:when>
</c:choose> 
</aside>
