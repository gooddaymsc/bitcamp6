<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
h2{
    position: absolute; 
    font-size: 30px; 
    font-family: 'Cafe24Oneprettynight';
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





</style>

<h2>게시판</h2>
<br>
<a href='form' id="board-btn" class="btn btn-outline-secondary btn-sm">글쓰기</a>
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








