<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
  <a href='../main/loginMenu' class="btn btn-light">로그인</a>
  <a href='../buyer/form' class="btn btn-light">회원가입</a><br>
  <a href='../board/list' class="btn btn-light">게시판</a>
  <a href='../product/ranking' class="btn btn-light">오늘의 술</a>
  </c:when>
  <c:otherwise>
  <p class="id">${loginUser.id}님</p>
  <br>
  </c:otherwise>
</c:choose> 
<c:choose> 
  <c:when test="${loginUser.authority eq 2}">
  <div class="btn-group-vertical">
      <a href='../main/myPage' class="btn btn-light">MyPage</a>
      <a href='../board/list' class="btn btn-light">게시판</a>
      <a href='../product/ranking' class="btn btn-light">오늘의 술</a>
      <a href='../cart/list' class="btn btn-light" >장바구니</a>
      <a href='../booking/list' class="btn btn-light">예약</a>
      <a href='../message/list' class="btn btn-light">메세지</a>
      <a href='../main/logout' class="btn btn-light" >로그아웃</a>
  </div>
  </c:when>
  <c:when test="${loginUser.authority eq 4}">
  <div class="btn-group-vertical">
      <a href='../main/myPage' class="btn btn-light">MyPage</a>
      <a href='../board/list' class="btn btn-light">게시판</a>
      <a href='../product/ranking' class="btn btn-light">오늘의 술</a>
      <a href='../stock/list?id=${loginUser.id}' class="btn btn-light">재고</a>
      <a href='../booking/list' class="btn btn-light">예약</a>
      <a href='../message/list' class="btn btn-light">메세지</a>
      <a href='../main/logout' class="btn btn-light">로그아웃</a>
  </div>
  </c:when>
  <c:when test="${loginUser.authority eq 8}">
  <div class="btn-group-vertical">
      <a href='../main/myPage' class="btn btn-light">관리자페이지</a>
      <a href='../board/list' class="btn btn-light">게시판</a>
      <a href='../message/list' class="btn btn-light">메세지</a>
      <a href='../main/logout' class="btn btn-light">로그아웃</a>
  </div>
  </c:when>
</c:choose> 
</aside>
