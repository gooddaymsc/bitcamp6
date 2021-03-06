<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set scope="page" var="contextRoot" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html>
<style>
@import url('https://fonts.googleapis.com/css2?family=Praise&display=swap');


</style>

<head>
  <title>${pageTitle}</title>
  <link rel="stylesheet" href="${contextRoot}/node_modules/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" href="${contextRoot}/node_modules/sweetalert2/dist/sweetalert2.css">
  <link rel="stylesheet" href="${contextRoot}/css/common.css?ver=1">
  <link rel="shortcut icon" href="${contextRoot}/favicon.ico" type="image/x-icon">
  <link rel="icon" href="${contextRoot}/favicon.ico" type="image/x-icon">

  <script src="https://kit.fontawesome.com/26add2f61b.js" crossorigin="anonymous"></script>
  <script src="${contextRoot}/node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="${contextRoot}/node_modules/bootstrap/dist/js/bootstrap.js"></script>
  <script src="${contextRoot}/node_modules/sweetalert2/dist/sweetalert2.js"></script>
</head>

<body>
  <div class="container">
    <jsp:include page="header_all.jsp" />
    <jsp:include page="sidebar.jsp" />

    <div id="content">
      <jsp:include page="${contentUrl}" />
    </div><!-- .content -->


  </div><!-- .container -->

</body>
  <jsp:include page="footer2.jsp" />

</html>