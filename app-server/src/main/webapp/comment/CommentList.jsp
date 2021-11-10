<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
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

<h1>댓글 목록(MVC + EL + JSTL)</h1>
<!-- <a href='form'>이전</a><br> -->
<a href='form' class="btn btn-outline-primary btn-sm">댓글 작성</a><br>
<table class="table table-hover">
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
    <td><a href='../../board/detail?no=${comment.boardNumber}'>${comment.boardNumber}</a></td> 
    <td>${comment.writer.id}</td> 
    <td>${comment.content}</td> 
    <td>${comment.registrationDate}</td> 
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

