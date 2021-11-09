<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>게시판</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" href="../css/common.css"> 
  
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>
.container {
    xborder: 1px solid red;
    width: 640px;
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
</head>
<body>
<div class="container">
<jsp:include page="../header.jsp"></jsp:include>
<jsp:include page="../sidebar.jsp"></jsp:include>

<div id="content">
<h1>게시글 목록</h1>
<!-- <a href='form'>이전</a><br> -->
<a href='form' class="btn btn-outline-primary btn-sm">게시글 작성</a>
<a href='../main/Menu.jsp' class="btn btn-outline-primary btn-sm">메인</a><br><br>
<table class="table table-hover">
<thead>
  <tr>
    <th>번호</th>
    <th>제목</th>
    <th>태그</th>
    <th>내용</th>
    <th>작성자</th>
    <th>조회수</th>
    <th>등록일</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${boardList}" var="board">
<tr>
    <td><a href='detail?no=${board.boardNumber}'>${board.boardNumber}</a></td>  
    <td>${board.title}</td> 
    <td>${board.boardTag.tag}</td> 
    <td>${board.content}</td> 
    <td>${board.writer.id}</td> 
    <td>${board.views}</td> 
    <td>${board.registrationDate}</td> 
</tr>
</c:forEach>

</tbody>
</table>
</div><!-- .content -->
<footer>
<a href='../main/logout' class="btn btn-primary">로그아웃</a>
</footer>
<jsp:include page="../footer.jsp"></jsp:include>
</div><!-- .container -->

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

</body>
</html>








