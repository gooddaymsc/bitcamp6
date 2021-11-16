<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<style>
.col a {
    color: black;
}
.col a:visited {
    color: black;
    border-bottom: 3px solid #000;
}
.col a:hover {
    color: black;
    display: block;
    }
.col a:hover::after {
    color: black;
    display: block;
}
.col a::after {
    color: black;
    display: block;
}
.col:hover {
    cursor: pointer;
    background-color : #f5d6bb;
    xborder-bottom: 3px solid #000;
    
}
#tb1 {
  position : relative;
  display:;
  
}
#tb2 {
position:asbolute;
display:;
}
#tb3 {
display:;
}
.col {
  xposition: absolute;
  border: 2px solid #eebc90;
  xwidth:880px;
  xheight: 150px;
}
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
  <div class="row align-items-start">
    <div class="col">
   <a class="nav-link active" aria-current="page" href="javascript:view(1)">내 게시글</a>
     </div>
    <div class="col">
     <a class="nav-link" href="javascript:view(2)">내 댓글</a>
    </div>
    <div class="col">
    <a class="nav-link" href="javascript:view(3)">내 리뷰</a>
    </div>
      <c:choose> 
    <c:when test="${loginUser.authority eq 2}">
    <div class="col">
      <a class="nav-link" href="../buyer/detail?id=${loginUser.id}">개인정보</a>
    </div>
    </c:when >
    <c:when test="${loginUser.authority eq 4}">
    <div class="col">
      <a class="nav-link" href="../seller/detail?id=${loginUser.id}">개인정보</a>
    </div>
    </c:when >
    <c:when test="${loginUser.authority eq 2}">
    <div class="col">
      <a class="nav-link" href='../buyer/list' class="btn btn-light">회원(구매자)관리</a>
    </div>
    <div class="col">
      <a class="nav-link" href='../seller/list' class="btn btn-light">회원(판매자)관리</a>
    </div>
    </c:when>
    </c:choose>
  </div>
<br>
<table id='tb1' class="table table-hover">
<thead>
  <tr>
    <th>번호</th>
    <th>제목</th>
    <!-- <th>태그</th> -->
    <th>작성자</th>
    <th>등록일</th>
    <th>조회수</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${boardList}" var="board">
<tr>
    <td><a href='../board/show?no=${board.boardNumber}'>${board.boardNumber}</a></td>  
    <td>${board.title}</td> 
    <!-- <td>${board.boardTag.tag}</td>  -->
    <td>${board.writer.id}</td> 
    <td>${board.registrationDate}</td> 
    <td>${board.views}</td> 
</tr>
</c:forEach>
</tbody>
</table>

<table id='tb2' class="table table-hover">
<thead>
  <tr>
    <th>댓글번호</th>
    <th>게시글번호</th>
    <th>아이디</th>
    <th>내용</th>
    <th>등록일</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${commentList}" var="comment">
<tr>
    <td>${comment.commentNumber}</td>  
    <td><a href='../board/detail?no=${comment.boardNumber}'>${comment.boardNumber}</a></td> 
    <td>${comment.writer.id}</td> 
    <td>${comment.content}</td> 
    <td>${comment.registrationDate}</td> 
</tr>
</c:forEach>
</tbody>
</table>

<table id='tb3' class="table table-hover">
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
    <td><a href='../product/review/detail?no=${review.no}'>${review.reviewProduct}</a></td> 
    <td>${review.score}</td> 
    <td>${review.comment}</td> 
    <td>${review.registeredDate}</td> 
</tr>
</c:forEach>
</tbody>
</table>

<script>
function view(arg) {
var t1 = document.getElementById("tb1");
var t2 = document.getElementById("tb2");
var t3 = document.getElementById("tb3");

if(arg == 1) {
t1.style.display="";
t2.style.display="none";
t3.style.display="none";
} else if (arg==2) {
t1.style.display="none";
t2.style.display="";
t3.style.display="none";
} else {
	t1.style.display="none";
	t2.style.display="none";
	t3.style.display="";
}
}
</script>

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