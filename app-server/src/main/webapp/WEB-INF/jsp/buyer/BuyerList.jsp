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

    <h1>회원 목록(구매자)</h1>    
 
<a href='../main/myPage' class="btn btn-outline-secondary btn-sm">이전</a>
    <table class="table table-hover">
      <thead>
        <tr>
          <th>번호</th>
          <th>아이디</th>
          <th>이름</th>
          <th>닉네임</th>
          <th>이메일</th>
          <th>생일</th>
          <!-- <th>사진</th> -->
          <th>전화번호</th>
          <th>레벨</th>
          <th>상태</th>
          <th>등록일</th>
        </tr>
      </thead>
      <tbody>

        <c:forEach items="${buyerList}" var="buyer">
          <tr>
            <td>${buyer.member.number}</td>
            <td><a href='detail?id=${buyer.member.id}'>${buyer.member.id}</a></td>
            <td>${buyer.member.name}</td>
            <td>${buyer.member.nickname}</td>
            <td>${buyer.member.email}</td>
            <td>${buyer.member.birthday}</td>
            <%-- <td>${buyer.member.photo}</td> --%>
            <td>${buyer.member.phoneNumber}</td>
            <td>${buyer.member.level}</td>
            <td>${buyer.member.active}</td>
            <td>${buyer.member.registeredDate}</td>
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


