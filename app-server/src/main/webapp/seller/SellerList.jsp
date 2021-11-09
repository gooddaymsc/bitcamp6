<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <h1>회원 목록(판매자)</h1>
    <table class="table table-hover">
      <thead>
        <tr>
          <th>번호</th>
          <th>아이디</th>
          <th>가게명</th>
          <th>이름</th>
          <th>닉네임</th>
          <th>등급</th>
          <th>등록일</th>
        </tr>
      </thead>
      <tbody>

        <c:forEach items="${sellerList}" var="seller">
          <tr>
            <td>${seller.member.number}</td>
            <td><a href='detail?id=${seller.member.id}'>${seller.member.id}</a></td>
            <td>${seller.businessName}</td>
            <td>${seller.member.name}</td>
            <td>${seller.member.nickname}</td>
            <td>${seller.member.level}</td>
            <td>${seller.member.registeredDate}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
<footer>
<a href='../main/logout' class="btn btn-primary">로그아웃</a>
</footer>

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
