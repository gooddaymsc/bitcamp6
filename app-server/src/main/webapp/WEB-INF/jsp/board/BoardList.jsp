 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>

/* .btn-group{
  margin-left: 35%;
  width: 400px;
  height: 60px;
  text-align: center;
}

#btntext1{
  margin-top: 10px;
  font-size: 20px; 
  text-align: center;
}

#btntext2{
  margin-top: 10px;
  font-size: 20px; 
  text-align: center;
} */

#boardFind{
  margin-top: 2%;
  margin-left: 32%;
  width: 500px;
  height: 46px;
  z-index: 10;
}


.input-group-append{
  border: black;
  text-align: center;
   z-index: -1;
}

#board-btn{
    float: right;    
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

.pagination_btn{
  margin-top: 5%;
  margin-left: 42%; 
}


</style>


<div class="html-board">
<br>
<h2>게시판</h2>
<h7>자유게시판</h7>
</div>

<!-- <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
  <input type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" checked>
  <label class="btn btn-outline-secondary" for="btnradio1"  id="btntext1">자유게시판</label>

  <input type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off">
  <label class="btn btn-outline-secondary" for="btnradio2" id="btntext2">공지사항</label>
</div> -->


 <fieldset class="input-group-append">
  <form action='search' method='post'>
    <input type="text" id="boardFind" class="form-control rounded-pill" name="boardFind" title="검색 키워드를 입력해주세요." placeholder="검색 키워드를 입력해주세요." value="">
 </form>
</fieldset>
   

<br>
<table class="table table-hover">
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
    <td><a href='show?no=${board.boardNumber}'>${board.boardNumber}</a></td>  
    <td>${board.title}</td> 
    <!-- <td>${board.boardTag.tag}</td>  -->
    <td>${board.writer.id}</td> 
    <td>${board.registrationDate}</td> 
    <td>${board.views}</td> 
</tr>
</c:forEach>
</tbody>
</table>

<a href='#' id="board-btn" onclick="btn_add(${loginUser.authority})" class="btn btn-outline-secondary btn-sm">글쓰기</a>

<div class="pagination_btn">
<nav aria-label="...">
		<ul class= "pagination">
		   <li class="page-item disabled">
		    <span class="page-link">이전</span>
		        </li>
		   <li class="page-item"><a class="page-link" href="#">1</a></li>
    <li class="page-item active" aria-current="page">
      <span class="page-link">2</span>
    </li>
    <li class="page-item"><a class="page-link" href="#">3</a></li>
    <li class="page-item">
      <a class="page-link" href="#">다음</a>
    </li>
  </ul>
</nav>
</div>

<script>
function btn_add(authority) {
	if ((authority==2) || (authority==4)||(authority==8)) {
    location.href="${contextRoot}/drinker/app/board/form";
	} else {
		  if (confirm("로그인 후 가능합니다.")==true) {
			  location.href="${contextRoot}/drinker/app/main/loginForm";
		  } else {
		    /* location.href="${contextRoot}/drinker/app/board/list" */
		    return false;
		  }
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








