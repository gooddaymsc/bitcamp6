<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
  <title>판매자목록</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">

  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>

  <style>
    .container {
      xborder: 1px solid red;
      width: 640px;
    }
  </style>
</head>

<body>
  <div class="container">
    <h1>회원 목록(판매자)</h1>
    <a href='form' class="btn btn-outline-primary btn-sm">새회원</a><br>
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
  </div><!-- .container -->
<footer>
<a href='../main/logout' class="btn btn-primary">로그아웃</a>
</footer>
</body>
</html>