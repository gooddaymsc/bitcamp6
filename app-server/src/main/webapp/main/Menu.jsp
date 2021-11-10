<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 
<c:choose> 
  <c:when  test="${loginUser eq null}">
  <a href='./loginMenu' class="btn btn-primary">로그인</a>
  <a href='../buyer/form' class="btn btn-primary">회원가입</a><br>
  <a href='../board/list' class="btn btn-primary">게시판</a>
  <a href='../product/ranking' class="btn btn-primary">오늘의 술</a>
  <a href='../product/list' class="btn btn-primary">상품</a>
  </c:when>
  <c:otherwise>
  <p>아이디 : ${loginUser.id}</p>
  <br>
  <a href='logout' class="btn btn-primary">로그아웃</a>
  </c:otherwise>
</c:choose> 

<c:choose> 
  <c:when test="${loginUser.authority eq 2}">
      <a href='../board/list' class="btn btn-primary">게시판</a>
      <a href='../product/ranking' class="btn btn-primary">오늘의 술</a>
      <a href='../product/list' class="btn btn-primary">상품</a>
      <a href='../product/review/find' class="btn btn-primary">내가남긴리뷰</a>
      <a href='../cart/list' class="btn btn-primary" >장바구니</a>
      <a href='../booking/list' class="btn btn-primary">예약</a>
      <a href='../message/list' class="btn btn-primary">메세지</a>
      <a href='../buyer/detail?id=${loginUser.id}' class="btn btn-primary">개인정보변경</a>
  </c:when>
  <c:when test="${loginUser.authority eq 4}">
      <a href='../board/list' class="btn btn-primary">게시판</a>
      <a href='../product/ranking' class="btn btn-primary">오늘의 술</a>
      <a href='../product/list' class="btn btn-primary">상품</a>
      <a href='../stock/list?id=${loginUser.id}' class="btn btn-primary">재고</a>
      <a href='../message/list' class="btn btn-primary">메세지</a>
      <a href='../seller/detail?id=${loginUser.id}' class="btn btn-primary">개인정보변경</a>
  </c:when>
  <c:when test="${loginUser.authority eq 8}">
      <a href='../board/list' class="btn btn-primary">게시판</a>
      <a href='../product/list' class="btn btn-primary">상품</a>
      <a href='../buyer/list' class="btn btn-primary">회원(구매자)관리</a>
      <a href='../seller/list' class="btn btn-primary">회원(판매자)관리</a>
      <a href='../message/list' class="btn btn-primary">메세지</a>
  </c:when>
</c:choose> 
<h1> 와인 </h1>
 <table border=1>
    <c:forEach items="${rankingWine}" var="product">
     <td>
      <div class="card" style="width: 15rem;">
        <img align="middle" class = "image" src = "../image/${product.photo}.jpg" onError="this.src='../image/logo.jpeg'" style="width:14rem; height:300px;">
        <a href="../product/detail?no=${product.productNumber}" class="list-group-item">${product.productName}</a>
        <p class="card-text"> - 평점: ${product.rate}점</p>
        <p class="card-text"> - 주종: ${product.productType.type} </p>
        <p class="card-text"> - 도수: ${product.alcoholLevel}</p>
        <p class="card-text"> - 용량: ${product.volume} </p>
      </div> 
    </td>
    </c:forEach>
 </table>
 <h1> 위스키 </h1>
 <table border=1>
    <c:forEach items="${rankingWhiskey}" var="product">
     <td>
      <div class="card" style="width: 15rem;">
        <img align="middle" class = "image" src = "../image/${product.photo}.jpg" onError="this.src='../image/logo.jpeg'" style="width:14rem; height:300px;">
        <a href="../product/detail?no=${product.productNumber}" class="list-group-item">${product.productName}</a>
        <p class="card-text"> - 평점: ${product.rate}점</p>
        <p class="card-text"> - 주종: ${product.productType.type} </p>
        <p class="card-text"> - 도수: ${product.alcoholLevel}</p>
        <p class="card-text"> - 용량: ${product.volume} </p>
      </div> 
    </td>
    </c:forEach>
 </table>
 <h1> 브랜디 </h1>
 <table border=1>
    <c:forEach items="${rankingBrandy}" var="product">
     <td>
      <div class="card" style="width: 15rem;">
        <img align="middle" class = "image" src = "../image/${product.photo}.jpg" onError="this.src='../image/logo.jpeg'" style="width:14rem; height:300px;">
        <a href="../product/detail?no=${product.productNumber}" class="list-group-item">${product.productName}</a>
        <p class="card-text"> - 평점: ${product.rate}점</p>
        <p class="card-text"> - 주종: ${product.productType.type} </p>
        <p class="card-text"> - 도수: ${product.alcoholLevel}</p>
        <p class="card-text"> - 용량: ${product.volume} </p>
      </div> 
    </td>
    </c:forEach>
 </table>
 <h1> 보드카 </h1>
 <table border=1>
    <c:forEach items="${rankingVodka}" var="product">
     <td>
      <div class="card" style="width: 15rem;">
        <img align="middle" class = "image" src = "../image/${product.photo}.jpg" onError="this.src='../image/logo.jpeg'" style="width:14rem; height:300px;">
        <a href="../product/detail?no=${product.productNumber}" class="list-group-item">${product.productName}</a>
        <p class="card-text"> - 평점: ${product.rate}점</p>
        <p class="card-text"> - 주종: ${product.productType.type} </p>
        <p class="card-text"> - 도수: ${product.alcoholLevel}</p>
        <p class="card-text"> - 용량: ${product.volume} </p>
      </div> 
    </td>
    </c:forEach>
 </table>
 <h1> 전통주 </h1>
 <table border=1>
    <c:forEach items="${rankingTrad}" var="product">
     <td>
      <div class="card" style="width: 15rem;">
        <img align="middle" class = "image" src = "../image/${product.photo}.jpg" onError="this.src='../image/logo.jpeg'" style="width:14rem; height:300px;">
        <a href="../product/detail?no=${product.productNumber}" class="list-group-item">${product.productName}</a>
        <p class="card-text"> - 평점: ${product.rate}점</p>
        <p class="card-text"> - 주종: ${product.productType.type} </p>
        <p class="card-text"> - 도수: ${product.alcoholLevel}</p>
        <p class="card-text"> - 용량: ${product.volume} </p>
      </div> 
    </td>
    </c:forEach>
 </table>
 <script>
document.querySelectorAll("table a").forEach((aTag) => {
  aTag.onclick = () => false;
});

var trList = document.querySelectorAll("table tr"); // 리턴 객체는 HTMLCollection 타입 객체이다.
trList.forEach(function(trTag) {
  trTag.onclick = (e) => {
    //console.log(e.currentTarget.querySelector("a").href);
    //e.currentTarget.querySelector("a").click();
    window.location.href = e.currentTarget.querySelector("a").href;
    //window.location.href = "detail?no=" + e.currentTarget.getAttribute("data-no");
  };
});
</script>
