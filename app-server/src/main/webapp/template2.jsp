<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set scope="page" var="contextRoot" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html>
<style>
  .html-img-container {
    position: relative;
    top: 0;
    width: 100%;
    height: 100px;
  }

  .html-img {
    xborder: 2px solid red;
    left: 0;
    top: 0;
    width: 100%;
    height: 100px;
    position: fixed;
    object-fit: cover;
    xz-index: 10;
  }

  .html-white1 {
    xborder: 2px solid blue;
    top: 100px;
    position: absolute;
    width: 100%;
    height: 600px;
    background-color: white;
    z-index: -1;

  }

  .html-color2 {
    top: 600px;
    xborder: 2px solid yellow;
    position: absolute;
    width: 100%;
    height: 600px;
/*     background-color: #f5d6bb;
 */    z-index: -1;

  }
</style>

<head>
  <title>${pageTitle}</title>
  <link rel="stylesheet" href="${contextRoot}/node_modules/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" href="${contextRoot}/node_modules/sweetalert2/dist/sweetalert2.css">
  <link rel="stylesheet" href="${contextRoot}/css/common.css">
  <link rel="shortcut icon" href="${contextRoot}/favicon.ico" type="image/x-icon">
  <link rel="icon" href="${contextRoot}/favicon.ico" type="image/x-icon">

  <script src="https://kit.fontawesome.com/26add2f61b.js" crossorigin="anonymous"></script>
  <script src="${contextRoot}/node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="${contextRoot}/node_modules/bootstrap/dist/js/bootstrap.js"></script>
  <script src="${contextRoot}/node_modules/sweetalert2/dist/sweetalert2.js"></script>
</head>

<body>
 <!--
   <div class="first-container">
    <div class="html-line">
      <div class="html-img-container">
        <img class="html-img" src="../image/menuD.jpg"></img>
      </div>
      <div class="html-white1">
        <div class="html-color2">
        </div>
      </div>
    </div>
  </div>
 -->
  <div class="container">
    <jsp:include page="/header_all.jsp" />
    <jsp:include page="/sidebar.jsp" />

    <div id="content">
      <jsp:include page="${contentUrl}" />
    </div><!-- .content -->


  </div><!-- .container -->

  <jsp:include page="/footer.jsp" />
</body>

</html>