<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set scope="page" var="contextRoot" value="${pageContext.servletContext.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>${pageTitle}</title>
  <link rel="stylesheet" href="${contextRoot}/node_modules/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" href="${contextRoot}/node_modules/sweetalert2/dist/sweetalert2.css">
  <link rel="stylesheet" href="${contextRoot}/css/common.css3"> 
  
  <script src="${contextRoot}/node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="${contextRoot}/node_modules/bootstrap/dist/js/bootstrap.js"></script>
  <script src="${contextRoot}/node_modules/sweetalert2/dist/sweetalert2.js"></script>
</head>
<body>
<div class="container">

<div id="content">
<jsp:include page="${contentUrl}"/>
</div><!-- .content -->

<jsp:include page="/footer.jsp"/>

</div><!-- .container -->

</body>
</html>