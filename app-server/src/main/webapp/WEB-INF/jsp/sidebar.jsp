<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
#aside{ 
  top:14em;
  width:10em;
  padding:20px;
  background-color: white;
  z-index: 11;
}
.side-col-sm-10 {
position: relative;
  xborder : 2px solid red;
  border-radius:50%; 
  xtop : 50px;
  left : 20px;
  xpadding-left : 40px;
  width : 70px;
  height: 70px;
}
.side-col-sm-10 img {
    position: absolute;
    border-radius:50%; 
    top: 0;
    left: 0;
    width: 70px;
    height: 70px;
    object-fit: fill;
}
.btn-group-vertical {
  xborder: 2px solid blue;
  position : relative;
  xtop : 20px;
}
.f-loginUserid {
  xborder: 2px solid black;
  top : 60px;
}
</style>


<aside id="aside" >
 <c:choose> 
  <c:when  test="${loginUser eq null}">
    <div class="btn-group-vertical">
	  <a href='${contextRoot}/drinker/app/main/loginForm' class="btn btn-light">로그인</a>
	  <a href='${contextRoot}/drinker/app/buyer/form' class="btn btn-light">회원가입</a>
	  <a href='${contextRoot}/drinker/app/board/list' class="btn btn-light">게시판</a>
	  <a href='${contextRoot}/drinker/app/product/ranking' class="btn btn-light">오늘의 술</a>
    </div>
  </c:when>
  <c:otherwise>
  <div class=side-col-sm-10>
     <img id="f-photo-image" src="${contextPath}/upload/member/${loginUser.photo}_100x100.jpg" onError="this.src='${contextPath}/upload/member/profile.png'">
   </div>
  <p class="f-loginUserid" style="font-weight: bold;">${loginUser.id}</p>
  <br>
  </c:otherwise>
</c:choose> 
<c:choose> 
  <c:when test="${loginUser.authority eq 2}">
  <div class="btn-group-vertical">
      <a href='${contextRoot}/drinker/app/main/myPage' class="btn btn-light">MyPage</a>
      <a href='${contextRoot}/drinker/app/board/list' class="btn btn-light">게시판</a>
      <a href='${contextRoot}/drinker/app/product/ranking' class="btn btn-light">오늘의 술</a>
      <a href='${contextRoot}/drinker/app/cart/list' class="btn btn-light" >장바구니</a>
      <a href='${contextRoot}/drinker/app/booking/list' class="btn btn-light">예약</a>
      <a href='${contextRoot}/drinker/app/message/list' class="btn btn-light">메세지</a>
      <a href='${contextRoot}/drinker/app/main/logout' class="btn btn-light" >로그아웃</a>
  </div>
  </c:when>
  <c:when test="${loginUser.authority eq 4}">
  <div class="btn-group-vertical">
      <a href='${contextRoot}/drinker/app/main/myPage' class="btn btn-light">MyPage</a>
      <a href='${contextRoot}/drinker/app/board/list' class="btn btn-light">게시판</a>
      <a href='${contextRoot}/drinker/app/product/ranking' class="btn btn-light">오늘의 술</a>
      <a href='${contextRoot}/drinker/app/product/form' class="btn btn-light">상품등록</a>
      <a href='${contextRoot}/drinker/app/stock/list?id=${loginUser.id}' class="btn btn-light">재고</a>
      <a href='${contextRoot}/drinker/app/booking/list' class="btn btn-light">예약</a>
      <a href='${contextRoot}/drinker/app/message/list' class="btn btn-light">메세지</a>
      <a href='${contextRoot}/drinker/app/main/logout' class="btn btn-light">로그아웃</a>
  </div>
  </c:when>
  <c:when test="${loginUser.authority eq 8}">
  <div class="btn-group-vertical">
      <a href='${contextRoot}/drinker/app/main/myPage' class="btn btn-light">관리자<br>페이지</a>
      <a href='${contextRoot}/drinker/app/board/list' class="btn btn-light">게시판</a>
      <a href='${contextRoot}/drinker/app/product/ranking' class="btn btn-light">오늘의 술</a>
      <a href='${contextRoot}/drinker/app/message/list' class="btn btn-light">메세지</a>
      <a href='${contextRoot}/drinker/app/main/logout' class="btn btn-light">로그아웃</a>
  </div>
  </c:when>
</c:choose> 
</aside>
