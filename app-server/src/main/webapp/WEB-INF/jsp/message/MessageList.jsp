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

<h1>메세지 목록</h1>
 <a href='form' class="btn btn-outline-secondary btn-sm">새 채팅</a>
 
<table class="table table-hover">
<thead>
  <tr>
    <th>채팅상대</th>
    <th>내용</th>
    <th>등록일</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${messages}" var="message">
<tr>
    <td style="display:none"><a href='detail?no=${message.roomNumber}' >${message.roomNumber}</a></td>  
    <td>${message.theOtherId} <i class="fas fa-arrow-right"></i> ${message.Id}</td> 
    <td>${message.content}</td>
    <td>${message.registrationDate}</td> 
    <td onclick="event.cancelBubble=true">
     <button type="button" onclick="location.href='delete?no=${message.roomNumber}'" class="btn btn-outline-secondary btn-sm">나가기</button>  
    </td>
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

<script>
$('.follower').click(function(){
    $('#followModal').modal();   //id가 "followModal"인 모달창을 열어준다. 
    $('.modal-title').text("팔로우");    //modal 의 header 부분에 "팔로우"라는 값을 넣어준다. 
});
</script>
